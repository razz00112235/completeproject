import java.net.*;
import java.io.*;
import java.util.*;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class Clientdata{
static JRadioButton reg_data,login_data;
static JLabel msg_lb,output,server_msg,u_name,u_mail,u_did,reg_pass,login_mail,login_pass,login_msg;
static JFrame client_page,frame,login_page;
static JTextField user_name,user_mail,user_did,login_m,login_p;
static Scanner sc=new Scanner(System.in);
static JButton sub_reg;
static String msg,name,mail;
static  int count_check=0;
static int id,pass;
    void home()
    {
        ButtonGroup bg=new ButtonGroup();
        Scanner sc=new Scanner(System.in);
        frame=new JFrame("Register Page");
        frame.setSize(300,300);
        msg_lb=new JLabel("Please Choose 1.Login 2.Register");
        msg_lb.setBounds(50, 10, 200, 20);
        login_data=new JRadioButton("Login");
        login_data.setBounds(80, 50, 100, 20);
        reg_data=new JRadioButton("Register");
        reg_data.setBounds(80, 80, 150, 20);

       

        bg.add(reg_data);bg.add(login_data);
        frame.add(msg_lb);
        frame.add(reg_data);frame.add(login_data);       
        frame.setLayout(null);
        frame.setVisible(true);

        ActionListener checked=new ActionListener(){
            public void actionPerformed(ActionEvent e)
            { 
               int num=2;               
               register(num);
            }
        };
        reg_data.addActionListener(checked);
        ActionListener checked2=new ActionListener(){
            public void actionPerformed(ActionEvent e)
            { 
               int num2=1;
               login(num2);
            }
        };
        login_data.addActionListener(checked2);
       

    }
      static void register(int a)
         {
            
            Clientdata cd=new Clientdata();
            try {
                
                client_page=new JFrame("Client Page");
                client_page.setSize(400,400);               
                server_msg=new JLabel("Please Fill Your Details:--");
                server_msg.setBounds(150, 20, 200, 30);
                u_name=new JLabel("Enter your Name");
                u_name.setBounds(10, 50, 150, 30);
                user_name=new JTextField();
                user_name.setBounds(170, 50, 200, 30);
                u_mail=new JLabel("Enter your Email");
                u_mail.setBounds(10, 100, 150, 30);
                user_mail=new JTextField(); 
                user_mail.setBounds(170, 100, 200, 30);
                u_did=new JLabel("Enter your Department ID");
                u_did.setBounds(10, 150, 150, 30);
                user_did=new JTextField();  
                user_did.setBounds(170, 150, 200, 30);
                sub_reg=new JButton("Register");
                sub_reg.setBounds(170, 200, 150, 40);

                reg_pass=new JLabel();
                reg_pass.setBounds(10, 250, 350, 30);
                Socket s=new Socket("localhost",8005);
                System.out.println("Client connected");
               
                JButton user_login=new JButton("Login");
                user_login.setBounds(150, 300, 110, 30);
                client_page.add(server_msg);client_page.add(u_name);
                client_page.add(user_name);client_page.add(u_mail);client_page.add(u_did);
                client_page.add(user_mail);client_page.add(user_did);client_page.add(sub_reg);
                client_page.add(reg_pass);client_page.add(user_login);
                user_login.setVisible(false);
                client_page.setLayout(null);
                client_page.setVisible(true);
                frame.setVisible(false);
               
                ActionListener click_reg=new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    { 
                        try {
                            while(true)
                             {
                                DataInputStream dis=new DataInputStream(s.getInputStream());  
                                DataOutputStream dout=new DataOutputStream(s.getOutputStream());
                            
                                
                                int fun=a;        
                                dout.writeInt(fun);                             
                            
                                dout.writeUTF(user_name.getText());  
                                dout.writeUTF(user_mail.getText()); 
                                id=Integer.parseInt(user_did.getText());       
                                dout.writeInt(id); 
                                
                                msg=dis.readUTF();
                                user_name.setText("");
                                user_mail.setText("");
                                user_did.setText("");
                                reg_pass.setText(String.valueOf(msg));  
                                user_login.setVisible(true);
                               break;
                        
                            }
                            ActionListener click2=new ActionListener(){
                                public void actionPerformed(ActionEvent e)
                                { 
                                    cd.home();
                                    client_page.setVisible(false);
                                }
                            };
                            user_login.addActionListener(click2);
                     
                 
                        } catch (Exception eee) {
                            // TODO: handle exception
                        }
                       
                  }
                };
                sub_reg.addActionListener(click_reg);
                
       
                } catch (Exception e) {
                    System.out.println(e);
                }
         }

      static void login(int num)
      {
        
        try {
           
            Socket s=new Socket("localhost",8005);
            System.out.println("Client connected");
                      
            frame.setVisible(false);
            login_page=new JFrame("Login Page");
            login_page.setSize(400, 400);
            login_mail=new JLabel("Email:-");
            login_mail.setBounds(30, 50, 40, 30);
            login_m=new JTextField();
            login_m.setBounds(180, 50, 150, 30);
            login_pass=new JLabel("Pass:-");
            login_pass.setBounds(30, 90, 40, 30);
            login_p=new JTextField();
            login_p.setBounds(180, 90, 150, 30);
            login_msg=new JLabel("Output");
            login_msg.setBounds(150, 300,150, 30);
            JButton user_in=new JButton("Login");
            user_in.setBounds(150, 200, 110, 30);
            login_page.add(login_mail);login_page.add(login_pass);
            login_page.add(user_in); login_page.add(login_msg);
            login_page.add(login_m);login_page.add(login_p);
            login_page.setLayout(null);
            login_page.setVisible(true);
            
            ActionListener check=new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {   
                        try {
                           
                            String user_mail_data,user_pass_data;
                            user_mail_data=login_m.getText();
                            user_pass_data=login_p.getText();
                          
                            File myObj = new File("Userdata.txt");
                            Scanner read_file = new Scanner(myObj);
                            while(read_file.hasNextLine())
                            {
                                String data = read_file.nextLine();  
                          
                                if(data.contains(user_mail_data) && data.contains(user_pass_data))
                                {
                                    count_check++;
                                }
                            }
                          
                            if(count_check==1)
                                {
                                    login_msg.setText("Login Success ");
                                    login_m.setText("");
                                    login_p.setText("");
                                }
                            else if(count_check==0)
                                {
                                    login_msg.setText("Incorrect Details "); 
                                    login_m.setText("");
                                    login_p.setText("");                            
                                                    
                                }
                            read_file.close();

                        } catch (Exception es) {
                            // TODO: handle exception
                        }
                }
            };
            user_in.addActionListener(check);
           
            } catch (Exception e) {
                // TODO: handle exception
            }
      }   
    public static void main(String[] args) {
        Clientdata cd=new Clientdata();
        cd.home();
        
        
    }
}