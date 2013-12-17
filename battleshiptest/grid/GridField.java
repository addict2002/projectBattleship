/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleshiptest.grid;

/**
 *
 * @author Andri
 */
public class GridField {
    public boolean hasShip;
    public boolean bombed;
    public Ship ship;
    
    public void setShip(Ship aShip){
        hasShip=true;
        ship=aShip;
    }
    public BombReport receiveBomb(Bomb abomb){
        BombReport report=new BombReport(abomb);
        
        bombed=true;
        report.bombOnShip=hasShip;
        if(hasShip){
            report.shipDestroyed=ship.receiveBomb();
            report.gameOver=false;
        }else{
            report.shipDestroyed=false;
            report.gameOver=false;
        }
        
        return report;
        
        
    }
    
}
