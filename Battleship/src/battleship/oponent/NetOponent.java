/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleship.oponent;

import battleship.grid.*;
import battleship.net.Connection;

/**
 *
 * @author Andri
 */
public class NetOponent implements IOponent{
    Connection aConnection;

    @Override
    public void sendBomb(Bomb aBomb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void sendBombReport(BombReport aReport){
        
        
    }
    
    @Override
    public Bomb receiveBomb() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BombReport receiveBombReport() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    NetOponent(){
        
    }
}
