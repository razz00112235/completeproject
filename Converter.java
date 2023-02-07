import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Converter{
 static void curreny_change()
 {
    JLabel input_msg,output_msg;
    JTextField input_data;
    JButton inr,dollr;
    JFrame frame=new JFrame("Converter Currency/Temperature");
        frame.setSize(400, 500);
        
        input_msg=new JLabel("Enter Amount");
        input_msg.setBounds(50, 50, 100, 30);

        input_data=new JTextField();
        input_data.setBounds(150, 50, 200, 30);

        inr=new JButton("Convert INR");
        inr.setBounds(50, 150, 120, 30);

        dollr=new JButton("Convert Doller");
        dollr.setBounds(220, 150, 120, 30);

        output_msg=new JLabel("Output:-");
        output_msg.setBounds(100, 220, 150, 30);
        frame.add(input_msg);frame.add(input_data);
        frame.add(inr); frame.add(dollr);frame.add(output_msg);
        frame.setLayout(null);
        frame.setVisible(true);
        ActionListener click=new ActionListener(){
            public void actionPerformed(ActionEvent e)
            { 
                Double data;
                data=Double.parseDouble(input_data.getText());
                data=data*81.70;
                output_msg.setText("INR:- "+String.valueOf(data)+" RS");

            }
        };
        ActionListener change_dollr=new ActionListener(){
            public void actionPerformed(ActionEvent e)
            { 
                Double data;
                data=Double.parseDouble(input_data.getText());
                data=data/81.70;
                output_msg.setText("Dollar:- "+String.valueOf(data)+" $");

            }
        };
        inr.addActionListener(click);
        dollr.addActionListener(change_dollr);
       
 }
    public static void main(String[] args) {
        curreny_change();
    }
}