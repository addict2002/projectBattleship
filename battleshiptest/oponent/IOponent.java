/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleshiptest.oponent;

import battleshiptest.grid.*;

/**
 *
 * @author Andri
 */
public interface IOponent {
    public void sendBombToOponent(Bomb aBomb);
    public void sendBombReportToOponent(BombReport aReport);
    public void bombFromOponent(Bomb aBomb);
    public void bombReportFromOponent(BombReport aReport);
}
