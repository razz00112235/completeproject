import java.util.*;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; 
public class Instalogin
{
 
    void reg_page()
    {
        JFrame frame=new JFrame("Register Page");
        frame.setSize(450,400); 
        JLabel msg=new JLabel("TCG USER");
        msg.setBounds(200, 60, 200, 30); 
        msg.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel lb_name=new JLabel("UserName");
        lb_name.setBounds(50, 100, 100, 30);        

        JTextField  uname=new JTextField(10);         
        uname.setBounds(150, 110, 200, 30);  

        JLabel lb=new JLabel("mail");
        lb.setBounds(50, 150, 100, 30);        

        JTextField  mail=new JTextField(10);         
        mail.setBounds(150, 150, 200, 30);       

        JLabel lb2=new JLabel("pass");
        lb2.setBounds(50, 190, 50, 30);           
        
        JTextField  pass=new JTextField(10);         
        pass.setBounds(150, 190, 200, 30);        
        
        JButton bt1=new JButton("Register");
        bt1.setBounds(200, 250, 100, 30);     
        Color color=new Color(128, 128, 128); 
        bt1.setBackground(color); 
        bt1.setForeground(color.white);  

        JLabel output=new JLabel();
        output.setBounds(100, 280, 100, 30);
        JLabel result=new JLabel();
        result.setBounds(100, 300, 100, 30);

        frame.add(lb_name);frame.add(msg);frame.add(uname);frame.add(lb);
        frame.add(mail);frame.add(lb2);frame.add(pass);frame.add(bt1);
        frame.add(output);frame.add(result);frame.setLayout(null);  
        frame.setVisible(true);

        ActionListener click=new ActionListener(){         
            public void actionPerformed(ActionEvent e)           
            { 
                  
                try {  if(uname.getText()!="" && mail.getText()!="" && pass.getText()!="")  
                        {
                            FileWriter fw = new FileWriter("instadata.txt",true);
                            fw.write(uname.getText()+"+"+mail.getText()+"+"+pass.getText()+"\n");           
                            fw.close(); 
                        }  
                              
                       
                } catch (Exception ee) {
                    System.out.println(ee);
                }           
                
                result.setText("Register success..");
                frame.setVisible(false);
                login();
            }
        };
        bt1.addActionListener(click);
    }
   void login()
      {
        JFrame frame=new JFrame("InstaGram Login Page");
        frame.setSize(450,400);  

        JLabel sign=new JLabel("Sign in");
        sign.setBounds(200, 30, 100, 30);   
        JLabel msg=new JLabel("Use your Register Account");
        msg.setBounds(150, 50, 200, 30); 
        JLabel lb=new JLabel("UserName");
        lb.setBounds(50, 90, 150, 30);        

        JTextField  mail=new JTextField(10);         
        mail.setBounds(120, 90, 200, 30);       

        JLabel lb2=new JLabel("pass");
        lb2.setBounds(50, 140, 50, 30);           
        
        JTextField  pass=new JTextField(10);         
        pass.setBounds(120, 140, 200, 30);        
        
        JButton bt1=new JButton("Login");
        bt1.setBounds(150, 200, 100, 30);  
        Color color=new Color(135,135,128); 
        bt1.setBackground(color); 
        bt1.setForeground(color.white); 
        
        
        JButton bt2=new JButton("New User");
        bt2.setBounds(150, 250, 100, 30);          
        bt2.setBackground(color); 
        bt2.setForeground(color.white);

        JLabel output=new JLabel();
        output.setBounds(100, 480, 300, 50);
        
        frame.add(sign);frame.add(msg);frame.add(lb);frame.add(mail);
        frame.add(lb2);frame.add(pass);frame.add(bt1);frame.add(bt2);
        frame.add(output);frame.setLayout(null);frame.setVisible(true);

        ActionListener click=new ActionListener(){         
            public void actionPerformed(ActionEvent e)           
            { 
                  
                try {
                    int count=0;
                    File check_data = new File("instadata.txt");
                    Scanner rd = new Scanner(check_data);
                    while (rd.hasNextLine()) {
                    String data = rd.nextLine();  
                          
                    if(data.contains(mail.getText()) && data.contains(pass.getText()))
                    {
                        count++;
                    }
                    
                    }
                    output.setText(""+count);
                    if(count==1)
                    {
                        output.setText("Login Success ");
                        frame.setVisible(false);
                        home_page(mail.getText());
                    }
                    else if(count==0)
                    {
                        output.setText("Incorrect Details ");                             
                        login();                  
                     }
                    
                } catch (Exception ee) {
                    System.out.println(ee);
                }           
               
            }
        };
        bt1.addActionListener(click);

        ActionListener reg=new ActionListener(){         
            public void actionPerformed(ActionEvent e)           
            { 
                
                reg_page();
                frame.setVisible(false);
                
            }

        };
        bt2.addActionListener(reg);
    }
 
  public void home_page(String user_name)
  {
    JFrame frame=new JFrame("InstaGram Home Page ");
    frame.setSize(500,600);  

    JLabel name=new JLabel();
    name.setText("Welcom Mr."+user_name);
    name.setBounds(100, 10, 300, 50);
    
        frame.add(name); frame.setLayout(null);  
        frame.setVisible(true);
  }

     public static void main(String args[])
       {
        Instalogin obj=new Instalogin();
           obj.login();
         

           
       }
   


 
}