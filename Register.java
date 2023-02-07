import java.util.*;
import java.lang.Math;   
import java.net.*;
import java.io.*;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; 
import javax.swing.*;
import java.awt.*;

class Register{

   static int register(String name,int id,String mail)
    {
        int pass[]=new int[8];
            int user_pass=0;
        try {
            
                System.out.println(name+"+"+mail+"+"+id);
                Random rn=new Random();
                
                for(int i=0;i<8;i++)
                {
                    pass[i]= rn.nextInt(9);
                }
                for(int a:pass)
                {
                    user_pass=(user_pass*10)+a;
                }
           System.out.println(user_pass);
           FileWriter fw = new FileWriter("Userdata.txt",true);
           fw.write(name+"+"+id+"+"+mail+"="+user_pass+"\n");           
           fw.close();              
              
                
        } catch (Exception e) {
            System.out.println(e);
        }
       return user_pass; 
    }
  
    public static void main(String[] args) {
        
        try {
            ServerSocket ss=new ServerSocket(8005); 
            String name,msg,mail;
            int id;
            while(true)
            {
                    Socket s=ss.accept();                   
                    System.out.println("Server Registration Running");

                    DataInputStream dis=new DataInputStream(s.getInputStream());  
                    DataOutputStream dout=new DataOutputStream(s.getOutputStream());

                    int fun=dis.readInt();
                    System.out.println(fun);
                    System.out.println("----------------");
                    if(fun==2)
                    {
                    
                        name=dis.readUTF();
                        mail=dis.readUTF();
                        id=dis.readInt();                   
                    
                        int password=register(name,id,mail);
                         dout.writeUTF("Your Registration Succefully & password is:-"+password);

                    }

                    else
                    {        
                        
                        String u_name=dis.readUTF();
                        System.out.println(u_name);

                        String password=dis.readUTF();
                        System.out.println(password);
                        
                        int x=login(password,u_name);
                        if(x>0)
                        {
                            String output="IN";        
                            dout.writeUTF(output); 
                            System.out.println("Homepage page ");
                        }
                        else
                        {
                            String output="OUT";
                            dout.writeUTF(output); 
                        }
                        
                    }

                    
                    

            }
           
        } catch (Exception e) {
            // TODO: handle exception
        }      
       
    
        
    }
}