/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formidesktop.database.acciones;

import formidesktop.MainFrame;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 *
 * @author Hector Torres
 */
public class ServerConnection extends Thread{
    
    private Parser request;
    private Parser response;
    private AccionResultadoConexion arc;
    private String statusMessage;
    
    public ServerConnection(Parser request, AccionResultadoConexion arc){
        this.request = request;
        this.arc = arc;
        this.statusMessage = "";
    }
    
    public interface AccionResultadoConexion{
        void accionPositiva(Thread t);
        void accionNegativa(Thread t);
    }
    
    public Parser getResponse(){
        return response;
    }
    
    public String getStatusMessage(){
        return statusMessage;
    }
    
    @Override
    public void run(){
        Socket socket;
        try{
            socket = new Socket(MainFrame.HOST, 23543);
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            IOHandler ioHandler = new IOHandler(entrada, salida);
            ioHandler.sendMessage(URLEncoder.encode(request.getSerializedMessage(), "UTF8").getBytes());
            response = new Parser(URLDecoder.decode(new String(ioHandler.handleIncommingMessage()), "UTF8"));
            if(response.getBoolean("status")){
                arc.accionPositiva(this);
            }else{
                arc.accionNegativa(this);
            }
        }catch(IOException e){
            statusMessage = e.getMessage();
            arc.accionNegativa(this);
        }
    }
}
