/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleshiptest.oponent;

import battleshiptest.engine.*;
import battleshiptest.engine.actions.*;
import battleshiptest.net.*;
import battleshiptest.grid.*;
import battleshiptest.net.*;


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
    public void sendBombToOponent(Bomb aBomb) {
        this.aConnection.sendMessage(new MsgBomb(aBomb));
    }
    
    @Override
    public void sendBombReportToOponent(BombReport aReport){
        
        this.aConnection.sendMessage(new MsgBombReport(aReport));
    }
    
    @Override
    public void bombFromOponent(Bomb aBomb) {
        Engine engine = Engine.getEngine();
        engine.pushAction(new ActReceiveBomb(aBomb));
        
    }

    @Override
    public void bombReportFromOponent(BombReport aReport) {
        Engine engine = Engine.getEngine();
        engine.pushAction(new ActReceiveBombReport(aReport));
    }
    
    
}
