/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jatnet.programajatnet2;

import animatefx.animation.Tada;
//import com.jatnet.programajatnet2.dao.ClienteDAO;
import com.jatnet.programajatnet2.dao.JugadorDAO;
//import com.jatnet.programajatnet2.model.Cliente;
import com.jatnet.programajatnet2.model.JugadorI;
import com.jatnet.programajatnet2.util.ConnectionBD;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author ken
 */
public class RegistrarClienteController implements Initializable {

    @FXML
    private VBox root;
    @FXML
    private HBox header;
    @FXML
    private Text txtCliente;
   
    @FXML
    private JFXButton btnGuardar;

    @FXML
    private JFXTextField cjClave;

    @FXML
    private JFXTextField cjColor;

    @FXML
    private JFXTextField cjCorreo;

    @FXML
    private JFXTextField cjNacionalidad;


   
    @FXML
    private JFXTextField cjNombre_avatar;
    
    private JugadorI jugador;
    private JugadorDAO JugadorDAO;
    private ConnectionBD conexionBD = new ConnectionBD();
 private boolean STATUS = false;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
    }    

    @FXML
    private void guardar(ActionEvent event) {
    
        if (cjNombre_avatar.getText().isEmpty()) {
            new Tada(cjNombre_avatar).play();
            org.controlsfx.control.Notifications.create().title("Aviso").text("Ingrese el nombre de avatar").position(Pos.CENTER).showWarning();
            return;
        }

        if(getCliente()==null){
            jugador = new JugadorI();
        }
       
        jugador.setNombre_avatar(cjNombre_avatar.getText());
        jugador.setClave(cjClave.getText());
        jugador.setColor(cjColor.getText());
        jugador.setCorreo(cjCorreo.getText());
        jugador.setNacionalidad( cjNacionalidad.getText());
        try {
            this.conexionBD.conector();
           
            JugadorDAO = new JugadorDAO(conexionBD);
            int n = JugadorDAO.guardar(jugador);
            if (jugador.getIdJugador() == 0) {
                jugador.setIdcliente(n);
              
            }
            com.jatnet.programajatnet2.util.Metodos.closeEffect(root);
           
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarClienteController.class.getName()).log(Level.SEVERE, null, ex);
            org.controlsfx.control.Notifications.create().title("Aviso").text("NO SE PUDO GUARDAR EL CLIENTE\n" + ex.getMessage()).position(Pos.CENTER).showError();
        } 
        finally {
            this.conexionBD.CERRAR();
        }
    }
      public JugadorI getCliente() {
        return jugador;
    }

    public void setCliente(JugadorI jugador) {
        this.jugador = jugador;
    }
     public boolean isSTATUS() {
        return STATUS;
    }
}
