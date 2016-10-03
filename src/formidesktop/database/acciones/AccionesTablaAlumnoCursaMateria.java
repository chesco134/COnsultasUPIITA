/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formidesktop.database.acciones;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author jcapiz
 */
public class AccionesTablaAlumnoCursaMateria {
    public static final String BIO = "INGENIERIA BIÓNICA";
    public static final String MEC = "INGENIERIA MECATRÓNICA";
    public static final String TEL = "INGENIERIA TELEMÁTICA";
    public static final String ISISA = "ISISA";
    
    private static PrintWriter pw;
    private static boolean bolResponse;
    private static String strResponse;
    private static String[] strArrResponse;
    
    static{
        try{
            pw = new PrintWriter(new FileWriter(new File("tac.log")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static void actualizaRecurse(final String boleta, final String ua, final boolean esRecurse){
        Parser request = new Parser();
        request.addInt("action", 2);
        request.addString("boleta", boleta);
        request.addString("unidad_aprendizaje", ua);
        request.addBoolean("es_recurse", esRecurse);
        ServerConnection sc = new ServerConnection(request, new ServerConnection.AccionResultadoConexion() {
            @Override
            public void accionPositiva(Thread t) {
                String message = "Recurse actualizado: " + boleta + ", " + ua + ", " + esRecurse;
                System.out.println(message);
                pw.println(message);
            }

            @Override
            public void accionNegativa(Thread t) {
                String message = ((ServerConnection) t).getStatusMessage();
                if("".equals(message))
                    message = "Bad action";
                System.out.println(message);
                pw.println(message);
            }
        });
        sc.start();
    }
    
    public static void insertaAlumnoCursaUnidadDeAprendizaje(String boleta, String ua, boolean esRecurse){
        Parser request = new Parser();
        request.addInt("action", 1);
        request.addString("boleta", boleta);
        request.addString("unidad_aprendizaje", ua);
        request.addBoolean("es_recurse", esRecurse);
        ServerConnection sc = new ServerConnection(request, new ServerConnection.AccionResultadoConexion() {
            @Override
            public void accionPositiva(Thread t) {
                String message = "Unidad de aprendizaje insertada: " + boleta + ", " + ua + ", " + esRecurse;
                System.out.println(message);
                pw.println(message);
            }

            @Override
            public void accionNegativa(Thread t) {
                String message = ((ServerConnection) t).getStatusMessage();
                if("".equals(message))
                    message = "Bad action";
                System.out.println(message);
                pw.println(message);
            }
        });
        sc.start();
    }
    
    public static void eliminaAlumnoCursaUnidadDeAprendizaje(String boleta, String ua){
        Parser request = new Parser();
        request.addInt("action", 3);
        request.addString("boleta", boleta);
        request.addString("unidad_aprendizaje", ua);
        ServerConnection sc = new ServerConnection(request, new ServerConnection.AccionResultadoConexion() {
            @Override
            public void accionPositiva(Thread t) {
                String message = "Unidad de aprendizaje eliminada: " + boleta + ", " + ua;
                System.out.println(message);
                pw.println(message);
            }

            @Override
            public void accionNegativa(Thread t) {
                String message = ((ServerConnection) t).getStatusMessage();
                if("".equals(message))
                    message = "Bad action";
                System.out.println(message);
                pw.println(message);
            }
        });
        sc.start();
    }
    
    public static boolean esRecurse(String boleta, String ua){
        Parser request = new Parser();
        request.addInt("action", 4);
        request.addString("boleta", boleta);
        request.addString("unidad_aprendizaje", ua);
        ServerConnection sc = new ServerConnection(request, new ServerConnection.AccionResultadoConexion() {
            @Override
            public void accionPositiva(Thread t) {
                Parser response = ((ServerConnection)t).getResponse();
                bolResponse = response.getBoolean("es_recurse");
                String message = "Solicitud de estado de recurse: " + boleta + ", " + ua + ", " + bolResponse;
                System.out.println(message);
                pw.println(message);
            }

            @Override
            public void accionNegativa(Thread t) {
                bolResponse = false;
                String message = ((ServerConnection) t).getStatusMessage();
                if("".equals(message))
                    message = "Bad action";
                System.out.println(message);
                pw.println(message);
            }
        });
        sc.start();
        try{
            sc.join();
        }catch(InterruptedException ignioe){}
        return bolResponse;
    }
    
    public static boolean cursaUnidadAprendizaje(String boleta, String ua){
        Parser request = new Parser();
        request.addInt("action", 5);
        request.addString("boleta", boleta);
        request.addString("unidad_aprendizaje", ua);
        ServerConnection sc = new ServerConnection(request, new ServerConnection.AccionResultadoConexion() {
            @Override
            public void accionPositiva(Thread t) {
                Parser response = ((ServerConnection)t).getResponse();
                bolResponse = response.getBoolean("cursa_unidad_aprendizaje");
                String message = "Solicitud de estado de curse de ua: " + boleta + ", " + ua + ", " + bolResponse;
                System.out.println(message);
                pw.println(message);
            }

            @Override
            public void accionNegativa(Thread t) {
                bolResponse = false;
                String message = ((ServerConnection) t).getStatusMessage();
                if("".equals(message))
                    message = "Bad action";
                System.out.println(message);
                pw.println(message);
            }
        });
        sc.start();
        try{
            sc.join();
        }catch(InterruptedException ignioe){}
        return bolResponse;
    }
    
    public static boolean existeBoleta(String boleta){
        Parser request = new Parser();
        request.addInt("action", 6);
        request.addString("boleta", boleta);
        ServerConnection sc = new ServerConnection(request, new ServerConnection.AccionResultadoConexion() {
            @Override
            public void accionPositiva(Thread t) {
                Parser response = ((ServerConnection)t).getResponse();
                bolResponse = response.getBoolean("existe_boleta");
                String message = "Solicitud de existencia de boleta: " + boleta + ", " + bolResponse;
                System.out.println(message);
                pw.println(message);
            }

            @Override
            public void accionNegativa(Thread t) {
                bolResponse = false;
                String message = ((ServerConnection) t).getStatusMessage();
                if("".equals(message))
                    message = "Bad action";
                System.out.println(message);
                pw.println(message);
            }
        });
        sc.start();
        try{
            sc.join();
        }catch(InterruptedException ignioe){}
        return bolResponse;
    }
    
    public static String carrera (String boleta){
        Parser request = new Parser();
        request.addInt("action", 7);
        request.addString("boleta", boleta);
        ServerConnection sc = new ServerConnection(request, new ServerConnection.AccionResultadoConexion() {
            @Override
            public void accionPositiva(Thread t) {
                Parser response = ((ServerConnection)t).getResponse();
                strResponse = response.getString("carrera");
                String message = "Solicitud de carrera: " + boleta + ", " + strResponse;
                System.out.println(message);
                pw.println(message);
            }

            @Override
            public void accionNegativa(Thread t) {
                strResponse = "";
                String message = ((ServerConnection) t).getStatusMessage();
                if("".equals(message))
                    message = "Bad action";
                System.out.println(message);
                pw.println(message);
            }
        });
        sc.start();
        try{
            sc.join();
        }catch(InterruptedException ignioe){}
        return strResponse;
    }
    
    public static String obtenerNombre(String boleta){
        Parser request = new Parser();
        request.addInt("action", 8);
        request.addString("boleta", boleta);
        ServerConnection sc = new ServerConnection(request, new ServerConnection.AccionResultadoConexion() {
            @Override
            public void accionPositiva(Thread t) {
                Parser response = ((ServerConnection)t).getResponse();
                strResponse = response.getString("nombre");
                String message = "Solicitud de nombre: " + boleta + ", " + strResponse;
                System.out.println(message);
                pw.println(message);
            }

            @Override
            public void accionNegativa(Thread t) {
                strResponse = "";
                String message = ((ServerConnection) t).getStatusMessage();
                if("".equals(message))
                    message = "Bad action";
                System.out.println(message);
                pw.println(message);
            }
        });
        sc.start();
        try{
            sc.join();
        }catch(InterruptedException ignioe){}
        return strResponse;
    }
    
    public static String[] obtenerMaterias(String boleta){
        Parser request = new Parser();
        request.addInt("action", 9);
        request.addString("boleta", boleta);
        ServerConnection sc = new ServerConnection(request, new ServerConnection.AccionResultadoConexion() {
            @Override
            public void accionPositiva(Thread t) {
                Parser response = ((ServerConnection)t).getResponse();
                strArrResponse = response.getStringArray("materias");
                String message = "Solicitud de materias: " + boleta;
                System.out.println(message);
                pw.println(message);
            }

            @Override
            public void accionNegativa(Thread t) {
                strResponse = "";
                String message = ((ServerConnection) t).getStatusMessage();
                if("".equals(message))
                    message = "Bad action";
                System.out.println(message);
                pw.println(message);
            }
        });
        sc.start();
        try{
            sc.join();
        }catch(InterruptedException ignioe){}
        return strArrResponse;
    }
    
}
