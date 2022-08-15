/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jatnet.programajatnet2;

import com.jatnet.programajatnet2.dao.JugadorDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import com.jatnet.programajatnet2.model.Usuario;
import com.jatnet.programajatnet2.dao.UsuarioDAO;
import com.jatnet.programajatnet2.model.Jugador;
import com.jatnet.programajatnet2.util.ConnectionBD;
import com.jatnet.programajatnet2.util.Sesion;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ken
 */
public class LoginController implements Initializable {

    @FXML
    private HBox root;
    @FXML
    private JFXButton btnWhatsapp;
    @FXML
    private JFXButton btnWww;
    @FXML
    private JFXTextField cjUsuario;
    @FXML
    private JFXPasswordField cjPassword;
    @FXML
    private JFXButton botoInicia;

    /**
     * Initializes the controller class.
     */
    
    private ConnectionBD conexionBD;
    //private UsuarioDAO usuarioDAO;
    private JugadorDAO jugadorDao;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        botoInicia.setOnAction(event -> {
//            System.out.println("Hola");
//        });
    }    

    @FXML
    private void login(ActionEvent event) throws SQLException, IOException {
        //System.out.println("Hola");
          String username = cjUsuario.getText().trim();
        String password = cjPassword.getText().trim();

        conexionBD = new ConnectionBD();
        conexionBD.conector();
        //usuarioDAO = new UsuarioDAO(conexionBD);
        jugadorDao = new JugadorDAO(conexionBD);
        //Usuario usuario = usuarioDAO.getUsuario(username, password);
        Jugador jugador = jugadorDao.getJugador(username, password);
        
        if (jugador == null) {
            new animatefx.animation.Tada(cjUsuario).play();
            new animatefx.animation.Tada(cjPassword).play();
             System.out.println("usurio no exite");
            org.controlsfx.control.Notifications
                .create()
                .title("Mensaje")
                .text("EL usuario o contraseña no coinciden")
                .position(Pos.CENTER).showInformation();

            return;
    }
         //FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Dashboard.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/jatnet/programajatnet2/Dashboard.fxml"));
        BorderPane borderPane = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(borderPane));
        stage.setTitle(Sesion.getSesion(null).getNombre());
        stage.setMaximized(true);
        com.jatnet.programajatnet2.util.Metodos.closeEffect(root);
   
        stage.show();
    
    }
}
