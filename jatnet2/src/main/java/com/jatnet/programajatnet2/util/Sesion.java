/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jatnet.programajatnet2.util;
import com.jatnet.programajatnet2.model.Jugador;
import com.jatnet.programajatnet2.model.Usuario;
/**
 *
 * @author ken
 */
public class Sesion {
//private static Usuario usuario;
//
//    public static Usuario getSesion(Usuario user) {
//        if (usuario == null) {
//            usuario = user;
//        }
//        return usuario;
//    }    
    private static Jugador jugador;
    public static Jugador getSesion(Jugador user){
       if (jugador == null) {
            jugador = user;
       }
      return jugador;
    }
}
