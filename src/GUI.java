
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rafay
 */
public class GUI {
    
    JFrame fr;
    JPanel pnl[];
    JTextField txt;
    JTextField operation;
    JButton btn[];
    keyHandler bHand;
    mouseHandler hand[];
    String buttonText[] = {"%","CE","C","back","(1/x)","x^2","root","/","7", "8", "9","*","4", "5", "6","-", "1", "2", "3","+","+/-", "0", ".","=" };
    
public GUI()
{   
    initGUI();
}
public void initGUI()   
{
    
   //handles OUTPUT textfield object
   operation= new JTextField();
   operation.setBackground(Color.DARK_GRAY);
   operation.setDisabledTextColor(Color.WHITE);
   operation.setPreferredSize(new Dimension(370,50));
   operation.setBorder(new LineBorder(Color.DARK_GRAY,1));
   operation.setHorizontalAlignment(SwingConstants.RIGHT);
   operation.setFont(new Font("Arial", Font.BOLD, 17));
   operation.setEnabled(false);
   
   //handles INPUT textfield object
   txt= new JTextField("0");
   txt.setBackground(Color.DARK_GRAY);
   txt.setDisabledTextColor(Color.WHITE);
   txt.setPreferredSize(new Dimension(370,150));
   txt.setFont(new Font("Arial", Font.BOLD, 30));
   txt.setHorizontalAlignment(SwingConstants.RIGHT);
   txt.setBorder(new LineBorder(Color.DARK_GRAY,1));
   txt.setEnabled(false);
   
   //makes new frame 
   fr = new JFrame();
   fr.setLayout(new FlowLayout());
   
   //creates a panel array
   pnl= new JPanel[10];
   
   //initializes the main panel object and sets it bg color
   pnl[0]= new JPanel(new FlowLayout(FlowLayout.CENTER,0, 0));
   pnl[0].setBackground(Color.DARK_GRAY);
   
    //initializes the text area panel object and sets it bg color
   pnl[1]= new JPanel(new FlowLayout(FlowLayout.CENTER,0, 15));
   pnl[1].setBackground(Color.DARK_GRAY);
   
      
    //initializes the text area panel object and sets it bg color
   pnl[2]= new JPanel(new FlowLayout());
   pnl[2].setBackground(Color.DARK_GRAY);
   
   //initializes the Button panel object and sets it bg color
   pnl[3]= new JPanel();
   pnl[3].setBackground(Color.DARK_GRAY);
   
  //adds textfield to panel 1
   pnl[1].add(txt); 
   pnl[2].add(operation); 
   
   //adds panel 1 and 2 to main panel
   pnl[0].add(pnl[2]);
   pnl[0].add(pnl[1]);
   pnl[0].add(pnl[3]);
   
    hand= new mouseHandler[30];
    bHand= new keyHandler(this);
    fr.addKeyListener(bHand);
   //initializes button array
   btn = new JButton[30];
   int k=0;
   //creates all other buttons and adds them to their
   //respective panels
   for( int i=3; i<9; i++)
   {
       //initializes jpanel object and sets it bg color and border
       pnl[i]=new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
       pnl[i].setBackground(Color.GRAY);
       pnl[i].setBorder(null);
       
       //initializes jbuttons objects, sets their bg color
       //and adds them to panels
       for (int j = 0; j < 4; j++,k++) {
            btn[k]= new JButton("No Border");
            btn[k].setPreferredSize(new Dimension(99,78));  //sets the size of button 
            btn[k].setText(buttonText[k]);                  //sets the button text
            btn[k].setFocusable(false);
            //sets border of buttons and its fonts
            btn[k].setBorder(new LineBorder(Color.GRAY));
            btn[k].setFont(new Font("Arial",Font.PLAIN,21));
            //sets a mouse handler which controls the hover and pressing
            //of mouse buttons
            hand[k]= new mouseHandler(this,k,i,j);
            btn[k].addMouseListener(hand[k]);
            
            
            //if the buttons are in first two rows .BOLD,17));and last
            //coloumn it sets their background to dark gray
            if(i==3 || i==4 ||j==3)
            {
                if ("=".equals(buttonText[k]))
                {
                    btn[k].setBackground(Color.decode("#307d9f"));
                }
                else
                {
                    btn[k].setBackground(Color.DARK_GRAY);
                }
            }
            //otherwise the color is set black
            else
            {
                btn[k].setBackground(Color.BLACK); 
            }
            btn[k].setForeground(Color.WHITE); //button color is set
            pnl[i].add(btn[k]);  // button is added to the panel
        }
        pnl[0].add(pnl[i]);  // panels are added to main panel
    }
   pnl[0].setPreferredSize(new Dimension(420,766)); //sets the size of main panel
   
   //adds the main panel to the frame, sets it's size and location.
   fr.add(pnl[0]);
   fr.setSize(420,766);
   fr.setLocationRelativeTo(null);
   fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   fr.setVisible((true));
   
   
}
}
