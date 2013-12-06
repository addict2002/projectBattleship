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
    private int pos;
    
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
                //((JButton)e.getSource()).setText(Int2String((getPosition(((JButton)e.getSource())))));
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
    
    public static void main(String[] args)
    {
        shipplacement p = new shipplacement();
    }
    
    public void setShip(int place){
    int p = place;
        switch (shipchoose.getSelectedItem().toString()) {
            //Schlachtschiff
            case "Schlachtschiff[5]": 
                    if(ver.isSelected()==true)
                    {
                        for(int i=0; i<5; i++ )
                        {
                            mybutton[p].setText("x");
                            p = p+10;
                        }
                        shipchoose.removeItemAt(shipchoose.getSelectedIndex());
                    }
                    else if(hor.isSelected()==true)
                            {
                        for(int i=0; i<5; i++ )
                        {
                            mybutton[p].setText("x");
                            p = p+1;
                        }
                        shipchoose.removeItemAt(shipchoose.getSelectedIndex());
                            }
                    break;
            //Kreuzer
            case "Kreuzer[4]":
            case "Kreuzer2[4]":
                    if(ver.isSelected()==true)
                    {
                        for(int i=0; i<4; i++ )
                        {
                            mybutton[p].setText("x");
                            p = p+10;
                        }
                        shipchoose.removeItemAt(shipchoose.getSelectedIndex());
                    }
                    else if(hor.isSelected()==true)
                            {
                        for(int i=0; i<4; i++ )
                        {
                            mybutton[p].setText("x");
                            p = p+1;
                        }
                        shipchoose.removeItemAt(shipchoose.getSelectedIndex());
                            }
                    break;
            //Zerstörer
            case "Zerstörer[3]":
            case "Zerstörer2[3]":
            case "Zerstörer3[3]":   
                if(ver.isSelected()==true)
                    {
                        for(int i=0; i<3; i++ )
                        {
                            mybutton[p].setText("x");
                            p = p+10;
                        }
                        shipchoose.removeItemAt(shipchoose.getSelectedIndex());
                    }
                    else if(hor.isSelected()==true)
                            {
                        for(int i=0; i<3; i++ )
                        {
                            mybutton[p].setText("x");
                            p = p+1;
                        }
                        shipchoose.removeItemAt(shipchoose.getSelectedIndex());
                            }
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
                    }
                else if(hor.isSelected()==true && isOK(p,2)==true)
                            {
                        for(int i=0; i<2; i++ )
                        {
                            mybutton[p].setText("x");
                            p = p+1;
                        }
                        shipchoose.removeItemAt(shipchoose.getSelectedIndex());
                            }
                    break;
                
        }
    }
    public boolean isOK(int place, int size ){
        boolean ok = true;
        int p = place;
        int s = size;
        String st = "x";
        if(hor.isSelected()){
        
        
            for(int i=0; i<s; i++)
            {
                if(mybutton[p].getText().equals(st) || p%10>=s)
                //if(mybutton[p].getText().equals(st) == false || p%10<= s )

                    {
                    ok = false;
                    }
                p++;
            }
        }
        else if(ver.isSelected())
        {
            for(int i=0; i<s; i++)
            {
                if(mybutton[p].getText().equals(st) || p+(s*10)>=99)
                //if(mybutton[p].getText().equals(st) == false || p+(s*10)>= 100)
                    {
                    ok = false;
                    }
                p = p+10;
            }
        }
        return(ok);
    }
    

}
