/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.gui;

import battleship.Battleship;
import battleship.grid.FieldPoint;
import battleship.grid.Ship;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Sandro
 */


public class ShipPlacement extends JFrame implements ActionListener{

    private Battleship game;
    private final int POSITION_HORIZONTAL = 1;
    private final int POSITION_VERTICAL = 0;
    private JPanel myfield = new JPanel();    
    private JButton mybutton[] = new JButton[100]; 
    private JPanel leftfield = new JPanel();
    private JLabel ship = new JLabel("Schiffe");
    private String[] ships = {"Schlachtschiff[5]", "Kreuzer[4]","Kreuzer2[4]", "Zerstörer[3]","Zerstörer2[3]","Zerstörer3[3]", "U-Boot[2]", "U-Boot2[2]", "U-Boot3[2]", "U-Boot4[2]"};
    private JComboBox shipchoose = new JComboBox(ships);
    private JButton next = new JButton("Weiter");
    private JRadioButton ver = new JRadioButton("vertikal");
    private JRadioButton hor = new JRadioButton("horizontal");
    private ButtonGroup group = new ButtonGroup();
    private int pos;
    private JButton reset = new JButton("zurücksetzen");

    
    public ShipPlacement(Battleship currentGame){
        super("Battleship");
        setSize(500,250);
        setLocation(100,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new GridLayout(1,2));  
        
        
        myfield.setLayout(new GridLayout(10,10));
        createPanel(mybutton, myfield);
        getContentPane().add(myfield);
        
        leftfield.setLayout(new GridLayout(0,1));
        leftfield.add(ship);
        leftfield.add(shipchoose);    
        group.add(ver);
        group.add(hor);
        leftfield.add(ver);
        leftfield.add(hor);
        leftfield.add(reset);
        reset.addActionListener(new ResetEvent());
        next.setEnabled(false);
        leftfield.add(next);
        next.addActionListener(new StartEvent());
        
        /*change von bruno */
        this.game = currentGame;
        
        
        getContentPane().add(leftfield);
        setVisible(true);
        
        
        
        
        }   
    public void createPanel(JButton[] buttons, JPanel panel){
        
        for(int i=0;i<buttons.length;i++)
            {
            buttons[i] = new JButton();
            panel.add(buttons[i]);
            buttons[i].addActionListener(this);
            }
        }
    public void closeShipPlacement(){
        setVisible(false);
    } 
    public void actionPerformed(ActionEvent e){
            if (e.getSource() instanceof JButton)
            {
                setShip((getPosition(((JButton)e.getSource()))));
                
            }
        }
    public int getPosition(JButton button)
    {
       for(int i=0; i<mybutton.length;i++)
                {
                    if(button==mybutton[i])
                    {
                        pos = i;
                    }
                }
       return(pos);
   }
    public String Int2String(int i)
    {
       String t = "";
       return(t.valueOf(i)); 
   }
    /* change von bruno */
  /*  public static void main(String[] args)
    {
        ShipPlacement p = new ShipPlacement();
    }
  */
    public void setShip(int place){
    int p = place;
    FieldPoint newPoint = FieldPoint.convertIndexToPoint(p);    
        /* change von bruno nur zum testen*/
        System.out.println("Place: " + p);
        System.out.println("Point x: " + newPoint.getXCoordiante() + " y: " + newPoint.getYCoordinate());
        
        switch (shipchoose.getSelectedItem().toString()) {
            //Schlachtschiff
            case "Schlachtschiff[5]": 
                    if(ver.isSelected()==true && isOK(p,5)==true)
                    {   
                        for(int i=0; i<5; i++ )
                        {
                            mybutton[p].setText("x");
                            p = p+10;
                        }
                        shipchoose.removeItemAt(shipchoose.getSelectedIndex());
                        Ship newShip = new Ship(5);
                        game.gameEngine.ownGrid.addShipToGrid(POSITION_VERTICAL, newPoint, newShip);
                    }
                    else if(hor.isSelected()==true && isOK(p,5)==true)
                            {
                        for(int i=0; i<5; i++ )
                        {
                            mybutton[p].setText("x");
                            p = p+1;
                        }
                        shipchoose.removeItemAt(shipchoose.getSelectedIndex());
                        Ship newShip = new Ship(5);
                        game.gameEngine.ownGrid.addShipToGrid(POSITION_HORIZONTAL, newPoint, newShip);
                    }
                    lockButton(); 
                    break;
            //Kreuzer
            case "Kreuzer[4]":
            case "Kreuzer2[4]":
                    if(ver.isSelected()==true && isOK(p,4)==true)
                    {
                        for(int i=0; i<4; i++ )
                        {
                            mybutton[p].setText("x");
                            p = p+10;
                        }
                        shipchoose.removeItemAt(shipchoose.getSelectedIndex());
                        Ship newShip = new Ship(4);
                        game.gameEngine.ownGrid.addShipToGrid(POSITION_VERTICAL, newPoint, newShip);
                    }
                    else if(hor.isSelected()==true && isOK(p,4)==true)
                            {
                        for(int i=0; i<4; i++ )
                        {
                            mybutton[p].setText("x");
                            p = p+1;
                        }
                        shipchoose.removeItemAt(shipchoose.getSelectedIndex());
                        Ship newShip = new Ship(4);
                        game.gameEngine.ownGrid.addShipToGrid(POSITION_HORIZONTAL, newPoint, newShip);
                    }
                    lockButton(); 
                    break;
            //Zerstörer
            case "Zerstörer[3]":
            case "Zerstörer2[3]":
            case "Zerstörer3[3]":   
                if(ver.isSelected()==true && isOK(p,3)==true)
                    {
                        for(int i=0; i<3; i++ )
                        {
                            mybutton[p].setText("x");
                            p = p+10;
                        }
                        shipchoose.removeItemAt(shipchoose.getSelectedIndex());
                        Ship newShip = new Ship(3);
                        game.gameEngine.ownGrid.addShipToGrid(POSITION_VERTICAL, newPoint, newShip);
                    }
                    else if(hor.isSelected()==true && isOK(p,3)==true)
                            {
                        for(int i=0; i<3; i++ )
                        {
                            mybutton[p].setText("x");
                            p = p+1;
                        }
                        shipchoose.removeItemAt(shipchoose.getSelectedIndex());
                        Ship newShip = new Ship(3);
                        game.gameEngine.ownGrid.addShipToGrid(POSITION_HORIZONTAL, newPoint, newShip);
                    }
                lockButton();     
                break;
            //U-Boot
            case "U-Boot[2]":
            case "U-Boot2[2]":
            case "U-Boot3[2]":
            case "U-Boot4[2]":
                if(ver.isSelected()==true && isOK(p,2)==true)
                    {
                        for(int i=0; i<2; i++ )
                        {
                            mybutton[p].setText("x");
                            p = p+10;
                        }
                        shipchoose.removeItemAt(shipchoose.getSelectedIndex());
                        Ship newShip = new Ship(2);
                        game.gameEngine.ownGrid.addShipToGrid(POSITION_VERTICAL, newPoint, newShip);
                    }
                else if(hor.isSelected()==true && isOK(p,2)==true)
                            {
                        for(int i=0; i<2; i++ )
                        {
                            mybutton[p].setText("x");
                            p = p+1;
                        }
                        shipchoose.removeItemAt(shipchoose.getSelectedIndex());
                        Ship newShip = new Ship(2);
                        game.gameEngine.ownGrid.addShipToGrid(POSITION_HORIZONTAL, newPoint, newShip);
                }
                lockButton(); 
                break;
                
        }
    }
    
    
    
    public void lockButton(){
        if(shipchoose.getItemCount() == 0)
        {
            for(int i=0;i<mybutton.length;i++)
            {
            mybutton[i].setEnabled(false);
            next.setEnabled(true);

            }
        }
    }
    public void unlockButton(){
            for(int i=0;i<mybutton.length;i++)
            {
            mybutton[i].setEnabled(true);
            
            }
            next.setEnabled(false);
    }  
    public boolean isOK(int place, int size ){
        boolean ok = true;
        int p = place;
        int s = size;
        String st = "x";
        if(hor.isSelected()){
        
            if((p%10)+s<=10){
                for(int i=0; i<s; i++)
                {
                    if(mybutton[p].getText().equals(st))
                    //if(mybutton[p].getText().equals(st) == false || p%10<= s )

                    {
                    ok = false;
                    }
                   p++;
                }
            
            
            }
            else ok= false;
            
        }
        else if(ver.isSelected())
        {
            if(p+((s-1)*10)<=99){
                for(int i=0; i<s; i++)
                {
                    if(mybutton[p].getText().equals(st))
                    //if(mybutton[p].getText().equals(st) == false || p+(s*10)>= 100)
                       {
                        ok = false;
                        }
                 p = p+10;
                }
            }
            else ok = false;
                
        }
        return(ok);
    }
    public boolean isShip(int f){
        boolean s;
        if(mybutton[f].getText().equals("x"))
            s = true;
        else
            s = false;
    return(s);
    }

    class ResetEvent implements ActionListener{
    public void actionPerformed(ActionEvent e){
        
        for(int i=0;i<mybutton.length;i++)
            {
                mybutton[i].setText("");
            }
        
        shipchoose.removeAllItems();
        for(String string : ships)
            {
            shipchoose.addItem(string);
            }
        
        unlockButton();
        }
    
    }
    class StartEvent implements ActionListener{
    public void actionPerformed(ActionEvent e){
        reset.setEnabled(false);
        next.setText("Wait");

   
    }
} 

}
