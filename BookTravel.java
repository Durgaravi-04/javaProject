
package tourism;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BookTravel extends JFrame implements ActionListener {
    Choice travelChoice, acChoice, foodChoice;
    JTextField personsTextField, daysTextField;
    JLabel usernameLabel, idLabel, numberLabel, phoneLabel, totalPriceLabel;
    JButton checkPriceButton, bookButton, backButton;
    Connection c;

    public BookTravel(String username) {
        setBounds(370, 180, 1100, 600);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);


        checkPriceButton = new JButton("Check Price");
        checkPriceButton.setBackground(Color.BLUE);
        checkPriceButton.setForeground(Color.WHITE);
        checkPriceButton.setBounds(50, 490, 120, 30);
        checkPriceButton.addActionListener(this);
        add(checkPriceButton);

        bookButton = new JButton("Book");
        bookButton.setBackground(Color.BLUE);
        bookButton.setForeground(Color.WHITE);
        bookButton.setBounds(200, 490, 120, 30);
        bookButton.addActionListener(this);
        add(bookButton);

        backButton = new JButton("Back");
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.WHITE);
        backButton.setBounds(350, 490, 120, 30);
        backButton.addActionListener(this);
        add(backButton);

        // ImageIcon and JLabel setup code

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == checkPriceButton) {
            try {
                
                c=DriverManager.getConnection("jdbc:mysql:///tms","root","21x42");

                // Retrieve travel information from the database based on user's choice
                String selectedTravel = travelChoice.getSelectedItem();
                String query = "SELECT * FROM travel WHERE name=?";
                PreparedStatement preparedStatement = c.prepareStatement(query);
                preparedStatement.setString(1, selectedTravel);
                ResultSet rs = preparedStatement.executeQuery();

                if (rs.next()) {
                    int cost = rs.getInt("cost_per_day");
                    int foodCharges = rs.getInt("food_charges");
                    int acCharges = rs.getInt("ac_charges");

                    int persons = Integer.parseInt(personsTextField.getText());
                    int days = Integer.parseInt(daysTextField.getText());

                    String acChoiceValue = acChoice.getSelectedItem();
                    String foodChoiceValue = foodChoice.getSelectedItem();

                    int total = cost * persons * days;
                    if (acChoiceValue.equals("Yes")) {
                        total += acCharges * persons * days;
                    }
                    if (foodChoiceValue.equals("Yes")) {
                        total += foodCharges * persons * days;
                    }

                    totalPriceLabel.setText("Total Price: $" + total);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid choice.");
                }

                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == bookButton) {
            try {
                
                c=DriverManager.getConnection("jdbc:mysql:///tms","root","21x42");

                // Insert travel booking information into the database
                String username = usernameLabel.getText();
                String selectedTravel = travelChoice.getSelectedItem();
                int persons = Integer.parseInt(personsTextField.getText());
                int days = Integer.parseInt(daysTextField.getText());
                String acChoiceValue = acChoice.getSelectedItem();
                String foodChoiceValue = foodChoice.getSelectedItem();
                int totalPrice = Integer.parseInt(totalPriceLabel.getText().split("\\$")[1]);

                String query = "INSERT INTO booked_travel(username, travel_name, persons, days, ac_choice, food_choice, total_price) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = c.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, selectedTravel);
                preparedStatement.setInt(3, persons);
                preparedStatement.setInt(4, days);
                preparedStatement.setString(5, acChoiceValue);
                preparedStatement.setString(6, foodChoiceValue);
                preparedStatement.setInt(7, totalPrice);

                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Travel Booked Successfully");

                c.close();
                this.setVisible(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == backButton) {
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new BookTravel("SampleUsername").setVisible(true);
    }
}

