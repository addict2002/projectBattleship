/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleship.engine;

import battleship.grid.Field;
import java.util.Random;
import battleship.grid.Grid;
import battleship.grid.Ship;
import battleship.gui.ModeSelector;
import battleship.net.Connection;
import battleship.net.Message;
import battleship.net.MessageListener;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bruno
 */
public class Engine{
   
    private final int STANDART_GAME_PORT = 1212;
 //   private boolean isHostCurrentPlayer;
    private boolean isHostGridReady;
    private boolean isOpponentGridReady;
    //current game state
    public gameState currentGameState;
    private networkMode netMode;
    private gameMode currentGameMode;
    
    public Grid ownGrid = new Grid(10, 10);
    public Connection conn;
    
    
    //defines the possible gamestates
    private enum gameState{
        PreparingGame, SelectingOpponent, PreparingGrid, Play, WaitingForOpponent, Finished;
    }
    
    //defines the possible gamemodes
    private enum gameMode{
        NetworkMode, OfflineMode;
    }
    
    private enum networkMode{
        Host, Client;
    }
    
    
    private Engine(boolean isHostGame, boolean isNetworkGame, InetAddress ip) throws IOException
    {
        this.currentGameState = gameState.PreparingGame;
      //  this.isHostCurrentPlayer = isHostGame; /* old */
        
        this.isHostGridReady = false;
        this.isOpponentGridReady = false;
        
        if(isNetworkGame)
        {
            this.currentGameMode = gameMode.NetworkMode;
            if(isHostGame)
            {
                this.conn = Connection.openConnection(STANDART_GAME_PORT);
                this.netMode = networkMode.Host;
            }
            else
            {
                this.conn = Connection.joinConnection(STANDART_GAME_PORT, ip);
                this.netMode = networkMode.Client;
            }
        }
        else
        {
            this.currentGameMode = gameMode.OfflineMode;
        }
    }
    
    public static Engine createHostEngine() throws IOException
    {
        Engine hostEngine = new Engine(true, true, null);
        hostEngine.currentGameState = gameState.SelectingOpponent;
        return hostEngine;
    }
   
    public static Engine createClientEngine(InetAddress hostIp) throws IOException
    {
        Engine clientEngine = new Engine(false, true, hostIp);
        clientEngine.currentGameState = gameState.SelectingOpponent;       
        return clientEngine;
    }
    
    public static Engine createOfflineEngine() throws IOException
    {
        Engine offlineEngine = new Engine(true, false, null);
        offlineEngine.currentGameState = gameState.PreparingGrid; /* maybe... */
        return offlineEngine; 
    }
    
    //start connection to host 
    public void connectToHost() throws IOException
    {
        if(this.currentGameState == gameState.SelectingOpponent)
        {
            Message openConnectionMessage = new Message();
            openConnectionMessage.textMessage = "msg:joinGame";
            openConnectionMessage.type = 3;
            this.conn.messageSender.sendMessageToClient(openConnectionMessage);
        }
    }
    
    //TODO: setShipOnOwnGrid parameter: shipsize, isVertical return isShipSet (true / false)
    public boolean setShipOnOwnGrid(int shipSize, boolean isVertical )
    {
        boolean isShipSet = false;
        
        //TODO: Grid.setShip(shipSize, isVertical);
        
        return false;
    } 
    //TODO: setCurrentGameState (use lockfield(false) from GUI to lock opponent)
    
    
    //TODO: setCurrentActivePlayer
    public void setCurrentActivePlayer(boolean _isHostCurrentPlayer)
    {
        if(_isHostCurrentPlayer)
        {
            if(currentGameState.equals(gameState.WaitingForOpponent))
            {
               // isHostCurrentPlayer = true;
                currentGameState = gameState.Play;
                // inform player that it is his turn
            }
        }
        else
        {
            if(currentGameState.equals(gameState.Play))
            {
                //isHostCurrentPlayer = false;
                currentGameState = gameState.WaitingForOpponent;
            }
        }
    }
    
    //TODO: startGame
    public void startGame()
    {
        if(isHostGridReady && isOpponentGridReady)
        {
            
            if(evalBeginningPlayer() > 0)
            {
                //isHostCurrentPlayer = true;
                currentGameState = gameState.Play;
                
                //TODO: inform gui that game is ready and the action is on the current player 
            }
            else
            {
                currentGameState = gameState.WaitingForOpponent;
                
                //TODO: inform gui that game is ready and the action is on the opponent
               
                //TODO: inform opponent that the action is on him
            }
        }
    }
    
    //Evaluate beginning Plyer
    public int evalBeginningPlayer()
    {
        int retVal = 0;
        Random x = new Random();
        retVal = x.nextInt(2);
        return retVal;
    }
    
    //reciveShot returns state --> 0 = no hit; 1 = ship hit; 2 = ship sunk; 3 = all ships are shunk; 
    public int handleRecivingShot(Field field)
    {
        int defaultResultState = 0;
        
        if(this.ownGrid.isShipOnField(field))
        {
            if(this.ownGrid.hitShip(field))
            {
                Iterator<Ship> shipListIterator = this.ownGrid.shipList.iterator();
                while(shipListIterator.hasNext())
                {
                    Ship tempShip = shipListIterator.next();
                    if(tempShip.getShipState())
                    {
                        return 2;
                    }
                }
                return 3;
            }
            return 1;
        }
        /*
        give the shoot to the grid
        if it is a hit --> return true
        if it is not a hit --> return false
        */
    
        //call set bomb
        
        return defaultResultState;
    }
    
    //sendShot return state --> 0 = no hit; 1 = ship hit; 2 = ship bombed; 3 = all ships are bombed;
    public int sendShot(Field field) throws IOException
    {
        int state = 0;
        Message shotMessage = new Message();
        shotMessage.textMessage = "Shoot on Field: x: " + field.getXCoordinate() + "; y: " + field.getYCoordiante() + ";";
        shotMessage.type = 2;
        this.conn.messageSender.sendMessageToClient(shotMessage);
        
        return state;
    }
    
    //TODO: setBomb für anzeige auf eignenm Grid (fiel, isHit, isOpponent)
    public boolean setBomb(int field, boolean isOpponent, boolean isHit)
    {
        boolean result = false;
        
        if(isOpponent)
        {
            //set bomb on opponent-grid --> return true if successfull
        }
        else
        {
            //set bomb on own-grid --> return true if successfull
        }
        
        return result;
    }
    
    public void closeModeSelector(ModeSelector modeSelector)
    {
        modeSelector.setVisible(false);
    }
   
    //TODO: finishGame
    public void finishGame()
    {
        currentGameState = gameState.Finished;
    }
}
