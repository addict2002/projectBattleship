/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.gui;

/**
 *
 * @author Sandro
 */
import java.util.Random;

public class RandomShipPlacement {
    
    private boolean field[] = new boolean[100];
    private boolean isVertical;
    private int shipstart;
    private int ships[] = {5,4,4,3,3,3,2,2,2,2};
    private int nextship = 0;
    private int shipcounter = 10;
    private Random rand = new Random();
    
    
    
    
    public RandomShipPlacement(){
        for(int i = 0; i<field.length; i++){
            field[i] = false; 
        }
        do   {
            shipstart = rand.nextInt(99);
            isVertical = rand.nextBoolean();
            if(isOK(shipstart,ships[nextship],isVertical)){
                setShip(shipstart,ships[nextship],isVertical);
            }
        }while(shipcounter!=0);  
    }
    
    // War da zum testen
//    public static void main(String[] args)
//    {
//        RandomShipPlacement p = new RandomShipPlacement();
//        boolean tabelle[][] = new boolean[10][10];
//        int mom = 0;
//        for(int i = 0; i<10;i++){
//                System.out.print("\n");
//            for(int i2 = 0; i2<10;i2++){
//                if(p.isEmpty(mom)){
//                 System.out.print("X");   
//                }else System.out.print("0"); 
//                mom++;
//            }
//        }
//        mom++;  
//                
//    }
    public boolean isOK(int f, int s, boolean iV){
        boolean isok = true;
        int fi = f;
        int si = s;
        boolean isV = iV;
        
        if(!isV){
            if((fi%10)+si<=10){
                for(int i = 0; i<si; i++){
                    if(!isEmpty(fi)){
                        isok = false;
                    }
                fi++;        
                }
            } else isok = false;
        }

        else if(isV){
            if(fi+((si-1)*10)<=99){    
                for(int i = 0; i<si; i++){
                    if(!isEmpty(fi)){
                        isok = false;
                    }
                fi = fi + 10;        
                }  
            }else isok = false;
        }
        return(isok);
    }
    
    public void setShip(int f, int s, boolean isVertical){
        int fi = f;
        if(isVertical){
            for(int i = 0; i<s; i++){
                field[fi] = true;
                //HIER GRID ÜBERGABE
                
                fi = fi + 10;
            } 
            shipcounter--;
            nextship++;
        }
        else if(!isVertical){
            for(int i = 0; i<s; i++){
                field [fi] = true;
                //HIER GRID ÜBERGABE
                
                fi++;
            }
            shipcounter--;
            nextship++;
        }
        
    } 
    public boolean isEmpty(int f){
        return(!field[f]);
    }
  
}
