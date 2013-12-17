/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleshiptest.engine.actions;

import battleshiptest.engine.Engine;
import battleshiptest.grid.Bomb;

/**
 *
 * @author Andri
 */
public class ActSendBomb implements IEngineAction{
    Bomb bomb;
    public ActSendBomb(Bomb abomb){
        bomb=abomb;
    }
    @Override
    public void execute() {
        Engine engine=Engine.getEngine();
        engine.oponent.sendBombToOponent(bomb);
    }
    
}
