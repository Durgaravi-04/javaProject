package tourism;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.sql.ResultSet;

import javax.swing.*;

public class ViewCustomer extends JFrame implements ActionListener{
    JButton b2;
    private static final long serialVersionUID = 1L;

    ViewCustomer(String username)
    {
        setBounds(480,180,866,630);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel l1 = new JLabel("Username : ");
        l1.setBounds(30,50,150,25);
        add(l1);

        JLabel l11 = new JLabel();
        l11.setBounds(220,50,150,25);
        add(l11);

        JLabel l2 = new JLabel("ID : ");
        l2.setBounds(30,110,150,25);
        add(l2);

        JLabel l12 = new JLabel();
        l12.setBounds(220,110,150,25);
        add(l12);

        JLabel l3 = new JLabel("Number : ");
        l3.setBounds(30,170,150,25);
        add(l3);

        JLabel l13 = new JLabel();
        l13.setBounds(220,170,150,25);
        add(l13);

        JLabel l4 = new JLabel("Name : ");
        l4.setBounds(30,230,150,25);
        add(l4);

        JLabel l14 = new JLabel();
        l14.setBounds(220,230,150,25);
        add(l14);

        JLabel l5 = new JLabel("Gender : ");
        l5.setBounds(30,290,150,25);
        add(l5);

        JLabel l15 = new JLabel();
        l15.setBounds(220,290,150,25);
        add(l15);

        JLabel l6 = new JLabel("Country : ");
        l6.setBounds(500,50,150,25);
        add(l6);

        JLabel l16 = new JLabel();
        l16.setBounds(690,50,150,25);
        add(l16);

        JLabel l7 = new JLabel("Address : ");
        l7.setBounds(500,110,150,25);
        add(l7);

        JLabel l17 = new JLabel();
        l17.setBounds(690,110,150,25);
        add(l17);

        JLabel l8 = new JLabel("Phone : ");
        l8.setBounds(500,170,150,25);
        add(l8);

        JLabel l18 = new JLabel();
        l18.setBounds(690,170,150,25);
        add(l18);

        JLabel l9 = new JLabel("Email : ");
        l9.setBounds(500,230,150,25);
        add(l9);

        JLabel l19 = new JLabel();
        l19.setBounds(690,230,150,25);
        add(l19);

        b2=new JButton("Back");
        b2.setBounds(350,350,100,25);
        b2.setForeground(Color.WHITE);
        b2.setBackground(Color.BLACK);
        b2.addActionListener(this);
        add(b2);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("tourism/icons/viewall.jpg"));
        Image i2=i1.getImage().getScaledInstance(620,200,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l10 = new JLabel(i3);
        l10.setBounds(20,390,600,250);
        add(l10);

        ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("tourism/icons/viewall.jpg"));
        Image i5=i4.getImage().getScaledInstance(620,200,Image.SCALE_DEFAULT);
        ImageIcon i6=new ImageIcon(i5);
        JLabel l20 = new JLabel(i6);
        l20.setBounds(600,390,600,250);
        add(l20);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where username = '"+username+"'");
            while(rs.next()) {
                l11.setText(rs.getString("username"));
                l12.setText(rs.getString("id"));
                l13.setText(rs.getString("number"));
                l14.setText(rs.getString("name"));
                l15.setText(rs.getString("gender"));
                l16.setText(rs.getString("country"));
                l17.setText(rs.getString("address"));
                l18.setText(rs.getString("phone"));
                l19.setText(rs.getString("email"));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource()==b2) {
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new ViewCustomer("").setVisible(true);
    }

}
