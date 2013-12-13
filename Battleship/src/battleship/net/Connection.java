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
    private ArrayList<Message> outbox;
    private int outboxSize=0;

    public int getOutboxSize() {
        return outboxSize;
    }

    public int getInboxSize() {
        return inboxSize;
    }
    private ArrayList<Message> inbox;
    private int inboxSize=0;
    private Server server;
    private Client client;
    InetAddress ip;
    int port;
    private Connection(){
        this.inbox=new ArrayList<>();
        this.outbox=new ArrayList<>();
    }
    private Connection(int port){
        this();
        this.port=port;
    }
    private Connection(int port,InetAddress ip){
        this(port);
        this.ip=ip;
    }
    
    
    public static Connection openConnection(int port){
        Connection aConnection=new Connection(port);
        aConnection.server=new Server(aConnection.port);
        return aConnection;
    }
    
    public static Connection joinConnection(int port,InetAddress iAddress){
        Connection aConnection=new Connection(port);
        
        return aConnection;
    }
    
    private synchronized Message dequeueInboxMessage(){
        
        if(inbox.size()>0){
            inboxSize--;
            return inbox.remove(0);
            
        }
        return null;
    }
    private synchronized void enqueueInboxMessage(Message aMessage){
        inboxSize++;
        inbox.add(aMessage);
    }
    private synchronized Message dequeueOutboxMessage(){
        
        if(outbox.size()>0){
            outboxSize--;
            return outbox.remove(0);
        }
        return null;
    }
    private synchronized void enqueueOutboxMessage(Message aMessage){
        outboxSize++;
        outbox.add(aMessage);
    }
    
    public void sendMessage(Message aMessage){
        this.enqueueOutboxMessage(aMessage);
    }
    
    
    public void receiveMessage(Message aMessage){
        this.enqueueInboxMessage(aMessage);
    }
    
    
    
    class ConnectionHandler implements Runnable{
        private Connection aConnection;
        ConnectionHandler(Connection aConnection){
            this.aConnection=aConnection;
            
            
        }
        @Override
        public void run() {
            try {
                while (true) {
                    if (aConnection.getInboxSize() > 0) {

                    }
                    Message aMessage = aConnection.dequeueInboxMessage();

                    if (aConnection.getInboxSize() == 0 && aConnection.getOutboxSize() == 0) {

                        Thread.sleep(500);

                    }

                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}

