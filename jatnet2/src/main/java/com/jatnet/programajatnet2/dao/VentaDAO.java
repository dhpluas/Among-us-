/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jatnet.programajatnet2.dao;

import com.jatnet.programajatnet2.model.DetalleVenta;
import com.jatnet.programajatnet2.model.Venta;
import com.jatnet.programajatnet2.util.ConnectionBD;
import com.jatnet.programajatnet2.util.Sesion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ken
 */
public class VentaDAO {
    private ConnectionBD conexionBD;

    public VentaDAO(ConnectionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    public int guardar(Venta v) throws SQLException {
//        String sql = "INSERT INTO venta(\n"
//                + "	idcliente, idusuario, formadepago)\n"
//                + "	VALUES ("+v.getCliente().getIdcliente()+" , "+ Sesion.getSesion(null).getIdUsuario()+" , '"+v.getFormadepago()+"');";

//        this.conexionBD.getConexion().setAutoCommit(false);
//
//        PreparedStatement pst = this.conexionBD.getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//        if (pst.executeUpdate() > 0) {
//            ResultSet rs = pst.getGeneratedKeys();
//            rs.next();
//            int idventa = rs.getInt(1);
//            sql = "INSERT INTO detalleventa( idventa, idproducto, preciodeventa, cantidad) VALUES\n";
//            for (DetalleVenta dv : v.getDetalleventa()) {
//                sql += "(";
//                sql += " "+idventa+", "+dv.getProducto().getIdproducto()+", "+dv.getPrecioventa()+", "+dv.getCantidad()+" ";
//                sql += "),";
//            }
//            sql = sql.substring(0, sql.length()-1);
//            pst = this.conexionBD.getConexion().prepareStatement(sql);
//            pst.executeUpdate();
//
//            this.conexionBD.getConexion().commit();
//            return idventa;
//        }
//
        return 0;
    }
}
