import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
public class keyHandler implements KeyListener
{
    mouseHandler hand;
    MouseEvent me;
    GUI ref;
    String buttonText[] = {"%","CE","C","back","(1/x)","x^2","root","/","7", "8", "9","*","4", "5", "6","-", "1", "2", "3","+","+/-", "0", ".","=" };
    public keyHandler(GUI gg)
    {
        ref=gg;
    }
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int code=ke.getKeyCode();
        char key=ke.getKeyChar();
        int index=searchIndex(key,code);
        if(index!=245)
        {
            ref.btn[index].setBackground(Color.GRAY);
            hand=new mouseHandler(ref, index, 0,0);
            hand.mouseClicked(me);
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int code=ke.getKeyCode();
        char key=ke.getKeyChar();
        int index=searchIndex(key,code);
        if(index!=245)
        {
            if ("=".equals(ref.btn[index].getText()))
            {
                ref.btn[index].setBackground(Color.decode("#307d9f"));   
            }
            else if ((ref.btn[index].getText().toCharArray()[0]>=48 && ref.btn[index].getText().toCharArray()[0]<=57) || ".".equals(ref.btn[index].getText()))
            {
                ref.btn[index].setBackground(Color.BLACK); 
            }
            else
            {
                ref.btn[index].setBackground(Color.DARK_GRAY);      
            }
        }
        
    }
    public int searchIndex(char key,int code)
    {
        switch (code) {
            case 8:
                return 3;
            case 61:
            case 10:
                return 23;
            default:
                for (int i=0;i<buttonText.length;i++)
                {
                    if (buttonText[i].equals(Character.toString(key)))
                    {
                        return i;
                    }
                }   break;
        }
        return 245;
    }

}
