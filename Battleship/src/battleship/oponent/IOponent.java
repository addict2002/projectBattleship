/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleship.oponent;

import battleship.grid.*;

/**
 *
 * @author Andri
 */
public interface IOponent {
    public void init();
    public boolean sendBombToOponent(Bomb aBomb);
    public boolean sendBombReportToOponent(BombReport aReport);
    public boolean sendReadyToOponent();
    
    public void bombFromOponent(Bomb aBomb);
    public void bombReportFromOponent(BombReport aReport);
    public void readyFromOponent();
    
}
