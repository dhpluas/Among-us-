/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jatnet.programajatnet2.util;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ken
 */
public class ConnectionBD {

 
    
    private static java.sql.Connection con;
       //java.sql.Connection con;
       private Statement statement;
       static int lport;
       static String rhost;
       static int rport;
    // Declaramos los datos de conexion a la bd
    private static final String driver="com.mysql.cj.jdbc.Driver";
    
    //private static final String user="root";
    private static final String dbUser="root";
    
    private static final String pass="admin";
    private static final String url="jdbc:mysql://127.0.1.1:3306/among_us";
    // Funcion que va conectarse a mi bd de mysql
    public Connection conector() throws SQLException {
//        // Reseteamos a null la conexion a la bd
//        con=null;
//        try{
//            Class.forName(driver);
//            // Nos conectamos a la bd
//          
//           // con = (ConnectionBD) DriverManager.getConnection(url, user, pass);
//            con = (Connection)  DriverManager.getConnection(url, user, pass);
//            statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//            // Si la conexion fue exitosa mostramos un mensaje de conexion exitosa
//            if (con!=null){
//                System.out.println("Conexion establecida");
//
//            }
//        }
//        // Si la conexion NO fue exitosa mostramos un mensaje de error
//        catch (ClassNotFoundException | SQLException e){
//            System.out.println("Error de conexion....." + e);
//        }
//       return con; 
   String user = "pedro";
        String password = "Yosoypegaso2";
        String host = "20.51.247.114";
        int port=446;
        try
            {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            lport = 3307;
            rhost = "localhost";
            rport = 3306;
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            System.out.println("Establishing Connection...");
            session.connect();
            int assinged_port=session.setPortForwardingL(lport, rhost, rport);
            System.out.println("localhost:"+assinged_port+" -> "+rhost+":"+rport);
            }
        catch(Exception e){System.err.print(e);}
                  System.out.println("An example for updating a Row from Mysql Database!");
           con = null;
          //String driver = "com.mysql.cj.jdbc.Driver";
          String url = "jdbc:mysql://" + rhost +":" + lport + "/";
          String db = "admin_amoung";
          String dbUser = "admin_impostor";
          String dbPasswd = "121212";
        
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
          con = (Connection) DriverManager.getConnection(url+db, dbUser, dbPasswd);
          
          statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
       
               if (con!=null){
                System.out.println("Conexion establecida");

            }                
          
                  
         
           return con; 
    }
      
         public ResultSet CONSULTAR(String sql) throws SQLException {
        ResultSet rs = null;
        rs = statement.executeQuery(sql);
        System.out.println(sql);
        return rs;
    }
           public boolean GUARDAR(String sql) throws SQLException {
        return (statement.executeUpdate(sql) > 0);
    }
   public void CERRAR() {
        try {
            if(con != null){
                con.close();
            }
            if(statement != null){
                statement.close();
            }
            System.out.println("CONEXION CERRADA");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       public static void main(String[] args) throws SQLException {
        ConnectionBD aplicacion = new ConnectionBD();
                 aplicacion.conector();
    }
   
    
    public java.sql.Connection getConexion() {
        return con;

    }  
}
