/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jatnet.programajatnet2.model;

import java.time.LocalDateTime;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author penrr
 */
public class JugadorI {
    private final IntegerProperty IdJugador = new SimpleIntegerProperty(0);
    private final StringProperty clave = new SimpleStringProperty();
    //private final StringProperty nombre = new SimpleStringProperty();
    private final StringProperty nombre_avatar = new SimpleStringProperty();
    private final StringProperty correo = new SimpleStringProperty();
    private final StringProperty nacionalidad = new SimpleStringProperty();
    //private final ObjectProperty<LocalDateTime> fechaderegistro = new SimpleObjectProperty<>();
    private final StringProperty color = new SimpleStringProperty();

     public int getIdJugador() {
        return IdJugador.get();
    }

    public IntegerProperty IdJugadorProperty() {
        return IdJugador;
    }

    public void setIdcliente(int idcliente) {
        this.IdJugador.set(idcliente);
    }
    
     public String getClave() {
        return clave.get();
    }

    public StringProperty ClaveProperty() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave.set(clave);
    }
// public String getNombre() {
//        return nombre.get();
//    }

//    public StringProperty NombreProperty() {
//        return nombre;
//    }

//    public void setNombre(String nombre) {
//        this.nombre.set(nombre);
//    }
    public String getNombre_avatar() {
        return nombre_avatar.get();
    }

    public StringProperty Nombre_avatarProperty() {
        return clave;
    }

    public void setNombre_avatar(String Nombre_avatar) {
        this.nombre_avatar.set(Nombre_avatar);
    }
     public String getCorreo() {
        return correo.get();
    }

    public StringProperty CorreoProperty() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo.set(correo);
    }
     public String getNacionalidad() {
        return nacionalidad.get();
    }

    public StringProperty NacionalidadProperty() {
        return nacionalidad;
    }

    public void setNacionalidad(String Nacionalidad) {
        this.nacionalidad.set(Nacionalidad);
    }
     public String getColoar() {
        return color.get();
    }

    public StringProperty ColorProperty() {
        return color;
    }

    public void setColor(String Nombre_avatar) {
        this.color.set(Nombre_avatar);
    }

    @Override
    public String toString() {
        return "JugadorI{" + "IdJugador=" + IdJugador + ", clave=" + clave + ", nombre_avatar=" + nombre_avatar + ", correo=" + correo + ", nacionalidad=" + nacionalidad + ", color=" + color + '}';
    }

}
