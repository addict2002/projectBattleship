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
    private Ship ship;
    private int xCoordinate;
    private int yCoordinate;
    
   
    
    public Field(int x, int y){
        this.setXCoodinate(x);
        this.setYCoordinate(y);
        this.ship=null;
    }
    
    public void setXCoodinate(int x)
    {
        this.xCoordinate = x;
    }
    
    public int getXCoordinate()
    {
        return this.xCoordinate;
    }
    
    public void setYCoordinate(int y)
    {
        this.yCoordinate = y;
    }
    
    public int getYCoordiante()
    {
        return this.yCoordinate;
    }
    
    public void addShip(Ship newShip){
        this.ship=newShip;
    }
    
    public boolean hasShip(){
        if(this.ship!=null){
            return true;
        }
        return false;
    }    
}
