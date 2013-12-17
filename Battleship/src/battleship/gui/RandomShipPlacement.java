/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.gui;

/**
 *
 * @author Sandro
 */
public class RandomShipPlacement {
    private int field[] = new int[100];
    private boolean isVertical;
    private int size[] = {5,4,4,3,3,3,2,2,2,2};
    
    public RandomShipPlacement(){
        for(int i = 0; i<field.length; i++){
            field[i] = i;
            
        }
            
    }
    public void setShip(int f, int s, boolean isVertical){
        
    }        
}
