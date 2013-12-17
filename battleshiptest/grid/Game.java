/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleshiptest.grid;

import battleshiptest.gui.IView;
import battleshiptest.oponent.IOponent;
import java.util.ArrayList;

/**
 *
 * @author Andri
 */
public class Game {
    ArrayList<IView>  views;
    private static Game theGame;
    
    private int gameState=0; //0:initializing,1: placing ships, 2:playing, 3: game over, 99: error
    private boolean playOnline=true;
    
    private String error;
    
    Player player;
    Grid oponentGrid;
    
    
    public int getGameState() {
        return gameState;
    }
    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public boolean isPlayOnline() {
        return playOnline;
    }

    public void setPlayOnline(boolean playOnline) {
        this.playOnline = playOnline;
    }
    
    
    private Game(){
        gameState=0;
        views=new ArrayList<>();
        this.player=new Player();
        oponentGrid=new Grid();
    }
    
    public static Game getTheGame(){
        if(theGame==null){
            theGame=new Game();
        }
        return theGame;
    }
    public void startGame(){
        
    }
    
    public void addView(IView view){
        this.views.add(view);
        
    }
    public void updateViews(){
        for(int i=0;i<views.size();i++){
            IView view=views.get(i);
            view.updateView();
        }
    }
    
    
}
