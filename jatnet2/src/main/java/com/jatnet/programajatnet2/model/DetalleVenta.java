/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jatnet.programajatnet2.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author ken
 */
public class DetalleVenta {
    private final IntegerProperty iddetalleventa = new SimpleIntegerProperty();
    private Venta venta;
    private Producto producto;
    private final DoubleProperty cantidad = new SimpleDoubleProperty();
    private final DoubleProperty precioventa = new SimpleDoubleProperty();
    private final DoubleProperty total = new SimpleDoubleProperty();

    public DetalleVenta() {
        NumberBinding multiply = Bindings.multiply(this.precioventaProperty(), cantidadProperty());
        this.totalProperty().bind(multiply);
    }

    public int getIddetalleventa() {
        return iddetalleventa.get();
    }

    public IntegerProperty iddetalleventaProperty() {
        return iddetalleventa;
    }

    public void setIddetalleventa(int iddetalleventa) {
        this.iddetalleventa.set(iddetalleventa);
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public double getCantidad() {
        return cantidad.get();
    }

    public DoubleProperty cantidadProperty() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad.set(cantidad);
    }

    public double getPrecioventa() {
        return precioventa.get();
    }

    public DoubleProperty precioventaProperty() {
        return precioventa;
    }

    public void setPrecioventa(double precioventa) {
        this.precioventa.set(precioventa);
    }

    public double getTotal() {
        return total.get();
    }

    public DoubleProperty totalProperty() {
        return total;
    }

    public void setTotal(double total) {
        this.total.set(total);
    }
}
