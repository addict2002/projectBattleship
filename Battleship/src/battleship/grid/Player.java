/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleship.grid;

import battleship.engine.*;
import battleship.engine.actions.*;

/**
 *
 * @author Andri
 */
public class Player{
    public Grid grid;
    public Grid shootingGrid;
    public Ship[] ships;
    private int destroyedShips=0;
    public boolean yourTurn=false;
    public boolean ready;
    public boolean oponentReady=false;
//    Game game;
    public Player(){
        
//        game=Game.getTheGame();
        ready=false;
        grid=new Grid();
        shootingGrid=new Grid();
        ships=Ship.getShipsTemplate();
    }
    
    public BombReport receiveBomb(Bomb aBomb){
        if(!ready){
            return null;
        }
        BombReport report;
        report = grid.receiveBomb(aBomb);
        if(report.shipDestroyed){
            destroyedShips++;
            if(destroyedShips>=ships.length){
                report.gameOver=true;
            }
        }
        if(!report.bombOnShip){
            yourTurn=true;
        }
        return report;
    }
    
    public boolean receiveBombReport(BombReport areport){
        if(!ready){
            return false;
        }
        if(areport.bombOnShip){
            GridField afield=shootingGrid.getField(areport.bomb.x, areport.bomb.y);
            afield.bombed=true;
            afield.hasShip=true;
            yourTurn=true;
        }
        return true;
    }
    
    public void sendBomb(Bomb aBomb){
        if(sendBombShootingGrid(aBomb)){
            Engine.getEngine().pushAction(new ActSendBombToOponent(aBomb));
        }else{
            Game.getTheGame().handleError("Field already bombed!");
        }
    }
    
    public boolean sendBombShootingGrid(Bomb aBomb){
        GridField afield=shootingGrid.getField(aBomb.x, aBomb.y);
        if(afield.bombed){
            return false;
        }
        afield.bombed=true;
        yourTurn=false;
        return true;
    }
    
    public boolean placeShip(int x,int y,boolean ishorizontal, int shipLength){
        Ship aship=null;
        for(int i=0;i<ships.length;i++){
            if(ships[i].shipLength==shipLength && !ships[i].isShipPlaced){
                aship=ships[i];
            }
        }
        if(aship==null){
            return false;
        }
        return grid.placeShip(x, y,ishorizontal, aship);
    }
    
    public boolean setPlayerReady(){
        for(Ship ship:ships){
            if(!ship.isShipPlaced){
                return false;
            }
        }
        ready=true;
        if(oponentReady){
            Game.getTheGame().setGameState(2);
        }
        return ready;
    }
    
    public boolean setOponentReady(){
        oponentReady=true;
        if(ready){
            Game.getTheGame().setGameState(2);
        }
        return false;
    }
    
    public void initPlayer(){
        ready=false;
        grid=new Grid();
        shootingGrid=new Grid();
        ships=Ship.getShipsTemplate();
    }
}
