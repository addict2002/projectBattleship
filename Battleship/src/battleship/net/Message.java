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
    
    Message(){
    }
    
    Message(int type,String textMessage, Object aObject){
        this.textMessage=textMessage;
        this.type=type;
        this.messageObject=aObject;
    }
}
