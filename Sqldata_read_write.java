import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class Register
{
    JFrame frame;
    JTextArea temail,tname;
    void reg()
    {
        frame = new JFrame("Home Page");
        JLabel email = new JLabel("User Email:-");
        JLabel name = new JLabel("User Name:-") ;
        temail = new JTextArea();
        tname = new JTextArea();
        JButton submit = new JButton("Submit");


        frame.setBounds(25,25,550,500);
        email.setBounds(10,10,100,15);
        temail.setBounds(10,30,518,30);
        name.setBounds(10,70,100,15);
        tname.setBounds(10,90,518,30);
        submit.setBounds(50,130,100,30);

        Color col = new Color(36,160,255,96);
        submit.setBackground(col);



        temail.setBorder(BorderFactory.createLineBorder(new Color(116,160,255,96)));
        tname.setBorder(BorderFactory.createLineBorder(new Color(116,160,255,96)));


        frame.add(email);
        frame.add(temail);
        frame.add(name);
        frame.add(tname);
        frame.add(submit);
        read();

        ActionListener click = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                call();
            }
        };
        submit.addActionListener(click);
        frame.setLayout(null);
        frame.setVisible(true);


    }

    int count(){
        int i=1;
       try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/social", "root", "12345");

           String datar = "select * from details";
           Statement st = con.createStatement();
           ResultSet rs = st.executeQuery(datar);

           while (rs.next()) { i++; }
       }catch (Exception a){
           System.out.println(a);
       }
        return i;
    }


    void call()
    {
        try {
            int i = 0, j = 0;
            String[] columns = new String[]{"Id", "Email", "Username", "Action"};

            Class.forName("com.mysql.cj.jdbc.Driver");

            //Connection tha mysql
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/social","root","12345");

            String mailv=temail.getText();
            String namev=tname.getText();
            int in = count();
            String num =  String.valueOf(in);
            System.out.println(num+"\t"+mailv+"\t"+namev);

            String insert= "Insert into details values('"+num+"','"+mailv+"','"+namev+"')";
            Statement st = con.createStatement();
            st.executeUpdate(insert);
            temail.setText("");
            tname.setText("");


            String datar = "select * from details";
            ResultSet rs = st.executeQuery(datar);



            JPanel panel = new JPanel();
            panel.setBounds(0, 170, 550, 300);
            panel.setBackground(Color.gray);




            String data[][] = new String[10][4];

            while (rs.next())
            {
                int id = rs.getInt("id");
                String emaila = rs.getString("user_mail");
                String namea =  rs.getString("user_name");

                data[i][j] = "               " + String.valueOf(id);
                data[i][j + 1] = emaila;
                data[i][j + 2] = namea;
                data[i][j + 3] = "action";

                i++;
            }

            JTable table = new JTable(data, columns);
            panel.add(new JScrollPane(table));
            panel.setVisible(true);
            frame.add(panel);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    void read()
    {
        

        try {
            int i = 0, j = 0;
            String[] columns = new String[]{"Id", "Email", "Username", "Action"};
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/social","root","12345");

           
            String datar = "select * from details";
            Statement sm=con.createStatement();
            ResultSet rs = sm.executeQuery(datar);



            JPanel panel = new JPanel();
            panel.setBounds(0, 170, 550, 300);
            panel.setBackground(Color.gray);




            String data[][] = new String[10][4];

            while (rs.next())
            {
                int id = rs.getInt("id");
                String emaila = rs.getString("user_mail");
                String namea =  rs.getString("user_name");

                data[i][j] = "               " + String.valueOf(id);
                data[i][j + 1] = emaila;
                data[i][j + 2] = namea;
                data[i][j + 3] = "action";

                i++;
            }

            JTable table = new JTable(data, columns);
            panel.add(new JScrollPane(table));
            panel.setVisible(true);
            frame.add(panel);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }



}

public class Sqldata_read_write {
    public static void main(String[] args) {

       Register obj = new Register();
       obj.reg();
    }
}
