/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleship.grid;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ceeedi
 */
public class Grid {
   // private List<Field> grid = new LinkedList<Field>();
    private Ship[][] grid;// = new int[10][10];
    
    
    private ArrayList<Ship> shipList = new ArrayList<Ship>();
    private final int MAX_SHIP_COUNT = 10;
    
    public Grid(int xSize, int ySize){
        //grid = new Field[xSize][ySize];        
        grid = new Ship[10][10];
    }
    
    /* change von bruno*/
    public void addShipToGrid(int direction, FieldPoint point , Ship ship){
        int i;
        int size;
        
        int xCord = point.getXCoordiante();
        int yCord = point.getYCoordinate();
        
        size = ship.getShipSize();
        
        /*change von bruno */
        /* das shiff wird zuerst in die shipList eingetragen, da wird geprüft das nur die maximale Anzahl schiffe plaziert werden kann */
        
        if(addShipToList(ship))
        {
            //add vertiacal ship
            if(direction==0){
                for(i=0;i<size;i++){
   //                grid[xCord][yCord].ship=ship;
                    grid[xCord][yCord] = ship;
                    yCord++;
                }
            }
            //add horizontal ship
            else{
                for(i=0;i<size;i++){
                   // grid[xCord][yCord].ship=ship;
                    grid[xCord][yCord] = ship;
                    xCord++;
                }
            }
            
            System.out.println("Ship added to grid, shipSize: " + size);
        }
        else
        {
            System.out.println("Ship not added to grid");
        }
    }
  
    /*change von bruno */
    /* funktion welche shiffe in die Liste einträgt, maximal MAX_SHIP_COUNT */
    
    //adds ships to shipList
    private boolean addShipToList(Ship ship)
    {
        boolean isShipAdded = false;
        if(shipList.size() <= MAX_SHIP_COUNT)    
        {
            shipList.add(ship);
            isShipAdded = true;
        }
        return isShipAdded;
    }

}

