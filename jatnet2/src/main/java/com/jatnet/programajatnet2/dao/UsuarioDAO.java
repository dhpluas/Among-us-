/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jatnet.programajatnet2.dao;
import com.jatnet.programajatnet2.model.Usuario;
import com.jatnet.programajatnet2.util.ConnectionBD;
import com.jatnet.programajatnet2.util.Sesion;

/**
 *
 * @author ken
 */



import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    private ConnectionBD conexionBD;

    public UsuarioDAO(ConnectionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    public Usuario getUsuario(String username, String password) throws SQLException {
        Usuario usuario = null;

        String sql = "SELECT * FROM usuario WHERE nombre='"+username+"' AND password='"+password+"' ";
        ResultSet rs = conexionBD.CONSULTAR(sql);
        if (rs.next()) {
            usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("idusuiaro"));
            usuario.setUsername(username);
            usuario.setPassword(password);
            usuario.setNombre(rs.getString("nombre"));

           // Sesion.getSesion(usuario);
        }

        return usuario;
    }  
}
