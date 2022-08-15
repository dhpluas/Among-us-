/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jatnet.programajatnet2;

import com.jatnet.programajatnet2.dao.JugadorDAO;
import com.jatnet.programajatnet2.model.Jugador;
import com.jatnet.programajatnet2.model.JugadorI;
import com.jatnet.programajatnet2.util.ConnectionBD;
import com.jfoenix.controls.JFXTabPane;

import javafx.scene.control.TabPane;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 *
 * @author ken
 */
public class DashboardController implements Initializable{

    @FXML
    private BorderPane root;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menuArchivo;
    @FXML
    private MenuItem menuconfig;
    @FXML
    private MenuItem menuSalir;
    @FXML
    private Menu menuProductos;
    @FXML
    private MenuItem menuVerProductos;
    @FXML
    private Menu menuVentas;
    @FXML
    private MenuItem menuRealizarVenta;
    @FXML
    private Menu menuClientes;
    @FXML
    private MenuItem nuevoCliente;
    @FXML
    private TabPane tabPane;

  private Tab tabProductos;
  private JugadorDAO jugador;
  private ConnectionBD conexionBD;
  @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }

    @FXML
    private void abrirConfiguracion(ActionEvent event) {
    }

    @FXML
    private void salir(ActionEvent event) {
    }

    @FXML
    private void mostrarTablaProductos(ActionEvent event) throws IOException, SQLException {
       
       
           if (tabProductos == null) {
            AnchorPane ap = FXMLLoader.load(getClass().getResource("/com/jatnet/programajatnet2/Producto.fxml"));
            tabProductos = new Tab("PRODUCTOS", ap);
            tabProductos.setGraphic(FontIcon.of(new FontIcon("fa-tags").getIconCode(), 20, Color.valueOf("#FFF")));
            tabProductos.setClosable(true);
            tabProductos.setOnClosed(event1 -> {
                tabProductos = null;
            });
            tabPane.getTabs().add(tabProductos);
        }
        tabPane.getSelectionModel().select(tabProductos);
        
    }

    @FXML
    private void mostraRealizarVenta(ActionEvent event)throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/jatnet/programajatnet2/RegistrarVenta.fxml"));
        VBox ap = loader.load();
        RegistrarVentaController rvc = loader.getController();
        Tab tabRealizarVenta = new Tab(LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEE dd MMM hh:mm:ss a")), ap);
        tabRealizarVenta.setGraphic(FontIcon.of(new FontIcon("fa-asl-interpreting").getIconCode(), 20, Color.valueOf("#FFF")));
        tabRealizarVenta.setClosable(true);
        tabPane.getTabs().add(tabRealizarVenta);
//        rvc.getCjCodigoBarras().requestFocus();
        tabPane.getSelectionModel().select(tabRealizarVenta);
    }

    @FXML
    private void nuevoCliente(ActionEvent event)throws IOException {
    
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/jatnet/programajatnet2/RegistrarCliente.fxml"));
        VBox vbox = loader.load();
        Scene scene = new Scene(vbox);
        Stage stage = new Stage();
        stage.setTitle("Nuevo cliente");
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/add_user.png")));
        stage.setScene(scene);
        stage.initOwner(root.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable(false);
        stage.setIconified(false);
        stage.showAndWait();
    }
    
}
