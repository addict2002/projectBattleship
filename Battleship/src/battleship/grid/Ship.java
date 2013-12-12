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
public class Ship {
    private int size;
    private boolean bombed;
    
    public Ship(int size){
        this.size=size;
        bombed=false;
    }
    
    public boolean bomb(){
        if(size>1){
            size--;
            bombed=false;
         }
         else{
            size=0;
            bombed=true;
         }
         return bombed;
    }
    
    public boolean getShipState(){
        return bombed;
    }
    
    public int getShipSize(){
        return size;
    }
}
