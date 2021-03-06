/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;
/**
 *
 * @author Sandro
 */
public class Play extends JFrame implements ActionListener{
    
    private JLabel myfieldlabel = new JLabel("Mein Feld");
    private JLabel opfieldlabel = new JLabel("Gegnerisches Feld");
    private JLabel state = new JLabel("Status");
    private JLabel status = new JLabel("Test");
    private JButton mybutton[] = new JButton[100];
    private JButton opbutton[] = new JButton[100];
    private JPanel myfield = new JPanel();
    private JPanel opfield = new JPanel();
    private JPanel midfield = new JPanel();
    private JPanel myfieldall = new JPanel();
    private JPanel opfieldall = new JPanel();
    private Border border = BorderFactory.createLineBorder(Color.RED ,5);
    
    public Play(){
        super("Battleship");
        setSize(1000,250);
        setLocation(100,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        setLayout(new GridLayout(1,3));

        myfield.setLayout(new GridLayout(10,10));
        myfieldlabel.setHorizontalAlignment(JLabel.CENTER);
        createPanel(mybutton, myfield);
        myfieldall.setLayout(new BorderLayout());
        myfieldall.add(myfieldlabel, BorderLayout.NORTH);
        myfieldall.add(myfield, BorderLayout.CENTER);
        
        opfield.setLayout(new GridLayout(10,10));
        opfieldlabel.setHorizontalAlignment(JLabel.CENTER);
        createPanel(opbutton, opfield);
        opfieldall.setLayout(new BorderLayout());
        opfieldall.add(opfieldlabel, BorderLayout.NORTH); 
        opfieldall.add(opfield, BorderLayout.CENTER);
        
        midfield.setLayout(new GridLayout(3,1));
        midfield.add(state);
        state.setHorizontalAlignment(JLabel.CENTER);
        midfield.add(status);
        status.setHorizontalAlignment(JLabel.CENTER);
        status.setBorder(border);

        getContentPane().add(opfieldall);
        getContentPane().add(midfield);
        getContentPane().add(myfieldall);
        
        lockFields(true);
        lockFields(false);
        
        
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
    public void fillMyfield(int f){
        mybutton[f].setText("x");
    }
    public void actionPerformed(ActionEvent e){
            if (e.getSource() instanceof JButton)
            {
                ((JButton)e.getSource()).setText("X");
                
            }
        }
    public static void main(String[] args)
    {
        Play p = new Play();
    }
    
    //Lock Methoden
    public void lockFields(boolean f){
        if(f==true)
        {
            for(int i=0;i<mybutton.length;i++)
        {
        mybutton[i].setEnabled(false);
        
        }  
        }
        else if(f==false){
            for(int i=0;i<opbutton.length;i++)
            {
            opbutton[i].setEnabled(false);
            }  
        }
    }
    public void lockField(int field, boolean f ){
        if(f==true){
            mybutton[field].setEnabled(false);
                
            }
        else if(f==false){
            opbutton[field].setEnabled(false);
        }
            
    }
    public void unlockField(int field, boolean f ){
        if(f==true){
            mybutton[field].setEnabled(true);
                
            }
        else if(f==false){
            opbutton[field].setEnabled(true);
        }
            
    }
    public void unlockFields(boolean f){
        if(f==true)
        {
            for(int i=0;i<mybutton.length;i++)
            {
            if(!mybutton[i].getText().equals("~") || !mybutton[i].getText().equals("X"))
                {
                mybutton[i].setEnabled(true);    
                }
            
        
            }  
        }
        else if(f==false){
            for(int i=0;i<mybutton.length;i++)
            {
                if(!mybutton[i].getText().equals("~") || !mybutton[i].getText().equals("X"))
                {
                mybutton[i].setEnabled(true);    
                }
            }  
        }
    }
    
    //Schiffe setzen
    public void setShip(int field){
        mybutton[field].setText("X");
    }
    
    //Bombem setzen
    public void setBomb(int field, boolean h, boolean f){
         if(f==true)
         {
             if(h==true)
             {
                 mybutton[field].setText("X");
                 mybutton[field].setForeground(Color.red);
             }
             else if(h==false)
             {
                 mybutton[field].setText("~");
                 mybutton[field].setForeground(Color.blue);
             }
         }
         else if(f==false)
         {
             if(h==true)
             {
                 opbutton[field].setText("X");
                 opbutton[field].setForeground(Color.red);
             }
             else if(h==false)
             {
                 opbutton[field].setText("~");
                 opbutton[field].setForeground(Color.blue);
             }
         }
         
     }         
    
    
    //Status Methoden
    public void myturnStatus(){
        status.setText("Du bist dran");
    }
    public void opturnStatus(){
        status.setText("Warte auf Gegner");
    }
    public void loseStatus(){
        status.setText("Verloren");
    } 
    public void winStatus(){
        status.setText("Gewonnen");
    }
    
}



