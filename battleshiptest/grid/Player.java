/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleshiptest.grid;

import battleshiptest.engine.*;
import battleshiptest.engine.actions.*;

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
    Game game;
    public Player(){
        game=Game.getTheGame();
        ready=false;
        grid=new Grid();
        shootingGrid=new Grid();
        ships=Ship.getShipsTemplate();
    }
    
    public BombReport receiveBomb(Bomb aBomb){
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
    
    public void receiveBombReport(BombReport areport){
        if(areport.bombOnShip){
            GridField afield=shootingGrid.getField(areport.bomb.x, areport.bomb.y);
            afield.bombed=true;
            afield.hasShip=true;
            yourTurn=true;
        }
    }
    
    protected void sendBomb(Bomb aBomb){
        sendBombShootingGrid(aBomb);
        Engine.getEngine().pushAction(new ActSendBomb(aBomb));
    }
    
    public void sendBombShootingGrid(Bomb aBomb){
        shootingGrid.getField(aBomb.x, aBomb.y).bombed=true;
        yourTurn=false;
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
    
}
