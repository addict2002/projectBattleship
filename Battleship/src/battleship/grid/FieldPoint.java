/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleship.grid;

/**
 *
 * @author bruno
 */
public class FieldPoint {
 
    private int xCoordinate;
    private int yCoordinate;
    
    
    public FieldPoint(int x, int y)
    {
        this.setXCoordinate(x);
        this.setYCoordinate(y);
    }
    
    public void setXCoordinate(int x)
    {
        this.xCoordinate = x;
    }
    
    public int getXCoordiante()
    {
        return this.xCoordinate;
    }
    
    public void setYCoordinate(int y)
    {   
        this.yCoordinate = y;
    }
    
    public int getYCoordinate()
    {
        return this.yCoordinate;
    }
    
    public static FieldPoint convertIndexToPoint(int p)
    {
        int xCoordinate; 
        int yCoordinate;
        
        xCoordinate = p % 10;
        yCoordinate = p / 10;
        
        FieldPoint newPoint = new FieldPoint(xCoordinate, yCoordinate);
        
        return newPoint;
    }
    
}
