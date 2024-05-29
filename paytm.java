package tourism;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class paytm extends JFrame implements ActionListener {
    paytm(){
        JEditorPane j= new JEditorPane();
        j.setEditable(false);
        try {
            j.setPage("https://paytm.com/credit-card-bill-payment");
        }catch(Exception e) {
            j.setContentType("text/html");
            j.setText("<html>Could not load, Error 404<html>");
        }
        JScrollPane js= new JScrollPane(j);
        getContentPane().add(js);

        JButton b1 = new JButton("Back");
        b1.setBounds(450,20,80,40);
        b1.addActionListener(this);
        j.add(b1);

        setBounds(500,190,800,600);
    }
    public void actionPerformed(ActionEvent ae)
    {
        this.setVisible(false);
        new Payment().setVisible(true);
    }
    public static void main(String[] args) {
        new paytm().setVisible(true);
    }

}
