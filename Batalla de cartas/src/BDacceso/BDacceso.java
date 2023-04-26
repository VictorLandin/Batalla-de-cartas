/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BDacceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author a22victorlr
 */
public class BDacceso {

    /**
     *
     * @param id
     * @param nombre
     */
    public void acceder(int id, String nombre){
    try {
            
            String mysqlUrl = "jdbc:mysql://localhost/batalla";
            String usuario = "root";
            String clave = "root";

            Connection mysqlCon = DriverManager.getConnection(mysqlUrl, usuario, clave);
            Statement stmt = mysqlCon.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT * from " + nombre + "");
            
            while (rs.next()) {
                System.out.println("Nombre: " + rs.getString("NOMBRE"));
                System.out.println("NIF: " + rs.getString("NIF"));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace( );
        }
    }
}
