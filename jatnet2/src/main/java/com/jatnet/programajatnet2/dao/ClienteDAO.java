/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jatnet.programajatnet2.dao;

import com.jatnet.programajatnet2.util.ConnectionBD;
import com.jatnet.programajatnet2.model.Cliente;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ken
 */
public class ClienteDAO {
    private ConnectionBD conexionBD;
    public ClienteDAO(ConnectionBD conexionBD){
        this.conexionBD = conexionBD;
    }
    public int guardar(Cliente c) throws SQLException {

        String sql = null;
        if (c.getIdcliente() == 0) {
            sql = "INSERT INTO cliente(\n"
                    + "	nombreCliente, apellidoCliente, direccioncliente, telefonocliente, numerodocumento)\n"
                    + "	VALUES ('"+c.getNombrecliente()+"', '"+c.getApellidocliente()+"', '"+c.getDireccioncliente()+"', '"+c.getTelefonocliente()+"', '"+c.getNumerodocumento()+"');";
        } else {
            sql = "UPDATE cliente\n" +
                    "	SET nombreCliente='"+c.getNombrecliente()+"', apellidoCliente='"+c.getApellidocliente()+"', direccioncliente='"+c.getDireccioncliente()+"', "
                    + "telefonocliente="+c.getTelefonocliente()+"', numerodocumento='"+c.getNumerodocumento()+"'\n" +
                    "	WHERE idcliente="+c.getIdcliente()+";";
        }
        System.out.println(sql);
                         
        PreparedStatement pst = this.conexionBD.getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        int n = pst.executeUpdate();
        if (c.getIdcliente() == 0) {
            ResultSet rs = pst.getGeneratedKeys();
            rs.next();
            n = rs.getInt(1);
        }
     
        return n;
    }

    public List<Cliente> getAll() throws SQLException{
        List<Cliente> lista = new ArrayList<>();
        Cliente c = null;
        ResultSet rs = this.conexionBD.CONSULTAR("SELECT idcliente, nombrecliente, apellidocliente FROM cliente;");
        while(rs.next()){
            c = new Cliente();
            c.setIdcliente(rs.getInt("idcliente"));
            c.setNombrecliente(rs.getString("nombrecliente").trim());
            c.setApellidocliente(rs.getString("apellidocliente").trim());

            lista.add(c);
        }
        return lista;
    }
}
