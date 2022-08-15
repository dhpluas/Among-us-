/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jatnet.programajatnet2;
import animatefx.animation.Tada;
import com.jatnet.programajatnet2.dao.JugadorDAO;
import com.jatnet.programajatnet2.model.JugadorI;
import com.jatnet.programajatnet2.model.Producto;
import com.jatnet.programajatnet2.util.ConnectionBD;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.imageio.ImageIO;

/**
 *
 * @author penrr
 */
public class editarJugadorController implements Initializable  {
     @FXML
    private JFXButton btnGuardar;

    @FXML
    private JFXTextField cj_nombre_avatar;

    @FXML
    private JFXTextField cjcolor;

    @FXML
    private JFXTextField cjcontraseña;

    @FXML
    private JFXTextField cjcorreo;

    @FXML
    private JFXTextField cjnacionalidad;

    @FXML
    private ImageView imageView;

    @FXML
    private AnchorPane root;

    @FXML
    void guardar(ActionEvent event) {
          if (cj_nombre_avatar.getText().isEmpty()) {
            new Tada(cj_nombre_avatar).play();
            org.controlsfx.control.Notifications.create().title("Aviso").text("Ingrese el nombre de avatar").position(Pos.CENTER).showWarning();
            return;
        }

        if(getJugador()==null){
            jugador = new JugadorI();
        }
       
        jugador.setNombre_avatar(cj_nombre_avatar.getText());
        jugador.setClave(cjcontraseña.getText());
        jugador.setColor(cjcolor.getText());
        jugador.setCorreo(cjcorreo.getText());
        jugador.setNacionalidad( cjnacionalidad.getText());
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
      private JugadorI jugador;
    private JugadorDAO JugadorDAO;
    private ConnectionBD conexionBD = new ConnectionBD();
 private boolean STATUS = false;
  @Override
    public void initialize(URL url, ResourceBundle rb) {
          
    } 
     public JugadorI getJugador() {
        return jugador;


    }
     public void setJugador(JugadorI jugador) {
        this.jugador = jugador;
                 cj_nombre_avatar.setText(jugador.getNombre_avatar().trim());
                 cjcolor.setText(jugador.getColoar().trim());
                 cjcontraseña.setText(jugador.getClave().trim());
                 cjcorreo.setText(jugador.getCorreo().trim());
                 cjnacionalidad.setText(jugador.getNacionalidad().trim());
    
           
       
    }
     public boolean isSTATUS() {
        return STATUS;
    }
    
        

       
    
}
