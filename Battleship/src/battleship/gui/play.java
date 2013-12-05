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
public class play extends JFrame implements ActionListener{
    
    private JLabel title = new JLabel("Battleship");
    private JLabel myfieldlabel = new JLabel("Mein Feld");
    private JLabel opfieldlabel = new JLabel("Gegnerisches Feld");
    private JLabel ships = new JLabel("Meine Schiffe");
    private JLabel state = new JLabel("Status");
    private JButton mybutton[] = new JButton[100];
    private JButton opbutton[] = new JButton[100];
    private JPanel myfield = new JPanel();
    private JPanel opfield = new JPanel();
    private JPanel midfield = new JPanel();
    private JPanel myfieldall = new JPanel();
    private JPanel opfieldall = new JPanel();
    
    public play(){
        super("Battleship");
        setSize(1000,250);
        setLocation(100,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        setLayout(new GridLayout(1,3));

        myfield.setLayout(new GridLayout(10,10));
        createPanel(mybutton, myfield);
        myfieldall.setLayout(new BorderLayout());
        myfieldall.add(myfieldlabel, BorderLayout.NORTH);
        myfieldall.add(myfield, BorderLayout.CENTER);
        
        opfield.setLayout(new GridLayout(10,10));
        createPanel(opbutton, opfield);
        opfieldall.setLayout(new BorderLayout());
        opfieldall.add(opfieldlabel, BorderLayout.NORTH);
        opfieldall.add(opfield, BorderLayout.CENTER);
        
        midfield.setLayout(new GridLayout(2,1));
        midfield.add(state);
        midfield.add(ships);
        
        getContentPane().add(opfieldall);
        getContentPane().add(midfield);
        getContentPane().add(myfieldall);
        

        
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
        play p = new play();
    }
    
}


