/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleship.net;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author Andri
 */


public class MessageSender extends Thread {
    private ArrayList<Message> mMessageQueue = new ArrayList<>();
    private MailBox mailbox;
    private Connection aConnection;
    private ObjectOutputStream mOut;
    
    public MessageSender(Connection aConnection) throws IOException {
        this.aConnection = aConnection;
        mailbox = aConnection.mailbox;
        Socket socket = aConnection.socket;
        mOut = new ObjectOutputStream(socket.getOutputStream());
    }

    /**
     *
     * Adds given message to the message queue and notifies this thread
     * (actually getNextMessageFromQueue method) that a message is arrived.
     * sendMessage is called by other threads (ServeDispatcher).
     *
     */
    
    public synchronized void sendMessage(Message aMessage) {
        mMessageQueue.add(aMessage);
        notify();
    }

    /**
     *
     * @return and deletes the next message from the message queue. If the queue
     *
     * is empty, falls in sleep until notified for message arrival by
     * sendMessage
     *
     * method.
     *
     */
    private synchronized Message getNextMessageFromQueue() throws InterruptedException {
        while (mMessageQueue.size() == 0) {
            wait();
        }
        Message message = (Message) mMessageQueue.remove(0);
        
        return message;
    }

    /**
     *
     * Sends given message to the client's socket.
     *
     */
    private void sendMessageToClient(Message aMessage) throws IOException  {
        
        mOut.writeObject(aMessage);
        mOut.flush();
    }

    /**
     *
     * Until interrupted, reads messages from the message queue
     *
     * and sends them to the client's socket.
     *
     */
    public void run() {

        try {

            while (!isInterrupted()) {

                Message message = getNextMessageFromQueue();

                sendMessageToClient(message);

            }

        } catch (Exception e) {
           // Commuication problem
        }

        // Communication is broken. Interrupt both listener and sender threads
        aConnection.interrupt();
    }

}

