/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleship.grid;

/**
 *
 * @author ceeedi
 */
public class Field {
    public Ship ship;
    
    public Field(){
        ship=null;
    }
    
    public void addShip(Ship newShip){
        ship=newShip;
    }
    
    public boolean hasShip(){
        if(ship!=null){
            return true;
        }
        return false;
    }    
}
