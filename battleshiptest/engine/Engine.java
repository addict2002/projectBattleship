/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleshiptest.engine;

import battleshiptest.engine.actions.*;
import battleshiptest.grid.*;
import battleshiptest.net.*;
import battleshiptest.oponent.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Andri
 */
public class Engine {
    private static Engine engine;
    
    
    public static void main(String[] args){
        Engine engine=getEngine();
        engine.handleActions();
    }
    
    
    final ArrayList<IEngineAction> actionList=new ArrayList<>();
    public Game game;
    public IOponent oponent;
    public Connection connection;
    
    private Engine(){
        game=Game.getTheGame();
    }
    
    
    public static Engine getEngine(){
        if(engine==null){
            engine=new Engine();
        }
        return engine;
    }
    private void handleActions(){
        while(true){
            try {
                IEngineAction action=waitForNextAction();
                action.execute();
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
}
