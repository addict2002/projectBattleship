/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Sandro
 */
public class modeselector extends JFrame implements ActionListener{

    private JPanel panel = new JPanel();
    private JButton start = new JButton("Start");

    
    
    //Join Components
    private JLabel labmyip = new JLabel("Meine IP Adresse");
    private JLabel myip = new JLabel("0");
    private JLabel op = new JLabel("IP Adresse des Gegners");
    private JPanel ippanel = new JPanel();
    private JTextField ip1 = new JTextField("000");
    private JLabel dot12 = new JLabel(".");
    private JTextField ip2 = new JTextField("000");
    private JLabel dot23 = new JLabel(".");
    private JTextField ip3 = new JTextField("000");
    private JLabel dot34 = new JLabel(".");
    private JTextField ip4 = new JTextField("000");
    
    
    
    //Host Components
    private String[] modes = { "Singleplayer", "Multiplayer"};
    private JComboBox mode = new JComboBox(modes);
    
    //Menu Components
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menugame = new JMenu("Game");
    private JMenuItem host = new JMenuItem("Host Game");
    private JMenuItem join = new JMenuItem("Join Game");
  
    
    public modeselector(){
        super("Welcome");
        setSize(600,600);
        setLocation(300,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        //Menu
        menugame.add(host);
        host.addActionListener(this);
        menugame.add(join);
        join.addActionListener(this);
        menuBar.add(menugame);
        setJMenuBar(menuBar);
        
        panel.setLayout(new GridLayout(1,1));
        add(panel);
        
        setVisible(true);
        
        
        
    }
     public static void main(String[] args)
    {
        modeselector g = new modeselector();
    }
    
     public void setJoin()
     {
        panel.setLayout(new GridLayout(2,1)); 
        ippanel.setLayout(new FlowLayout());
        ippanel.add(op);
        ippanel.add(ip1);
        ippanel.add(dot12);
        ippanel.add(ip2);
        ippanel.add(dot23);
        ippanel.add(ip3);
        ippanel.add(dot34);
        ippanel.add(ip4);
        
        panel.add(ippanel);
        panel.add(start);
        
        setVisible(true);
     }
     
     public void setHost()
     {
         panel.setLayout(new GridLayout(0,1));
         panel.add(mode);
         panel.add(labmyip);
         panel.add(myip);
         panel.add(start);
         setVisible(true);
         
         
     }
     
     
     
    public void actionPerformed(ActionEvent e){
            if (e.getSource()==host)
            {
                setJMenuBar(null);
                setHost();
                
            }
            else if(e.getSource()==join)
            {
                setJMenuBar(null);
                setJoin();     
            }
        }
    
}
