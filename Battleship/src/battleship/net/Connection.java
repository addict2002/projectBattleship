/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleship.net;

import battleship.oponent.*;
import java.net.*;


/**
 *
 * @author Andri
 */

public class Connection {
    public NetOponent oponent;
    protected MailBox mailbox;
    boolean running;
    boolean online;
    private boolean isServer;
    
    public boolean isIsServer() {
        return isServer;
    }
    private Server server;
    private Client client;
    
    public MessageListener messageListener = null;
    public MessageSender messageSender = null;
    private MessageProcessor messageProcessor=null;
    
    Socket socket;
    InetAddress ip;
    int port;

    
    private Connection(int port){
        online=false;
        this.port=port;
        mailbox=new MailBox();
    }
    private Connection(int port,InetAddress ip){
        this(port);
        this.ip=ip;
    }
    
    public static Connection openConnection(int port){
        Connection aConnection=new Connection(port);
        aConnection.messageProcessor=new MessageProcessor(aConnection);
        aConnection.messageProcessor.start();
        
        aConnection.isServer=true;
        aConnection.server=new Server(aConnection);
        try{
            aConnection.server.start();
        }catch(Exception ex){
            aConnection.running=false;
        }
        return aConnection;
    }
    
    public static Connection joinConnection(int port,InetAddress ip){
        Connection aConnection=new Connection(port,ip);
        aConnection.messageProcessor=new MessageProcessor(aConnection);
        aConnection.messageProcessor.start();
        aConnection.isServer=false;
        aConnection.client=new Client(aConnection);
        try{
            aConnection.client.start();
        }catch(Exception ex){
            aConnection.running=false;
        }
        return aConnection;
    }
    
    public void sendMessage(Message message){
        this.mailbox.sendMessage(message);
        
    }
    
    
    public synchronized void interrupt(){
        online=false;
        messageListener.interrupt();
        messageSender.interrupt();
        messageProcessor.interrupt();
    }
    
    
    
    
}
