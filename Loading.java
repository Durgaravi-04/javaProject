package tourism;

import javax.swing.*;
import java.awt.*;

public class Loading extends JFrame implements Runnable{
    JProgressBar p;
    Thread t;
    String username;
    Loading(String user){
        username=user;
        t=new Thread(this);
        setBounds(500,250,650,400);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        JLabel l1=new JLabel("Travel And Tourism Application");
        l1.setBounds(50,20,600,40);
        l1.setFont(new Font("Raleway",Font.BOLD,35));
        l1.setForeground(Color.BLUE);
        add(l1);

        p=new JProgressBar();
        p.setStringPainted(true);
        p.setBounds(150,100,300,25);
        add(p);

        JLabel l2=new JLabel("Please Wait...");
        l2.setBounds(255,130,120,25);
        l2.setFont(new Font("Tahoma",Font.BOLD,16));
        l2.setForeground(Color.RED);
        add(l2);

        JLabel l3=new JLabel("Welcome "+username);
        l3.setBounds(20,310,400,25);
        l3.setFont(new Font("Tahoma",Font.BOLD,16));
        l3.setForeground(Color.RED);
        add(l3);

        t.start();
    }
    public void run(){
        try{
            for(int i=1;i<=101;i++){
                int m=p.getMaximum();
                int n=p.getValue();
                if(n<m){
                    p.setValue(p.getValue()+1);
                }else{
                    i=101;
                    setVisible(false);
                    new dashboard(username).setVisible(true);
                    //Home
                }
                Thread.sleep(50);
            }
        }catch (Exception e){

        }
    }
    public static void main(String[] args){
        new Loading("").setVisible(true);
    }
}
