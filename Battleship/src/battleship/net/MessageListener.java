/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleship.net;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andri
 */


public class MessageListener extends Thread {
    private MailBox mailbox;
    private Connection aConnection;
    private ObjectInputStream mIn;
    
    public MessageListener(Connection aConnection) throws IOException {
        this.aConnection = aConnection;
        mailbox = aConnection.mailbox;
        Socket socket = aConnection.socket;
        mIn = new ObjectInputStream(socket.getInputStream());
    }
    /**
     * Until interrupted, reads messages from the client socket, forwards them
     * to the server dispatcher's queue and notifies the server dispatcher.
     */
    public void run() {
        try {
            while (!isInterrupted()) {
                
                // read from the stream  
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                byte buffer[] = new byte[1024];
//                for(int s; (s=mIn.read(buffer)) != -1; )
//                {
//                  baos.write(buffer, 0, s);
//                }
//                byte result[] = baos.toByteArray();
                Message message=null;
                try{
                    message=(Message)mIn.readObject();
                }catch (ClassNotFoundException ex) {
                    
                }
                
                
                if (message == null) {
                    break;
                }
                mailbox.receiveMessage(message);
            }
        } catch (IOException ioex) {
           // Problem reading from socket (communication is broken)
        } 
        // Communication is broken. Interrupt both listener and sender threads
        aConnection.interrupt();
    }
}

