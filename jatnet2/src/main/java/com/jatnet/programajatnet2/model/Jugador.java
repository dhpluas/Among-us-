/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jatnet.programajatnet2.model;

import javafx.beans.property.*;

import java.time.LocalDateTime;

public class Jugador {
    private Integer IdJugador;
    private String clave;
    private String nombre_avatar;
    private String nombre;
    private String correo;
    private String nacionalidad;
    private String color;

    public Integer getIdJugador() {
        return IdJugador;
    }

    public void setIdUsuario(Integer IdJuador) {
        this.IdJugador = IdJugador;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre_avatar() {
        return nombre_avatar;
    }

    public void setNombre_avatar(String Nombre_avatar) {
        this.nombre_avatar = nombre_avatar;
    }
     public String getNombre() {
        return nombre;
    }
  public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }
    
    public void setCorreo(String Correo) {
        this.correo = correo;
    }
     public String getNacionalidad() {
        return nacionalidad;
    }
      public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
   public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
   
}
