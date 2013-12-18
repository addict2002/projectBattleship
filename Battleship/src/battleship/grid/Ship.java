/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleship.grid;

/**
 *
 * @author Andri
 */
public class Ship {
    public int shipLength;
    public int bombsOnShip=0;
    public boolean isShipPlaced;
    public Ship(int shipLength){
        this.shipLength=shipLength;
    }
    public boolean isDestroyed(){
        if(bombsOnShip>=shipLength){
            return true;
        }
        return false;
    }
    public boolean receiveBomb(){
        bombsOnShip++;
        if(bombsOnShip>=shipLength){
            return true;
        }
        return false;
    }
    
    public void resetShip(){
        this.isShipPlaced=false;
        this.bombsOnShip=0;
    }
    
    public static Ship[] getShipsTemplate(){
        Ship[] ships=new Ship[10];
        for(int i=0;i<4;i++){
            ships[i]=new Ship(2);
        }
        for(int i=4;i<7;i++){
            ships[i]=new Ship(3);
        }
        ships[7]=new Ship(4);
        ships[8]=new Ship(4);
        ships[9]=new Ship(5);
        return ships;
    }
    
}
