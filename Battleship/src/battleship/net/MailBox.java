/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleship.net;

import java.util.ArrayList;

/**
 *
 * @author Andri
 */
public class MailBox {
    
    private ArrayList<Message> outbox;
    private int outboxSize=0;

    public int getOutboxSize() {
        return outboxSize;
    }

    public int getInboxSize() {
        return inboxSize;
    }
    private int inboxSize=0;
    private ArrayList<Message> inbox;
    
    public MailBox(){
        this.inbox=new ArrayList<>();
        this.outbox=new ArrayList<>();
    }
    
    
    public synchronized Message dequeueInboxMessage(){
        
        if(inbox.size()>0){
            inboxSize--;
            return inbox.remove(0);
            
        }
        return null;
    }
    private synchronized void enqueueInboxMessage(Message aMessage){
        inboxSize++;
        inbox.add(aMessage);
        notifyAll();
    }
    public synchronized Message dequeueOutboxMessage(){
        
        if(outbox.size()>0){
            outboxSize--;
            return outbox.remove(0);
        }
        return null;
    }
    private synchronized void enqueueOutboxMessage(Message aMessage){
        outboxSize++;
        outbox.add(aMessage);
        notifyAll();
    }
    
    public void sendMessage(Message aMessage){
        this.enqueueOutboxMessage(aMessage);
    }
    
    
    public void receiveMessage(Message aMessage){
        this.enqueueInboxMessage(aMessage);
    }
    
    
}
