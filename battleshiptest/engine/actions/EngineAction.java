/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleshiptest.engine.actions;

import battleshiptest.engine.Engine;

/**
 *
 * @author Andri
 */
public class EngineAction implements IEngineAction{
    Engine engine;
    int type;
    
    public EngineAction(int aType){
        this.type=aType;
        
    }
    
    @Override
    public void execute() {
        
    }
    
}
