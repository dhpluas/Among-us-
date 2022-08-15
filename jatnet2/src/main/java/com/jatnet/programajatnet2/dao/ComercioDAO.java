/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jatnet.programajatnet2.dao;

import com.jatnet.programajatnet2.model.Comercio;
import com.jatnet.programajatnet2.util.ConnectionBD;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ken
 */
public class ComercioDAO {
    private ConnectionBD conexionBD = new ConnectionBD();

    public ComercioDAO() {
    }

    public Comercio getComercio() throws SQLException {
        Comercio c = new Comercio();
        this.conexionBD.conector();
        ResultSet rs = this.conexionBD.CONSULTAR("SELECT * FROM comercio;");
        if (rs.next()) {
            c.setIdcomercio(rs.getInt("idcomercio"));
            c.setNombre(rs.getString("nombre"));
            c.setNit(rs.getString("nit"));
            c.setDireccion(rs.getString("direccion"));
            c.setTelefono(rs.getString("telefono"));
            c.setIva(rs.getInt("iva"));
        }
        this.conexionBD.CERRAR();
        return c;
}}
