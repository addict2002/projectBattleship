/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.gui;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Sandro
 */
public class play extends JFrame {
    
    private JLabel title = new JLabel("Battleship");
    private JLabel myfield = new JLabel("Mein Feld");
    private JLabel opponentfield = new JLabel("Gegnerisches Feld");
    private JLabel ships = new JLabel("Meine Schiffe");
    private JLabel state = new JLabel("Status");
    
    public play(){
        super("Battleship");
        setSize(1200,600);
        setLocation(100,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        setLayout(new GridLayout(3,3));
        add(title);
        
        add(myfield);
        add(opponentfield);
        add(ships);
        add(state);
        setVisible(true);
        
                
    }
    public static void main(String[] args)
    {
        play p = new play();
    }
}


