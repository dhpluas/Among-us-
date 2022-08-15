/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jatnet.programajatnet2;

import com.jatnet.programajatnet2.dao.JugadorDAO;
import com.jatnet.programajatnet2.dao.ProductoDAO;
import com.jatnet.programajatnet2.model.JugadorI;
import com.jatnet.programajatnet2.model.Producto;
import com.jatnet.programajatnet2.util.ConnectionBD;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author ken
 */
public class ProductoController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton btnListar;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private JFXButton btnBorrar;
    @FXML
    private JFXTextField cjBuscar;
    @FXML
    private TableView<JugadorI> tablaProductos;
    
    
    
     @FXML
    private TableColumn<JugadorI, String> colColor;

    @FXML
    private TableColumn<JugadorI, String> colCorreo;

    @FXML
    private TableColumn<JugadorI, Integer> colId;

    @FXML
    private TableColumn<JugadorI, String> colNacionalidad;

    @FXML
    private TableColumn<JugadorI, String> colNombre;

    @FXML
    private TableColumn<JugadorI, String> colNombre_avatar;

       
    
 
    static int id;

    /**
     * Initializes the controller class.
     */
     private ObservableList<JugadorI> listaProductos = FXCollections.observableArrayList();

    private ConnectionBD conexionBD = new ConnectionBD();
    private JugadorDAO jugadorDAO;
   // private ProductoDAO productoDAO;

    private Stage stageProducto;
    private RegistrarClienteController registraJugadorController;
    private RegistrarProductoController    registrarProductoController;
     private editarJugadorController   editarJugadorController;

    private ObjectProperty<JugadorI> objProducto = new SimpleObjectProperty<>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         //colNombre.setCellValueFactory(param -> param.getValue().nombreproductoProperty());
       // colNombre.setCellValueFactory(param -> param.getValue().NombreProperty());
        colColor.setCellValueFactory(param -> param.getValue().ColorProperty());
        colCorreo.setCellValueFactory(param -> param.getValue().CorreoProperty());
        colId.setCellValueFactory(new PropertyValueFactory<>("IdJugador"));
         //colId.setCellValueFactory(param -> param.getValue().IdJugadorProperty());
          colNacionalidad.setCellValueFactory(param -> param.getValue().NacionalidadProperty());
          colNombre_avatar.setCellValueFactory(param -> param.getValue().Nombre_avatarProperty());

//        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
//        colStock.setCellFactory(param -> {
//            return new TableCell<Producto, Double>() {
//
//                @Override
//                protected void updateItem(Double value, boolean empty) {
//                    super.updateItem(value, empty);
//                    if (value == null || empty) {
//                        setText(null);
//                        setStyle(null);
//                    } else {
//                        setAlignment(Pos.CENTER);
//                        setText("" +value);
//                        Producto producto = getTableView().getItems().get(getIndex());
//                        if(value > producto.getStockminimo()){
//                            setStyle("-fx-background-color: #4CAF50; fx-font-weight: bold; -fx-text-fill: white; ");
//                        }else if(value > 1 && value <= getTableView().getItems().get(getIndex()).getStockminimo() ){
//                            setStyle("-fx-background-color: #FFFF00; fx-font-weight: bold; -fx-text-fill: black; ");
//                        }else if(value < 1){
//                            setStyle("-fx-background-color: #F44336; fx-font-weight: bold; -fx-text-fill: white; ");
//                        }
//                    }
//
//                }
//            };
//        });

//        colStockMinimo.setCellValueFactory(new PropertyValueFactory<>("stockminimo"));
//
//        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
//
//        coolFechaVencimiento.setCellValueFactory(new PropertyValueFactory<>("fechadevencimiento"));
//        coolFechaVencimiento.setCellFactory(param -> {
//            return new TableCell<Producto, LocalDate>() {
//                @Override
//                protected void updateItem(LocalDate value, boolean empty) {
//                    super.updateItem(value, empty);
//                    if(!empty || value!=null) {
//                        setText(DateTimeFormatter.ofPattern("EEEE dd MMM yyyy").format(value));
//                        setAlignment(Pos.CENTER);
//
//                        if (Period.between(LocalDate.now(), value).isNegative() || Period.between(LocalDate.now(), value).isZero()) {
//                            setStyle("-fx-background-color: #F44336; fx-font-weight: bold; -fx-text-fill: white; ");
//                        } else if (LocalDate.now().plusDays(5).isBefore(value)) {
//                            setStyle("-fx-background-color: #4CAF50; fx-font-weight: bold; -fx-text-fill: white;");
//                        } else if ((LocalDate.now().plusDays(5).isBefore(value) || LocalDate.now().plusDays(5).equals(value)) || (value.isBefore(LocalDate.now().plusDays(5)) && value.isAfter(LocalDate.now()))) {
//                            setStyle("-fx-background-color: #FFFF00; fx-font-weight: bold;");
//                        }
//                    } else {
//                        setStyle(null);
//                        setText(null);
//                    }
//                }
//            };
//        });

        tablaProductos.setItems(listaProductos);

        objProducto.bind(tablaProductos.getSelectionModel().selectedItemProperty());
    }    

    @FXML
    private void listarProductos(ActionEvent event) {
        Task<List<JugadorI>> listTask = new Task<List<JugadorI>>() {
            @Override
            protected List<JugadorI> call() throws Exception {
                conexionBD.conector();
                jugadorDAO = new JugadorDAO(conexionBD);
                //productoDAO = new ProductoDAO(conexionBD);
                //return productoDAO.getProductos();
                return jugadorDAO.getJugador2();
            }
        };

        listTask.setOnFailed(event1 -> {
            conexionBD.CERRAR();
            tablaProductos.setPlaceholder(null);
        });
        listTask.setOnSucceeded(event1 -> {
            tablaProductos.setPlaceholder(null);
            conexionBD.CERRAR();
            listaProductos.setAll(listTask.getValue());
            tablaProductos.getColumns().forEach(column -> {
                com.jatnet.programajatnet2.util.Metodos.changeSizeOnColumn(column, tablaProductos, -1);
            });
        });

        ProgressIndicator progressIndicator = new ProgressIndicator();
        tablaProductos.setPlaceholder(progressIndicator);

        Thread hilo = new Thread(listTask);
        hilo.start();
    }

    @FXML
    private void nuevoProducto(ActionEvent event) throws IOException {
          id=0;
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/jatnet/programajatnet2/RegistrarProducto.fxml"));
        AnchorPane ap = loader.load();
        registrarProductoController = loader.getController();
        Scene scene = new Scene(ap);
        stageProducto = new Stage();
        stageProducto.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/productos.png")));
        stageProducto.setScene(scene);
        stageProducto.initOwner(root.getScene().getWindow());
        stageProducto.initModality(Modality.WINDOW_MODAL);
        stageProducto.initStyle(StageStyle.DECORATED);
        stageProducto.setResizable(false);
        stageProducto.setOnCloseRequest((WindowEvent e) -> {
            root.setEffect(null);
        });
        stageProducto.setOnHidden((WindowEvent e) -> {
            root.setEffect(null);
        });
        root.setEffect(new GaussianBlur(7.0));
        stageProducto.showAndWait();
        listarProductos(null);
    }

    @FXML
    private void editarProducto(ActionEvent event)throws IOException {
          id =0;
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/jatnet/programajatnet2/editarJugador.fxml"));
        AnchorPane ap = loader.load();
        editarJugadorController = loader.getController();
        Scene scene = new Scene(ap);
        stageProducto = new Stage();
        stageProducto.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/productos.png")));
        stageProducto.setScene(scene);
        stageProducto.initOwner(root.getScene().getWindow());
        stageProducto.initModality(Modality.WINDOW_MODAL);
        stageProducto.initStyle(StageStyle.DECORATED);
        stageProducto.setResizable(false);
        stageProducto.setOnCloseRequest((WindowEvent e) -> {
            root.setEffect(null);
        });
        stageProducto.setOnHidden((WindowEvent e) -> {
            root.setEffect(null);
        });
        root.setEffect(new GaussianBlur(7.0));
       // stageProducto.showAndWait();
        //listarProductos(null);
//         if(objProducto.get()==null){
//             com.jatnet.programajatnet2.util.Metodos.rotarError(tablaProductos);
//            
//            return;
//        }
      
        try {
            this.conexionBD.conector();
            jugadorDAO = new JugadorDAO(conexionBD);
             System.out.println("ffffffffffffffff");
              System.out.println(objProducto.get().getIdJugador());
            
              editarJugadorController.setJugador(jugadorDAO.getById(objProducto.get().getIdJugador()));
          
            id = objProducto.get().getIdJugador();
        } catch (SQLException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
            org.controlsfx.control.Notifications.create().title("Aviso").text("Error al intentar buscar el producto\n"+ex.getMessage()).position(Pos.CENTER).showError();
        } finally {
            this.conexionBD.CERRAR();
        }
        stageProducto.setTitle("Editar -- Jugador");
        stageProducto.showAndWait();
        
        if (editarJugadorController.isSTATUS()) {
            listarProductos(null);
        }
    }

    public int getId() {
        return id;
    }

    @FXML
    private void borrarProducto(ActionEvent event) throws SQLException {
          if(objProducto.get()==null){
              com.jatnet.programajatnet2.util.Metodos.rotarError(tablaProductos);
            
            return;
        }
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "¿DESEA ELIMINAR EL PRODUCTO?", ButtonType.YES, ButtonType.NO);
        a.setHeaderText(this.objProducto.get().getNombre_avatar());
        if (a.showAndWait().get() == ButtonType.YES) {
            conexionBD.conector();
            jugadorDAO = new JugadorDAO(conexionBD);
            boolean delete = jugadorDAO.delete(objProducto.get().getIdJugador());
            listarProductos(null);
            this.conexionBD.CERRAR();
        }
    }

    @FXML
    private void buscarProductoKeyReleased(KeyEvent event) {
    }
    
}
