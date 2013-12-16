/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleship.net;

import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andri
 */

public class Connection {
    MailBox mailbox;
    boolean running;
    private boolean isServer;
    
    public boolean isIsServer() {
        return isServer;
    }
    private Server server;
    private Client client;
    
    public MessageListener messageListener = null;
    public MessageSender messageSender = null;
    
    Socket socket;
    InetAddress ip;
    int port;

    
    private Connection(int port){
        this.port=port;
        mailbox=new MailBox();
    }
    private Connection(int port,InetAddress ip){
        this(port);
        this.ip=ip;
    }
    
    public static Connection openConnection(int port){
        Connection aConnection=new Connection(port);
        aConnection.isServer=true;
        aConnection.server=new Server(aConnection);
        try{
            aConnection.server.runServer();
        }catch(Exception ex){
            aConnection.running=false;
        }
        return aConnection;
    }
    
    public static Connection joinConnection(int port,InetAddress iAddress){
        Connection aConnection=new Connection(port);
        aConnection.isServer=false;
      //  aConnection.client=new Client(aConnection); 
        /* change von bruno -> client erwartet keine connection sondern "werte" da ich keine ahnung habe was mit werde gemeint sind, übergebe ich null*/ 
        aConnection.client=new Client(null);
        return aConnection;
    }
    
    public synchronized void interrupt(){
        messageListener.interrupt();
        messageSender.interrupt();
    }
    
}
