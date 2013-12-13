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
    public void sendBomb(Bomb aBomb);
    public void sendBombReport(BombReport aReport);
    public Bomb receiveBomb();
    public BombReport receiveBombReport();
}
