/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.gui;

/**
 *
 * @author Sandro
 */
public class GuiGrid {
    
    private static boolean [] field;
    private static int ships;
    private static int shipsleft;
    
    public GuiGrid(){
        ships = 30;
        shipsleft = 30;
        field = new boolean[100];
        
    }
    public void setShip(int f, boolean b){
        field[f] = b;
    }
    public static boolean getShip(int f){
        boolean isShip;
        isShip = field[f];
        return(isShip);
    }
    public boolean isWon(){
        boolean iswon;
        if(shipsleft==0){
            iswon = true;
        }
        else
            iswon = false;
     return(iswon);       
    }
    public void incShip(){
        shipsleft--;
    }
}
