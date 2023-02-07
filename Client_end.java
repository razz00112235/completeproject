import javax.sound.midi.Soundbank;
import javax.sound.sampled.SourceDataLine;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;  
import java.net.*;
import java.util.Scanner;  
import java.text.SimpleDateFormat;  
import java.util.Date;
public class Client_end{
static JFrame frame_main;
static JPanel server;
static JLabel lb2,lb3;
static ServerSocket se;
static Socket s;
static Scanner sc1 = new Scanner(System.in);
static Scanner sca;
static  PrintWriter ps;
static String str="",str1="";
static int i=110,msg_size=10;
static JButton send,recv_data;
static JTextField sendtext,receivetext;
static JTextArea tx2;
static JLabel msg,msg2;
static void home()
    {
        
        Color textcolor=new Color(247,232,227);
        frame_main=new JFrame("Client Chat Box");
        Image icon = Toolkit.getDefaultToolkit().getImage("wht.PNG");  
        frame_main.setIconImage(icon);  
        frame_main.setBounds(500, 0, 400, 400);
       // frame_main.setSize(400, 400);
        frame_main.getContentPane().setBackground(textcolor);
        sendtext=new JTextField();
        sendtext.setBounds(5, 320, 310, 40);
        
        send=new JButton("Send");
        send.setBounds(315, 320, 70, 40);       
        Color btclor=new Color(163,36,103); 
        send.setBackground(btclor);
        send.setForeground(Color.WHITE);

       
        frame_main.add(sendtext);
        frame_main.add(send);
     
        frame_main.setLayout(null);
        frame_main.setVisible(true);
        
        ActionListener sent_msg=new ActionListener(){
            public void actionPerformed(ActionEvent e)
            { 
                send_msg();
            }
        };
        send.addActionListener(sent_msg);  
        client_conn(); 
    }
    public static void main(String[] args) {
      home();
      

    }

    static void client_conn()
    {
        try {
            
            s  = new Socket(InetAddress.getLocalHost(),8035);
            System.out.println("Connected to server");

            sca = new Scanner(s.getInputStream());
            ps = new PrintWriter(s.getOutputStream(),true);
            rec_msg();
        } catch (Exception e) {
           System.out.println(e);
        }       
    }

    static public void send_msg()
    {      Thread sendmsg = new Thread(new Runnable(){
                        public void run(){   
                            

                            str1= sendtext.getText();
                            ps.println(str1);                             
                            SimpleDateFormat time_hour = new SimpleDateFormat("HH"); 
                            SimpleDateFormat time_min = new SimpleDateFormat("mm"); 
                            Date date = new Date();  
                            String hr=time_hour.format(date); String mm=time_min.format(date);
                            String msg_tm="";
                            if(Integer.parseInt(hr)>11 && Integer.parseInt(mm)>58)
                            {
                                hr=hr.concat(":"+mm+" am");
                                msg_tm=msg_tm.concat(hr);
                            }
                            else
                            {
                                hr=hr.concat(":"+mm+" pm");
                                msg_tm=msg_tm.concat(hr);
                            }
                            if(!str1.equals(""))
                            {
                                msg2=new JLabel("");
                                msg2.setText("<html><div style='height:20px'><p style='text-align:left;width:100%;font-size:10px' >"+"Me:-"+"\n"+str1+"</p>"+"<p style='text-align:right;width:100%;font:size:2px'>"+msg_tm+"</p></div></html>");
                                msg2.setBounds(0, msg_size, 350, 40);
                                msg2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                                frame_main.add(msg2);                   
                                frame_main.revalidate();
                                frame_main.repaint();                    
                                msg_size=msg_size+40;
                                sendtext.setText(""); 
                            }
                                          
                           
                        }
                    });
                    sendmsg.start();
                     
    }

   static public void rec_msg()
    {
       
                    Thread recievemsg = new Thread(new Runnable(){
                        public void run(){ 
                           
                            while(true)
                            {
                                str = sca.nextLine();  
                                SimpleDateFormat time_hour = new SimpleDateFormat("HH"); 
                                SimpleDateFormat time_min = new SimpleDateFormat("mm"); 
                                Date date = new Date();  
                                String hr=time_hour.format(date); String mm=time_min.format(date);
                                String msg_tm="";
                                if(Integer.parseInt(hr)>11 && Integer.parseInt(mm)>58)
                                {
                                    hr=hr.concat(":"+mm+" am");
                                    msg_tm=msg_tm.concat(hr);
                                }
                                else
                                {
                                    hr=hr.concat(":"+mm+" pm");
                                    msg_tm=msg_tm.concat(hr);
                                }
                                if(!str.equals(""))
                                {
                                    msg=new JLabel("");
                                    msg.setText("<html><div style='height:20px'><p style='text-align:right;width:100%;font-size:10px' >"+"Server:-\n"+str+"</p>"+"<p style='text-align:right;width:100%;font:size:2px'>"+msg_tm+"</label></div></html>");
                                    msg.setBounds(0, msg_size, 350, 40);
                                    msg.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                                    frame_main.add(msg);
                                    frame_main.revalidate();
                                    frame_main.repaint();
                                    msg_size=msg_size+40;
                                    if(str.equals("Bye")){break;}   
                                }   
                                                                                                                            
                            
                             
                            }
                                  
                                  
                        }
                    });
                    recievemsg.start();  
                  
    }

}