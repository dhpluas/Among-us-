/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jatnet.programajatnet2.dao;

import com.jatnet.programajatnet2.model.Producto;
import com.jatnet.programajatnet2.util.ConnectionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ken
 */
public class ProductoDAO {
     private ConnectionBD conexionBD;

    public ProductoDAO(ConnectionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    public List<Producto> getProductos() throws SQLException {
        List<Producto> productos = new ArrayList<>();

        Producto p = null;
        ResultSet rs = conexionBD.CONSULTAR("SELECT * FROM productos");
        while (rs.next()) {
            p = new Producto();

            p.setIdproducto(rs.getInt("idproductos"));
            p.setNombreproducto(rs.getString("nombreproducto").trim());
            p.setCodigodebarras(rs.getString("codigodebarras").trim());
            p.setReferencia(rs.getString("referencia").trim());
            p.setStock(rs.getDouble("stock"));
            p.setStockminimo(rs.getDouble("stockminimo"));
            p.setDescripcion(rs.getString("descripcion").trim());
            p.setEstado(rs.getString("estado").trim());
            p.setPrecio(rs.getDouble("precio"));
            p.setFechadevencimiento(rs.getDate("fechadevencimiento").toLocalDate());

            productos.add(p);
        }

        return productos;
    }

    public int guardar(Producto pro) throws SQLException {
        String sql = "";
        if (pro.getIdproducto() == 0) {
            sql = "INSERT INTO productos ( ";
            sql += " codigodebarras, referencia, nombreproducto, stock, stockminimo, descripcion, estado, precio, fechadevencimiento, imagen ";
            sql += ") VALUES (";
            sql += " '"+pro.getCodigodebarras()+"' , '"+pro.getReferencia()+"' , '"+pro.getNombreproducto()+"' , '"+pro.getStock()+"' , ";
            sql += " '"+pro.getStockminimo()+"' , '"+pro.getDescripcion()+"' , '"+pro.getEstado()+"' , '"+pro.getPrecio()+"' , ";
            sql += " '"+pro.getFechadevencimiento()+"' , ? ";
            sql += ")";
        } else {
            sql = "UPDATE productos SET \n"
            + "	codigodebarras='"+pro.getCodigodebarras()+"', referencia='"+pro.getReferencia()+"', nombreproducto='"+pro.getNombreproducto()+"', \n"
            + " stock=" + pro.getStock() + ", "+"stockminimo="+pro.getStockminimo()+", descripcion='"+pro.getDescripcion()+"', estado='"+pro.getEstado()+"', precio="+pro.getPrecio()+", \n"
            + " fechadevencimiento='"+pro.getFechadevencimiento()+"' , imagen=? WHERE idproductos="+pro.getIdproducto()+";";
        }


        PreparedStatement pst = conexionBD.getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pst.setBytes(1, pro.getImagen());

        int insert = pst.executeUpdate();
        if (pro.getIdproducto() == 0) {
            ResultSet rs = pst.getGeneratedKeys();
            rs.next();
            insert = rs.getInt(1);
            rs.close();
        }
        return insert;
    }

    public Producto getById(int idproducto) throws SQLException {
        Producto p = null;
        ResultSet rs = this.conexionBD.CONSULTAR("SELECT * FROM productos WHERE idproductos="+idproducto);
        if(rs.next()){
            p = new Producto();
            p.setIdproducto(idproducto);
            p.setCodigodebarras(rs.getString("codigodebarras").trim());
            p.setReferencia(rs.getString("referencia").trim());
            p.setNombreproducto(rs.getString("nombreproducto").trim());
            p.setStock(rs.getDouble("stock"));
            p.setStockminimo(rs.getDouble("stockminimo"));
            p.setDescripcion(rs.getString("descripcion").trim());
            p.setImagen(rs.getBytes("imagen"));
            p.setEstado(rs.getString("estado").trim());
            p.setPrecio(rs.getDouble("precio"));
            p.setFechadevencimiento(rs.getDate("fechadevencimiento").toLocalDate());
        }
        return p;
    }
    
 

    public boolean delete(int idproducto) throws SQLException {
        String sql = "DELETE FROM productos WHERE idproductos="+idproducto;
        return conexionBD.GUARDAR(sql);
    }

    public List<Producto> getAll() throws SQLException{
        List<Producto> lista = new ArrayList<>();
        Producto p = null;
        ResultSet rs = this.conexionBD.CONSULTAR("SELECT idproductos, codigodebarras, referencia, nombreproducto, stock, stockminimo, descripcion, estado, fechaderegistro, precio, fechadevencimiento\n" +
                "	FROM productos;");
        while(rs.next()){
            p = new Producto();
            p.setIdproducto(rs.getInt("idproductos"));
            p.setCodigodebarras(rs.getString("codigodebarras").trim());
            p.setReferencia(rs.getString("referencia").trim());
            p.setNombreproducto(rs.getString("nombreproducto").trim());
            p.setStock(rs.getDouble("stock"));
            p.setStockminimo(rs.getDouble("stockminimo"));
            p.setDescripcion(rs.getString("descripcion").trim());
            p.setEstado(rs.getString("estado").trim());
            p.setPrecio(rs.getDouble("precio"));
            p.setFechadevencimiento(rs.getDate("fechadevencimiento").toLocalDate());

            lista.add(p);
        }
        return lista;
    }
}
