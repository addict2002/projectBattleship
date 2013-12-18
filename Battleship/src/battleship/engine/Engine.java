/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleship.engine;

import battleship.engine.actions.*;
import battleship.grid.*;
import battleship.gui.GUIShipPlacement;
import battleship.gui.IView;
import battleship.net.*;
import battleship.oponent.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Andri
 */
public class Engine {
    private static Engine mengine;
    
    
    public static void main(String[] args){
        Engine engine=getEngine();
        
        engine.start();
    }
    
    private GUIShipPlacement guiShipPlacement;
    private final ArrayList<IEngineAction> actionList=new ArrayList<>();
    public Game game;
    public IOponent oponent;
    public Connection connection;
    public ArrayList<IView>  views;
    private Engine(){
        game=Game.getTheGame();
        views=new ArrayList<>();
    }
    public void start(){
        gameStateChanged();
        handleActions();
    }
    
    public static Engine getEngine(){
        if(mengine==null){
            mengine=new Engine();
        }
        return mengine;
    }
    private void handleActions(){
        while(true){
            try {
                IEngineAction action=waitForNextAction();
                if(action.execute()){
                    updateViews();
                }else{
                    if(action.getCounter()<100){
                        pushAction(action);
                    }else{
                        handleError("Can't execute action");
                    }
                }
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public synchronized void pushAction(IEngineAction action){
        actionList.add(action);
        notifyAll();
    }
    
    public synchronized IEngineAction waitForNextAction() throws InterruptedException{
        while(actionList.isEmpty()){
            wait();
        }
        return actionList.remove(0);
    }
    
    public void gameStateChanged(){
        switch(game.getGameState()){
            case 0:
                if(guiShipPlacement==null){
                    guiShipPlacement=new GUIShipPlacement();
                }
                guiShipPlacement.guiGameState=0;
                addView(guiShipPlacement);
                updateViews();
                break;
            case 1:
                if(guiShipPlacement==null){
                    guiShipPlacement=new GUIShipPlacement();
                }
                guiShipPlacement.guiGameState=1;
                addView(guiShipPlacement);
                updateViews();
                break;
            case 2:
                guiShipPlacement.setVisible(false);
                removeView(guiShipPlacement);
                break;
            default:
                
                break;
        }
    }
    
    public void addView(IView view){
        views.add(view);
    }
    public boolean removeView(IView view){
        views.remove(view);
        return true;
    }
    
    public void updateViews(){
        for(IView view:views){
            if(view!=null){
                view.updateView();
            }
        }
    }
    public void handleError(String error){
        System.out.println(error);
        game.handleError(error);
    }
}
