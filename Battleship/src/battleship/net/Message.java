/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleship.net;

import java.io.*;

/**
 *
 * @author Andri
 */
public class Message {
    int type;
    String textMessage;
    Object messageObject;
    
    
    Message(int type,String textMessage, Object aObject){
        this.textMessage=textMessage;
        this.type=type;
        this.messageObject=aObject;
    }
    public static Message deserialize(byte[] serializedMessage) throws IOException, ClassNotFoundException{
        ByteArrayInputStream inputStream = new ByteArrayInputStream(serializedMessage);
        ObjectInput in = null;
        try {
          in = new ObjectInputStream(inputStream);
          Message message=(Message)in.readObject();
          return message;
        } finally {
          try {
            inputStream.close();
          } catch (IOException ex) {
            // ignore close exception
          }
          try {
            if (in != null) {
              in.close();
            }
          } catch (IOException ex) {
            // ignore close exception
          }
        }
    }
    public byte[] serialize() throws IOException {
        byte[] serializedMessage;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(this);
            serializedMessage = bos.toByteArray();
            
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
            try {
                bos.close();
            } catch (IOException ex) {
                // ignore close exception
            }
        }
        return serializedMessage;
    }
}
