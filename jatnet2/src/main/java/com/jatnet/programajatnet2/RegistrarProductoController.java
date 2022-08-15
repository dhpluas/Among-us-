/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jatnet.programajatnet2;

import com.jatnet.programajatnet2.model.Producto;
import animatefx.animation.Tada;
import com.jatnet.programajatnet2.dao.ProductoDAO;
import com.jatnet.programajatnet2.util.ConnectionBD;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import com.github.sarxos.webcam.Webcam;
import static java.awt.SystemColor.window;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author ken
 */
public class RegistrarProductoController implements Initializable {
    //private ProductoController productoControl= new ProductoController();
    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField cjnombre;
    @FXML
    private JFXTextField cjcodigo;
    @FXML
    private JFXTextField cjreferencia;
    @FXML
    private JFXTextField cjstock;
    @FXML
    private JFXTextField cjstockminimo;
    @FXML
    private JFXTextField cjprecio;
    @FXML
    private JFXDatePicker cjfechavencimiento;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private ImageView imageView;
    @FXML
    private JFXButton btnTomarFoto;
    @FXML
    private boolean estadoCamara = false;
    private Webcam selWebCam = null;
    private BufferedImage bufferImage;
    private JFXButton btnBuscarImagen;
    private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();
    private ConnectionBD conexionBD = new ConnectionBD();
    private ProductoDAO productoDAO;
    private Producto producto = null;
    private boolean STATUS = false;
    private boolean entro = false;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         
         cjprecio.setTextFormatter(new TextFormatter<>(new StringConverter<Double>(){

            @Override
            public String toString(Double object) {
                if (object != null) {
                    return NumberFormat.getCurrencyInstance().format(object);
                }
                return NumberFormat.getCurrencyInstance().format(0.0);
            }

            @Override
            public Double fromString(String valor) {
                try {
                    return NumberFormat.getCurrencyInstance().parse(valor).doubleValue();
                } catch (ParseException e) {
                    try {
                        return NumberFormat.getCurrencyInstance().parse("$".concat(valor)).doubleValue();
                    } catch (ParseException ex) { ex.printStackTrace(); }
                }
                return 0.0;
            }
        }));
    }    
       public Producto getProducto() {
        return producto;
    }

    public boolean isSTATUS() {
        return STATUS;
    }

    public void setSTATUS(boolean STATUS) {
        this.STATUS = STATUS;
    }

    @FXML
    private void guardar(ActionEvent event) throws SQLException {
        
        if (cjnombre.getText().isEmpty()) {
            new Tada(cjnombre).play();
            org.controlsfx.control.Notifications.create().title("Aviso").text("Ingrese el nombre del p").position(Pos.CENTER).showWarning();
            return;
        }
        double precio = 0;
        try {
            precio = (NumberFormat.getCurrencyInstance().parse((cjprecio.getText())).doubleValue());
        } catch (ParseException ex) {
            new Tada(cjprecio).play();
            org.controlsfx.control.Notifications.create().title("Aviso").text("Precio no valido\n" + ex.getMessage()).position(Pos.CENTER).showError();
            return;
        }
        if (cjfechavencimiento.getValue() == null) {
            new Tada(cjfechavencimiento).play();
            org.controlsfx.control.Notifications.create().title("Aviso").text("Seleccione una fecha de vencimiento").position(Pos.CENTER).showWarning();
            return;
        }

        if (getProducto() == null) {
            this.producto = new Producto();
        }

        producto.setNombreproducto(cjnombre.getText().trim());
        producto.setCodigodebarras(cjcodigo.getText().trim());
        producto.setReferencia(cjreferencia.getText().trim());
        producto.setStock(Double.parseDouble(cjstock.getText()));
        producto.setStockminimo(Double.parseDouble(cjstockminimo.getText()));
        producto.setPrecio(precio);
        producto.setFechadevencimiento(cjfechavencimiento.getValue());
        
//       producto.setImagen(com.jatnet.programajatnet2.util.Metodos.ImageToByte(imageView.getImage()));

       int numer= ProductoController.id;
        conexionBD.conector();
        
        productoDAO = new ProductoDAO(conexionBD);
        if(numer!=0){
        Producto producto2 = new Producto();
        producto2=   productoDAO.getById(numer);
         if(entro == false){
            producto.setImagen(producto2.getImagen());
             System.out.println("ENTRO CUANDO ES NULL");
         }else {
            producto.setImagen(com.jatnet.programajatnet2.util.Metodos.ImageToByte(imageView.getImage()));
            System.out.println("ENTRO CUANDO NO ES NULL");
         }}
             if(ProductoController.id ==0){
              Image     image = new Image("/images/productos.png");
              imageView.setImage(image);
             }
       // productoDAO.imagen(25);
       //  producto.setImagen(productoDAO.imagen(25));
        try {
            int guardar = productoDAO.guardar(producto);
            if ( guardar > 0 ){
                setSTATUS(true);
                com.jatnet.programajatnet2.util.Metodos.closeEffect(root);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirCamara(ActionEvent event) {
         if (Webcam.getWebcams().size() < 1) {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "NO HAY CAMARAS DISPONIBLES", ButtonType.OK);
            a.showAndWait();
            return;
        }

        if (!estadoCamara) {
            imageView.imageProperty().unbind();
            imageView.setImage(new Image(getClass().getResourceAsStream("/images/load.gif")));
            Task<Void> webCamIntilizer = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    if (selWebCam == null) {
                        selWebCam = Webcam.getWebcams().get(0);
                        selWebCam.open();
                    }

                    estadoCamara = true;
                    Task<Void> task = new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            while (estadoCamara) {
                                try {
                                    if ((bufferImage = selWebCam.getImage()) != null) {
                                        Platform.runLater(() -> {
                                            final Image mainiamge = SwingFXUtils.toFXImage(bufferImage, null);
                                            imageProperty.set(mainiamge);
                                        });
                                        bufferImage.flush();
                                    }
                                } catch (Exception e) {
                                } finally {
                                }
                            }
                            return null;
                        }
                    };
                    Thread th = new Thread(task);
                    th.setDaemon(true);
                    th.start();
                    imageView.imageProperty().bind(imageProperty);

                    return null;
                }
            };
            new Thread(webCamIntilizer).start();
        } else {
            estadoCamara = false;
        }
    }
//(ActionEvent event)
    @FXML
    private void buscarImagen(ActionEvent event) {
      
       //  Image  image = new Image("/images/productos.png");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen AHORA");
       fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
         // Obtener la imagen seleccionada
        File imgFile = fileChooser.showOpenDialog(null);
          if (imgFile != null) {
             Image     image = new Image("file:" + imgFile.getAbsolutePath());
            imageView.setImage(image);
            entro = true;
            ProductoController.id=-1;
        }
                  }
    
    public void setProducto(Producto producto) {
        this.producto = producto;

        cjnombre.setText(producto.getNombreproducto().trim());
        cjcodigo.setText(producto.getCodigodebarras());
        cjreferencia.setText(producto.getReferencia());
        cjstock.setText(""+ producto.getStock());
        cjstockminimo.setText(""+ producto.getStockminimo());
        cjprecio.setText((NumberFormat.getCurrencyInstance().format(producto.getPrecio())));
        cjfechavencimiento.setValue(producto.getFechadevencimiento());
        try {
            if (producto.getImagen() != null) {
                imageView.setImage(SwingFXUtils.toFXImage(ImageIO.read(new ByteArrayInputStream(producto.getImagen())), null));
            }
        } catch (IOException ex) {
            Logger.getLogger(RegistrarProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
