/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleshiptest.engine.actions;

import battleshiptest.grid.BombReport;

/**
 *
 * @author Andri
 */
public class ActReceiveBombReport implements IEngineAction{
    BombReport report;
    
    public ActReceiveBombReport(BombReport areport){
        report=areport;
    }
    
    @Override
    public void execute() {
        System.out.println(Thread.currentThread().getName()+ ":  Got BombReport!!!");
        
    }
    
}
