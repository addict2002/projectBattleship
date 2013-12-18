/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleship.grid;

import battleship.engine.Engine;
import battleship.gui.IView;
import java.util.ArrayList;

/**
 *
 * @author Andri
 */
public class Game {
    
    private static Game theGame;
    
    private int gameState=0; //0:initializing, placing ships, 2:playing, 3: game over, 99: error
    private boolean playOnline=true;
    
    public String error;
    
    public Player player;
    
    
    public int getGameState() {
        return gameState;
    }
    public void setGameState(int gameState) {
        this.gameState = gameState;
        Engine.getEngine().gameStateChanged();
    }

    public boolean isPlayOnline() {
        return playOnline;
    }

    public void setPlayOnline(boolean playOnline) {
        this.playOnline = playOnline;
    }
    
    public void initPlayer(){
        player=new Player();
    }
    private Game(){
        gameState=0;
        initPlayer();
    }
    
    public static Game getTheGame(){
        if(theGame==null){
            theGame=new Game();
        }
        return theGame;
    }
    public void startGame(){
        
    }
    public void handleError(String strError){
        error=strError;
        System.out.println(error);
    }
}
