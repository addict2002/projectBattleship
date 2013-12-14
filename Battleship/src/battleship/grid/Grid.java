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
public class Grid {
    private final Field[][] grid;
    
    public Grid(int xSize, int ySize){
        grid = new Field[xSize][ySize];        
    }
    
    public void addShipToGrid(int direction, int xCord, int yCord, Ship ship){
        int i;
        int size;
        
        size=ship.getShipSize();
        
        //add vertiacal ship
        if(direction==0){
            for(i=0;i<size;i++){
                grid[xCord][yCord].ship=ship;
                yCord++;
            }
        }
        //add horizontal ship
        else{
            for(i=0;i<size;i++){
                grid[xCord][yCord].ship=ship;
                xCord++;
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Ship ship = new Ship(4);
        boolean bombed;
        
        Grid myGrid=new Grid(10, 10);
        myGrid.addShipToGrid(0, 5, 5, ship);
        if(myGrid.grid[5][5].hasShip()){
           bombed=myGrid.grid[5][5].ship.bomb();
        }
        else{
            bombed=false;
        }
        System.out.println(bombed);
    }
}

