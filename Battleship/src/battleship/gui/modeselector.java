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
public class modeselector extends JFrame {

    private JLabel welcome = new JLabel("Welcome");
    private JLabel choose = new JLabel("Choose your Mode");
    private JPanel panel;
    private String[] modes = { "Singleplayer", "Multiplayer"};
    private JComboBox mode = new JComboBox(modes);
    
    public modeselector(){
        super("Welcome");
        setSize(600,600);
        setLocation(300,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new GridLayout(5,1));
        welcome.setHorizontalAlignment(JLabel.CENTER);
        add(welcome);
        add(choose);
        add(mode);
        setVisible(true);
    }
     public static void main(String[] args)
    {
        modeselector g = new modeselector();
    }
}
