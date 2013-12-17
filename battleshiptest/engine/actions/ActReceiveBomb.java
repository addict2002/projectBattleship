package battleshiptest.engine.actions;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import battleshiptest.grid.*;

/**
 *
 * @author Andri
 */
public class ActReceiveBomb implements IEngineAction{
    Bomb bomb;
    
    public ActReceiveBomb(Bomb aBomb){
        bomb=aBomb;
    }
    
    @Override
    public void execute() {
        System.out.println(Thread.currentThread().getName()+ ":  We're bombed!!!");
        
    }
    
}
