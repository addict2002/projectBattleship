/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.gui;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Sandro
 */


public class shipplacement extends JFrame{

    
    private JPanel myfield = new JPanel();    
    private JButton mybutton[] = new JButton[100]; 
    
    
    public shipplacement(){
        super("Battleship");
        setSize(500,250);
        setLocation(100,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new GridLayout(1,3));  
        myfield.setLayout(new GridLayout(10,10));
        createPanel(mybutton, myfield);
        getContentPane().add(myfield);
        setVisible(true);
        
        
        
        
        }  
    
    public void createPanel(JButton[] buttons, JPanel panel){
        
        for(int i=0;i<buttons.length;i++)
            {
            buttons[i] = new JButton();
            buttons[i].setBackground(Color.BLUE);
            panel.add(buttons[i]);
            }
        }
    
    public static void main(String[] args)
    {
        shipplacement p = new shipplacement();
    }
}
