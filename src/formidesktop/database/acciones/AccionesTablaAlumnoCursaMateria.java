/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formidesktop.database.acciones;

import formidesktop.database.DatabaseConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    
    public static boolean esRecurse(String boleta, String ua){
        boolean esRecurse = false;
        DatabaseConnection db = new DatabaseConnection();
        try{
            Connection con = db.getConnection();
            PreparedStatement pstmnt = con.prepareStatement("select isRecurse "
                    + "from Alumno_Cursa_Unidad_Aprendizaje where boleta "
                    + "like ? and idUnidad_Aprendizaje like ?");
            pstmnt.setString(1, boleta);
            pstmnt.setString(2, ua);
            ResultSet rs = pstmnt.executeQuery();
            while(rs.next()){
                esRecurse = rs.getInt(1) != 0;
            }
            db.closeConnection();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return esRecurse;
    }
    
    public static boolean cursaUnidadAprendizaje(String boleta, String ua){
        boolean cursaUA = false;
        DatabaseConnection db = new DatabaseConnection();
        try{
            Connection con = db.getConnection();
            PreparedStatement pstmnt = con.prepareStatement("select count(*) "
                    + "from Alumno_Cursa_Unidad_Aprendizaje where boleta like "
                    + "? and idUnidad_Aprendizaje like ?");
            pstmnt.setString(1, boleta);
            pstmnt.setString(2, ua);
            ResultSet rs = pstmnt.executeQuery();
            if(rs.next())
                cursaUA = rs.getInt(1) != 0;
            db.closeConnection();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return cursaUA;
    }
}
