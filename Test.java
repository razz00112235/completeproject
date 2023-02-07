import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.FileReader;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JRadioButton;
class Test{
static int j=0,i;  
static int data=0;
static int cnext=0,marks=0,qust,qust_count=1;
static String qt,obj1,obj2,obj3,obj4,ans;  
static JRadioButton op1,op2,op3,op4;
static ButtonGroup G;
static JLabel result,mark_show;
static JButton next;
static JLabel Question,answr,lb_top,user_answr;
    public static void main(String[] args) {
             
           
        JFrame frame=new JFrame("MCQ Test");
        frame.setSize(500,600);

        JButton btn_val=new JButton("0");
        btn_val.setBounds(300,300,150,50);
        btn_val.setVisible(false);
        lb_top=new JLabel(); 
        lb_top.setBounds(300, 10, 200, 20);  
             
        JSONParser parser=new JSONParser();    
                     try { 
                              

                           
                            G = new ButtonGroup();
                            Object obj=parser.parse(new FileReader("test.json")); 
                            JSONArray arr=(JSONArray)obj; 
                            
                            btn_val.setText(String.valueOf(cnext));                           
                               lb_top.setText("Total Questions: "+arr.size());
                               mark_show=new JLabel("Your Marks"+marks);
                               mark_show.setBounds(100, 400, 100, 50);
                               mark_show.setVisible(false);
                               JSONObject objdata=(JSONObject)arr.get(0);                     
                                qt=(String)objdata.get("Question");
                                obj1=(String)objdata.get("A");
                                obj2=(String)objdata.get("B");
                                obj3=(String)objdata.get("C");
                                obj4=(String)objdata.get("D");
                                ans=(String)objdata.get("ANS"); 
                               
                  
                                Question=new JLabel(qust_count+". "+qt);
                                Question.setBounds(10, 10, 400, 50);
                        
                                op1=new JRadioButton(obj1);
                                op1.setBounds(10, 60, 300, 50);
                                op2=new JRadioButton(obj2);
                                op2.setBounds(10, 110, 300, 50);
                                op3=new JRadioButton(obj3);
                                op3.setBounds(10, 160, 300, 50);
                                op4=new JRadioButton(obj4);
                                op4.setBounds(10, 210, 300, 50);
                                answr=new JLabel(ans);
                                answr.setBounds(10, 260, 150, 50);
                                user_answr=new JLabel();
                                user_answr.setBounds(300, 260, 150, 50);
                                next=new JButton("Next");
                                next.setBounds(150,300,150,50);
                                G.add(op1);G.add(op2);G.add(op3);G.add(op4);
                                
                                
                                user_answr.setVisible(false);
                                answr.setVisible(false);
                                frame.add(Question);frame.add(op1);frame.add(op2);
                                frame.add(op3);frame.add(op4);frame.add(answr);frame.add(next);
                                frame.add(btn_val);frame.add(user_answr);
                                frame.add(mark_show);
                                frame.setLayout(null);
                                frame.setVisible(true);

                                ActionListener click=new ActionListener(){
                                    public void actionPerformed(ActionEvent e)
                                    { 
                                        G.clearSelection();
                                        if(user_answr.getText().equals(answr.getText()))
                                            {
                                                marks++;
                                                mark_show.setText(String.valueOf("Your Marks:-"+marks));                                          
                                           
                                        }
                                            
                                        JSONParser parser=new JSONParser();    
                                         try {
                                                Object obj=parser.parse(new FileReader("test.json")); 
                                                JSONArray arr=(JSONArray)obj; 
                                                
                                                cnext++;
                                                btn_val.setText(String.valueOf(cnext));
                                                qust_count++;               
                                                   int data=Integer.parseInt(btn_val.getText());
                                                   JSONObject objdata=(JSONObject)arr.get(data);                     
                                                    qt=(String)objdata.get("Question");
                                                    obj1=(String)objdata.get("A");
                                                    obj2=(String)objdata.get("B");
                                                    obj3=(String)objdata.get("C");
                                                    obj4=(String)objdata.get("D");
                                                    ans=(String)objdata.get("ANS"); 
                                                    Question.setText(qust_count+". "+qt); 
                                                    op1.setText(obj1);  
                                                    op2.setText(obj2); 
                                                    op3.setText(obj3); 
                                                    op4.setText(obj4);         
                                                    answr.setText(ans);
                                    
                                                            

                                } catch (Exception ee) {
                                   System.out.println(ee);
                                }      
                                     
                                if(btn_val.getText().equals("9"))
                                {
                                    next.setText("Submit");
                                }
                                if(btn_val.getText().equals("10"))
                                                    {
                                                        next.setVisible(false);
                                                        Question.setVisible(false); 
                                                        op1.setVisible(false); 
                                                        op2.setVisible(false); 
                                                        op3.setVisible(false); 
                                                        op4.setVisible(false); 
                                                        answr.setVisible(false); 
                                                        lb_top.setVisible(false);
                                                        user_answr.setVisible(false);
                                                        result=new JLabel("Total Marks:- "+arr.size()+"\nYour Marks:- "+marks);
                                                        result.setBounds(150, 200, 200, 50);
                                                        result.setFont(new Font("Arial", Font.PLAIN, 10));
                                                        frame.add(result);
                                                    }
                               
                                

                                    }
                                    
                                };
                                qust=Integer.parseInt(btn_val.getText());
                           
                                next.addActionListener(click);
                                frame.add(lb_top);   
                                
                            
            } catch (Exception ee) {
               System.out.println(ee);
            } 
            ActionListener opcheck=new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    if(op1.isSelected()){    
                        user_answr.setText(op1.getText());
                      } 
                      else if(op2.isSelected()){    
                        user_answr.setText(op2.getText());
                        } 
                     else if(op3.isSelected()){    
                        user_answr.setText(op3.getText());
                       } 
                       else if(op4.isSelected()){    
                        user_answr.setText(op4.getText());
                      }                     
                }
            };
            op1.addActionListener(opcheck);
            op2.addActionListener(opcheck);
            op3.addActionListener(opcheck);
            op4.addActionListener(opcheck);     
       
         
    }
    
}