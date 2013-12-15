/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleship.engine;

import java.util.Random;
import battleship.grid.Grid;
import battleship.net.Connection;
import battleship.net.MessageListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bruno
 */
public class Engine{
   
    private final int STANDART_GAME_PORT = 1212;
    private boolean isHostCurrentPlayer;
    private boolean isHostGridReady;
    private boolean isOpponentGridReady;
    //current game state
    private gameState currentGameState;
    private networkMode netMode;
    private gameMode currentGameMode;
    
    private Grid ownGrid = new Grid(10, 10);
    private Connection conn;
    
    
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
    
    
    private Engine(boolean isHostGame, boolean isNetworkGame) throws IOException
    {
        this.currentGameState = gameState.PreparingGame;
        this.isHostCurrentPlayer = isHostGame; /* old */
        
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
                this.conn = Connection.joinConnection(STANDART_GAME_PORT, null);
                this.netMode = networkMode.Client;
            }
        }
        else
        {
            this.currentGameMode = gameMode.OfflineMode;
        }
    }
    
    
    public void createHostEnginge()
    {
        
    }
   
    public void createClientEnginge()
    {
        
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
                isHostCurrentPlayer = true;
                currentGameState = gameState.Play;
                // inform player that it is his turn
            }
        }
        else
        {
            if(currentGameState.equals(gameState.Play))
            {
                isHostCurrentPlayer = false;
                currentGameState = gameState.WaitingForOpponent;
            }
        }
    }
    
    //TODO: startGame
    public void startGame()
    {
        if(isHostGridReady && isOpponentGridReady)
        {
            currentGameState = gameState.Play;
            if(evalBeginningPlayer() > 0)
            {
                isHostCurrentPlayer = true;
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
    
    //TODO: shoot parameter: field (int 1 - 99); return isShipHit (true/false)
    public boolean shoot(int field)
    {
        boolean result = false;
        
        /*
        give the shoot to the grid
        if it is a hit --> return true
        if it is not a hit --> return false
        */
    
        //call set bomb
        
        return result;
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
    
    
   
    //TODO: finishGame
    public void finishGame()
    {
        currentGameState = gameState.Finished;
    }
}