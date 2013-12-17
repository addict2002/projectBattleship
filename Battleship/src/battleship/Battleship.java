/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleship;

import battleship.engine.Engine;
import battleship.gui.ModeSelector;
import battleship.gui.Play;
import battleship.gui.ShipPlacement;
import java.io.IOException;

/**
 *
 * @author bruno
 */
public class Battleship {

    /**
     * @param args the command line arguments
     */
    
    public Engine gameEngine;
    
    private ModeSelector screenModeSelector;
    private ShipPlacement screenShipPlacement;
    private Play screenPlay;
    
    public Battleship()
    {
        this.screenModeSelector = new ModeSelector(this);
    }
    
    public void createHostEngine() throws IOException
    {
        this.gameEngine = Engine.createHostEnginge();
        //this.gameEngine.conn.messageListener.start();
        
        //this.screenModeSelector.setVisible(false);
        //this.screenShipPlacement = new ShipPlacement(this);
    }
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        //Some Change
       
       Battleship game = new Battleship();
       //game.screenModeSelector.setVisible(true);
       
       // Engine gameEngine = Engine.createHostEnginge();
       
       //  System.out.println("Current State of the Game: " + gameEngine.currentGameState);
       
        
    }

   
    
}
