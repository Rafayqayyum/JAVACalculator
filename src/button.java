
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTextField;
//This class sets mouse handler
class mouseHandler implements MouseListener{

    GUI ref;
    int m,i,j;
    static String prevText;
    static String prevbutton;
    static Boolean ans=false;
    static Boolean errorFlag=false;
    static Boolean minusFlag=false;
    static Boolean check1byx=false;
    static double repNum=0;
    static Boolean opFlag=false;
    mouseHandler(GUI gg,int k,int n,int l)
    {
        ref=gg;
        m=k;
        i=n;
        j=l;
    }
    @Override
    public void mouseClicked(MouseEvent me) {
        if(errorFlag)
        {
            ref.txt.setText("0");
            ref.operation.setText("");
            errorFlag=false;
        }
        Boolean equalCheck=(ref.operation.getText().contains("^") || ref.operation.getText().contains("root") || check1byx);
        if(!"=".equals(ref.btn[m].getText()) && !"(1/x)".equals(ref.btn[m].getText()))
        {
            check1byx=false;
        }
        String text;
        String opera="/*-+";
        if(opera.contains(ref.btn[m].getText()) && opFlag==true)
        {
            opFlag=false;
        }
        prevText=ref.txt.getText();
        text = ref.btn[m].getText();
        if(ref.btn[m].getText().toCharArray()[0] >=48 && ref.btn[m].getText().toCharArray()[0]<=57 && ref.txt.getText().length()<17)
        {
            prevbutton=ref.btn[m].getText();
            if(ans==true || opFlag==true)
            {
                ref.txt.setText("0");
                ref.operation.setText("");
                ans=false;
                prevText="";
                opFlag=false;
            }
            if("0".equals(ref.txt.getText()))
            {
                if("0".equals(ref.btn[m].getText()))
                {
                    ref.txt.setText("0");
                }
                else
                {
                    ref.txt.setText(text);
                }
            }
            else
            {
                ref.txt.setText(prevText+text); 
            }
        }
        else if("back".equals(ref.btn[m].getText()))
        {
            if(prevText.length()>0)
            {
                ref.txt.setText(prevText.substring(0, prevText.length()-1));
            }
            if("".equals(ref.txt.getText()))
            {
                ref.txt.setText("0");
            }
        }
        else if ("C".equals(ref.btn[m].getText()))
        {
            ref.txt.setText("0");
            ref.operation.setText("");
        }
        else if ("root".equals(ref.btn[m].getText()))
        {
            String val=ref.txt.getText();
            if(!endsWithOpera())
            {
                Double va=stringToDouble(val);
                if(va>=0)
                {
                    ref.operation.setText(ref.btn[m].getText()+"("+val+")");
                    ref.txt.setText(prettyPrint(Math.sqrt(va)));
                }
                else
                {
                    ref.txt.setText("Invalid input");
                    ref.operation.setText("");
                    errorFlag=true;
                    
                }
            }
            else 
            {
                repNum=stringToDouble(removeLast(ref.operation.getText()));
                ref.operation.setText(prettyPrint(repNum)+ref.operation.getText().substring(val.length())+ref.btn[m].getText()+"("+prettyPrint(repNum)+")");
                performOperation(prettyPrint(Math.sqrt(repNum)), repNum, ref.txt);
                
            }
        }
        else if ("x^2".equals(ref.btn[m].getText()))
        {
            String val=ref.txt.getText();
            if(!endsWithOpera())
            {
                String arr[]=ref.btn[m].getText().split("x");
                ref.operation.setText(val+arr[1]);
                Double va=stringToDouble(val);
                ref.txt.setText(prettyPrint(Math.pow(va,2)));
            } 
            else   
            {
                repNum=stringToDouble(removeLast(ref.operation.getText()));
                ref.operation.setText(prettyPrint(repNum)+ref.operation.getText().substring(val.length())+prettyPrint(repNum)+"^2");
                performOperation(prettyPrint(Math.pow(repNum,2)), repNum, ref.txt);               
            }
        }
        else if ("CE".equals(ref.btn[m].getText()))
        {
                ref.txt.setText("0");
        }
        else if ("(1/x)".equals(ref.btn[m].getText()))
        {
            if("0".equals(ref.txt.getText()) && !endsWithOpera())
            {
                ref.txt.setText("Cannot Divide by zero");
                errorFlag=true;
            }
            else
            {
                if(endsWithOpera())
                {
                    repNum=stringToDouble(removeLast(ref.operation.getText()));
                    performOperation(prettyPrint(repNum), (1/repNum), ref.txt);
                    ref.operation.setText(prettyPrint(repNum)+ref.operation.getText().substring(ref.operation.getText().length()-1)+"1/"+prettyPrint(repNum));
                    
                   
                }
                else
                {

                    ref.operation.setText("1/"+ref.txt.getText());
                    ref.txt.setText(prettyPrint(1/stringToDouble(ref.txt.getText())));
                }
                check1byx=true;
            }
            
        }
        else if ("=".equals(ref.btn[m].getText()) && !"".equals(ref.operation.getText()) && !equalCheck )
        {
            if((endsWithOpera() && "0".equals(ref.txt.getText()) && !prevbutton.equals("0")) || opFlag==true)
            {
                String str;
                Double first;
                int index=0;
                if(ref.operation.getText().contains("/"))
                {
                    index=ref.operation.getText().indexOf("/");
                }
                else if(ref.operation.getText().contains("*"))
                {
                    index=ref.operation.getText().indexOf("*");
                }
                else if(ref.operation.getText().contains("+"))
                {
                    index=ref.operation.getText().indexOf("+");
                }
                else if(ref.operation.getText().contains("-"))
                {
                    index=ref.operation.getText().indexOf("-");
                    if(index==0)
                    {
                        String cut=ref.operation.getText().substring(1);
                        if(ref.operation.getText().contains("-"))
                        {
                            index=cut.indexOf("-")+1;
                        }
                    }
                }                
                if(opFlag==false)
                {
                    str= removeLast(ref.operation.getText());
                    repNum=stringToDouble(str);
                    first= stringToDouble(removeLast(ref.operation.getText()));
                    performOperation(str,first,ref.txt);
                    ref.operation.setText(prettyPrint((first))+ref.operation.getText().substring(index, index+1)+prettyPrint(first));
                    opFlag=true;
                }
                else 
                {
                    str= ref.txt.getText();
                    first= repNum;
                    performOperation(str,first,ref.txt);
                    ref.operation.setText(str+ref.operation.getText().substring(index, index+1)+prettyPrint(first));
                }
                ans=false; 
            }
            else
            {   
                if(ans==false && !ref.operation.getText().contains("^"))
                {
                    Double second=stringToDouble(ref.txt.getText());
                    String str=ref.operation.getText();
                    ref.operation.setText(ref.operation.getText()+ref.txt.getText());
                    str=removeLast(str);
                    performOperation(str,second,ref.txt);
                    ans=true;
                    opFlag=true;
                    repNum=second;
                    
                }
            }
            
        }
        else if (endsWithOpera() && !"0".equals(ref.txt.getText())  && opera.contains(ref.btn[m].getText()))
        {
                    
            Double second=stringToDouble(ref.txt.getText());
            String str=ref.operation.getText();
            ref.operation.setText(ref.operation.getText()+ref.txt.getText());
            str=removeLast(str);
            performOperation(str,second,ref.operation);
            if(!(second==0 || ref.operation.getText().contains("/")))
            {
                ref.txt.setText("0");
                ref.operation.setText(ref.operation.getText()+ref.btn[m].getText());
            }
        }
        else if ("/".equals(ref.btn[m].getText()))
        {
            ans=false;
            if(endsWithOpera() && "0".equals(ref.txt.getText()))
            {
                replaceOperation();
            }
            else
            {
                ref.operation.setText(ref.txt.getText()+"/");
                ref.txt.setText("0");
            }
        }
        else if ("*".equals(ref.btn[m].getText()))
        {
            ans=false;
            if(endsWithOpera() && "0".equals(ref.txt.getText()))
            {
                replaceOperation();
            }
            else
            {
                ref.operation.setText(ref.txt.getText()+"*");
                ref.txt.setText("0");
            }   
        }
        else if ("+".equals(ref.btn[m].getText()))
        {
            ans=false;
            if(endsWithOpera() && "0".equals(ref.txt.getText()))
            {
                replaceOperation();
            }
            else 
            {
                ref.operation.setText(ref.txt.getText()+"+");
                ref.txt.setText("0");
            }   
        }
        else if ("-".equals(ref.btn[m].getText()))
        {
            ans=false;
            if(endsWithOpera() && "0".equals(ref.txt.getText()))
            {
                replaceOperation();
            }
            else
            {
                ref.operation.setText(ref.txt.getText()+"-");
                ref.txt.setText("0");
            }   
        }
        else if (".".equals(ref.btn[m].getText()) && !ref.txt.getText().contains("."))
        {
            ref.txt.setText(ref.txt.getText()+".");
        }
        else if ("+/-".equals(ref.btn[m].getText()) && !"0".equals(ref.txt.getText()))
        {
            if(!ref.txt.getText().contains("-"))
            {
                ref.txt.setText("-"+ref.txt.getText()); 
            }
            else
            {
                ref.txt.setText(ref.txt.getText().substring(1));
            }
        }
        else if("%".equals(ref.btn[m].getText()))
        {
            double num =stringToDouble(ref.txt.getText());
            double num2= stringToDouble(removeLast(ref.operation.getText()));
            ref.txt.setText(prettyPrint((num2*num)/100));
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        ref.btn[m].setBackground(Color.GRAY);
    }

    @Override
    public void mouseExited(MouseEvent me) {
        if (i==3 || i==4 ||j==3)
        {
            if ("=".equals(ref.btn[m].getText()))
            {
                ref.btn[m].setBackground(Color.decode("#307d9f"));   
            }
            else
            {
                ref.btn[m].setBackground(Color.DARK_GRAY); 
            }
        }
        else if(m<6)
        {
            ref.btn[m].setBackground(Color.DARK_GRAY);
        }
        else
        {
            ref.btn[m].setBackground(Color.BLACK);      
        }
    }
    public Boolean endsWithOpera()
    {
        return (ref.operation.getText().endsWith("/") || ref.operation.getText().endsWith("-") || ref.operation.getText().endsWith("*") || ref.operation.getText().endsWith("+"));
    }
    public void replaceOperation()
    {
        String str=ref.operation.getText();
        if (str != null && str.length() > 0) 
        {
            str = str.substring(0, str.length() - 1);
        }
        if("/".equals(ref.btn[m].getText()))
        {
            ref.operation.setText(str+"/");
        }
        else if("*".equals(ref.btn[m].getText()))
        {
            ref.operation.setText(str+"*");
        }
        else if("+".equals(ref.btn[m].getText()))
        {
            ref.operation.setText(str+"+");
        }
        else if("-".equals(ref.btn[m].getText()))
        {
            ref.operation.setText(str+"-");
        }         
    }
    public Double stringToDouble(String str)
    {
        Double foo;
        try {
            foo = Double.parseDouble(str);
        }
        catch (NumberFormatException e)
        {
            foo = 0.0;
        }
        return foo;
    }
    public String removeLast(String str)
    {
        if(str.length()>=2)
        {
            return str.substring(0, str.length() - 1);
        }
        else
        {
          return str;  
        }
    }
    private void performOperation(String str, Double second,JTextField both) {
        if(second==0 && ref.operation.getText().contains("/"))
        {
            ref.txt.setText("Cannot Divide by zero");  
            errorFlag=true;
        }
        else if(ref.operation.getText().contains("/"))
        {
            both.setText(prettyPrint(stringToDouble(str)/second));
        }
        else if(ref.operation.getText().contains("*"))
        {
           both.setText(prettyPrint(stringToDouble(str)*second));
        }
        else if(ref.operation.getText().contains("+"))
        {
            both.setText(prettyPrint(stringToDouble(str)+second));
        }
        else if(ref.operation.getText().contains("-"))
        {
            both.setText(prettyPrint(stringToDouble(str)-second));
        }
    }
    public String prettyPrint(double d) {
        int i = (int) d;
        return d == i ? String.valueOf(i) : String.valueOf(d);
    }
}
