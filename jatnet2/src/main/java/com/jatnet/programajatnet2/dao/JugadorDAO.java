/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jatnet.programajatnet2.dao;


import com.jatnet.programajatnet2.model.Jugador;
import com.jatnet.programajatnet2.model.JugadorI;
import com.jatnet.programajatnet2.model.Producto;
import com.jatnet.programajatnet2.util.ConnectionBD;
import com.jatnet.programajatnet2.util.Sesion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class JugadorDAO {
     private ConnectionBD conexionBD;

    public JugadorDAO(ConnectionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
      public List<Jugador> getAll() throws SQLException{
        List<Jugador> lista = new ArrayList<>();
        Jugador c = null;
        ResultSet rs = conexionBD.CONSULTAR("SELECT * FROM Jugador;");
        while(rs.next()){
            c = new Jugador();
            c.setIdUsuario(rs.getInt("id_jugador"));
            c.setClave(rs.getString("clave").trim());
            c.setNombre_avatar(rs.getString("nombre_avatar").trim());
             System.out.println(rs.getString("nombre_avatar"));
            lista.add(c);
        }
        return lista;
    }
        public Jugador getJugador(String username, String password) throws SQLException {
        Jugador jugador = null;

        String sql = "SELECT * FROM Jugador WHERE nombre_avatar='"+username+"' AND clave='"+password+"' ";
        ResultSet rs = conexionBD.CONSULTAR(sql);
        if (rs.next()) {
            jugador = new Jugador();
            jugador.setIdUsuario(rs.getInt("id_jugador"));
            jugador.setClave(password);
            jugador.setNombre_avatar(username);
            
            jugador.setCorreo(rs.getString("correo"));
            jugador.setNacionalidad(rs.getString("nacionalidad"));
            jugador.setColor(rs.getString("color"));
            //jugador.setNombre("nombre");
            Sesion.getSesion(jugador);
        }

        return jugador;
    }
        
         public List<JugadorI> getJugador2() throws SQLException {
        List<JugadorI> productos = new ArrayList<>();

        JugadorI p = null;
        ResultSet rs = conexionBD.CONSULTAR("SELECT * FROM Jugador");
        while (rs.next()) {
            p = new JugadorI();

            p.setIdcliente(rs.getInt("id_jugador"));
            p.setClave(rs.getString("clave").trim());
            p.setNombre_avatar(rs.getString("nombre_avatar").trim());
            p.setCorreo(rs.getString("correo").trim());
            p.setNacionalidad(rs.getString("nacionalidad").trim());
            p.setColor(rs.getString("color").trim());
            // p.setNombre(rs.getString("nombre").trim());
            //p.setFechadevencimiento(rs.getDate("fechadevencimiento").toLocalDate());

            productos.add(p);
        }

        return productos;
    }
          public boolean delete(int id_jugador) throws SQLException {
        String sql = "DELETE FROM Jugador WHERE id_jugador="+id_jugador;
        return conexionBD.GUARDAR(sql);
    }
        
        
        
         public int guardar(JugadorI c) throws SQLException {

        String sql = null;
        if (c.getIdJugador() == 0) {
            sql = "INSERT INTO Jugador(\n"
                    + "	clave, nombre_avatar, correo, nacionalidad, color)\n"
                    + "	VALUES ('"+c.getClave()+"', '"+c.getNombre_avatar()+"', '"+c.getCorreo()+"', '"+c.getNacionalidad()+"', '"+c.getColoar()+"' );";
        } else {
            sql = "UPDATE Jugador\n" +
                    "	SET clave='"+c.getClave()+"', nombre_avatar='"+c.getNombre_avatar()+"', correo='"+c.getCorreo()+"', "
                    + "nacionalidad='"+c.getNacionalidad()+"', color='"+c.getColoar()+"'\n" +
                    "WHERE id_jugador="+c.getIdJugador()+";";
        }
        System.out.println(sql);
        //this.conexionBD.GUARDAR(sql);
        //System.out.println(this.conexionBD.getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS));
        
       //PreparedStatement pst = this.conexionBD.getConexion().prepareStatement(sql);
                //this.conexionBD.getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                
                
                        
        PreparedStatement pst = this.conexionBD.getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
               
        
        
        int n = pst.executeUpdate();
        if (c.getIdJugador() == 0) {
            ResultSet rs = pst.getGeneratedKeys();
            rs.next();
            n = rs.getInt(1);
        }
        return n;
    }
   public JugadorI getById(int id_jugador) throws SQLException {
        JugadorI p = null;
        ResultSet rs = this.conexionBD.CONSULTAR("SELECT * FROM Jugador WHERE id_jugador="+id_jugador+";");
        if(rs.next()){
            p = new JugadorI();
            p.setIdcliente(id_jugador);
            p.setClave(rs.getString("clave").trim());
            p.setColor(rs.getString("color").trim());
            p.setCorreo(rs.getString("correo").trim());
            p.setNacionalidad(rs.getString("nacionalidad").trim());
            p.setNombre_avatar(rs.getString("nombre_avatar").trim());
           // p.setFechadevencimiento(rs.getDate("fechadevencimiento").toLocalDate());
        }
        System.out.println("-------");
        System.out.println(p.getNombre_avatar());
        return p;
    }
}
