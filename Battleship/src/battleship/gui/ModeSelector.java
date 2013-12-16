/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.gui;
import battleship.Battleship;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Sandro
 */
public class ModeSelector extends JFrame implements ActionListener{

    private Battleship game;
    private JPanel panel = new JPanel();
    private JButton start = new JButton("Start");
    private boolean ishost = true;
    
    
    //Join Components
    private JLabel op = new JLabel("IP Adresse des Gegners");
    private JPanel ippanel = new JPanel();
    private JTextField ip = new JTextField("0.0.0.0");
    private JButton check = new JButton("check ip");
    private InetAddress opip;
    private JLabel checkresult = new JLabel("unchecked");
    
    
    
    //Host Components
    private String[] modes = { "Singleplayer", "Multiplayer"};
    private JComboBox mode = new JComboBox(modes);
    private InetAddress ownIp;
    private JLabel ipLabel = new JLabel();
    
    //Menu Components
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menugame = new JMenu("Game");
    private JMenuItem host = new JMenuItem("Host Game");
    private JMenuItem join = new JMenuItem("Join Game");
  
    
    public ModeSelector(Battleship currentGame){
        super("Welcome");
        setSize(400,200);
        setLocation(300,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        ownIp = null;
        try {
            ownIp = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            //Logger.getLogger(ModeSelector.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error: " + ex.getMessage());
        }
        
        
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
        
        /* change von bruno */
       // this.gameEngine = gameEng;
        this.game = currentGame;
    }
    
    /* change von bruno */
    /* klasse brauch kein main, wird jetzt in battleship.Battleship.java aufgerufen*/
    
 /*   public static void main(String[] args)
    {
        ModeSelector g = new ModeSelector();
    } 
 */   
    public void setJoin()
     {
        panel.setLayout(new GridLayout(0,1)); 
        ippanel.setLayout(new GridLayout(0,1));
        ippanel.add(op);
        ippanel.add(ip);

        
        panel.add(ippanel);
        panel.add(check);
        check.addActionListener(new checkEvent());
        panel.add(checkresult);
        panel.add(start);
        start.setEnabled(false);
        start.addActionListener(new joinEvent());
        setVisible(true);
     }
    public String getIP(){
        return(ip.getText());
    }
    public String getMode(){
        return(mode.getSelectedItem().toString());
    }
            
    public void setHost()
     {
         ipLabel.setText("HOST IP: " + ownIp.getHostAddress().toString());
         panel.setLayout(new GridLayout(0,1));
         panel.add(mode);
         panel.add(start);
         panel.add(ipLabel);
         start.addActionListener(new hostEvent());
         setVisible(true);
         
     }
    public void actionPerformed(ActionEvent e){
            if (e.getSource()==host)
            {
                setJMenuBar(null);
                setHost();
                ishost = true;
                
            }
            else if(e.getSource()==join)
            {
                setJMenuBar(null);
                setJoin(); 
                ishost = false;
            }
        }     
    class joinEvent implements ActionListener{
    public void actionPerformed(ActionEvent e){
        
        start.setText("Bitte warten");
    }
    } 
    class hostEvent implements ActionListener{
    public void actionPerformed(ActionEvent e){
        
        mode.setEnabled(false);
        start.setText("Bitte warten Spiel wird eröffnet");
        try {
           
            //Verbindung aufbauen ---> engine
            game.gameEngine.createHostEnginge();
           
        } catch (IOException ex) {
            //Logger.getLogger(ModeSelector.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error: " + ex.getMessage());
        }
    }
    } 
    class checkEvent implements ActionListener{
    public void actionPerformed(ActionEvent e){
       try{
            {
            opip = InetAddress.getByName(ip.getText());
                if(opip.isReachable(2000) && !opip.isAnyLocalAddress() /*&& !opip.isLoopbackAddress()*/){
                checkresult.setText("Host erreichbar");
                start.setEnabled(true);
                ip.setEditable(false);
                check.setEnabled(false);
                }
                else
                    checkresult.setText("Host nicht erreichbar");
            }  
           } 
       catch(Exception p){
           checkresult.setText("Keine gültige IP-Adresse");
       }
       finally{
           
       } 
        
    }
    }
}
