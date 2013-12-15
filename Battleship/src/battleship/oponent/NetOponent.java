/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleship.oponent;

import battleship.net.*;
import battleship.grid.*;
import battleship.net.*;


/**
 *
 * @author Andri
 */
public class NetOponent implements IOponent{
    Connection aConnection;
    
    NetOponent(Connection aConnection){
        this.aConnection=aConnection;        
    }
    
    public void init(){
        this.aConnection.oponent=this;
        
        
    }
    
    
    @Override
    public void sendBomb(Bomb aBomb) {
        this.aConnection.sendMessage(new MsgBomb(aBomb));
    }
    
    @Override
    public void sendBombReport(BombReport aReport){
        
        this.aConnection.sendMessage(new MsgBombReport(aReport));
    }
    
    @Override
    public Bomb receiveBomb(Bomb aBomb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BombReport receiveBombReport(BombReport aReport) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
