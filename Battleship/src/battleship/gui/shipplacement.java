/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Sandro
 */


public class shipplacement extends JFrame implements ActionListener{

    
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

    
    public shipplacement(){
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
        leftfield.add(next);
        
        getContentPane().add(leftfield);
        setVisible(true);
        
        
        
        
        }  
    
    public void createPanel(JButton[] buttons, JPanel panel){
        
        for(int i=0;i<buttons.length;i++)
            {
            buttons[i] = new JButton();
            buttons[i].setBackground(Color.BLUE);
            panel.add(buttons[i]);
            buttons[i].addActionListener(this);
            }
        }
    

   public void actionPerformed(ActionEvent e){
            if (e.getSource() instanceof JButton)
            {
                ((JButton)e.getSource()).setText("X");
                
            }
        }

    
    public static void main(String[] args)
    {
        shipplacement p = new shipplacement();
    }
    

}
