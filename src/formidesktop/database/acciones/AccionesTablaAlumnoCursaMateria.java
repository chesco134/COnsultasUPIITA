/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formidesktop.database.acciones;

import formidesktop.database.DatabaseConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author jcapiz
 */
public class AccionesTablaAlumnoCursaMateria {
    
    
    public static void actualizaRecurse(String boleta, String ua, boolean esRecurse){
        DatabaseConnection db = new DatabaseConnection();
        try{
            Connection con = db.getConnection();
            CallableStatement cstmnt = con.prepareCall("{call actualizaRecurse(?,?,?)}");
            cstmnt.setString(1, boleta);
            cstmnt.setString(2, ua);
            cstmnt.setBoolean(3, esRecurse);
            cstmnt.executeUpdate();
            con.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void insertaAlumnoCursaUnidadDeAprendizaje(String boleta, String ua, boolean esRecurse){
        DatabaseConnection db = new DatabaseConnection();
        try{
            try (Connection con = db.getConnection()) {
                CallableStatement cstmnt =
                        con.prepareCall("{call insertaAlumno_Cursa_Unidad_Aprendizaje(?,?,?)}");
                cstmnt.setString(1, boleta);
                cstmnt.setString(2, ua);
                cstmnt.setBoolean(3, esRecurse);
                cstmnt.executeUpdate();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void eliminaAlumnoCursaUnidadDeAprendizaje(String boleta, String ua){
        DatabaseConnection db = new DatabaseConnection();
        try{
            Connection con = db.getConnection();
            CallableStatement cstmnt = 
                    con.prepareCall("{call eliminaAlumno_Cursa_Unidad_Aprendizaje(?,?)}");
            cstmnt.setString(1, boleta);
            cstmnt.setString(2, ua);
            cstmnt.executeUpdate();
            con.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
