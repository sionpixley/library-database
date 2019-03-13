package idk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BorrowerForm implements ActionListener
{
    /* window for borrower form */
    private JFrame borrower_frame;

    /* submit button */
    private JButton borrower_submit_button;

    /* fields for the borrower_frame window */
    private JTextField borrower_card_num_field, borrower_name_field, borrower_address_field, borrower_phone_field;

    /* creates window to enter borrower info */
    public BorrowerForm()
    {
        borrower_frame = new JFrame("Borrower Form");
        borrower_frame.setSize(500, 500);
        borrower_frame.setLayout(null);
        borrower_frame.getContentPane().setBackground(new Color(169, 169, 169));
        borrower_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        borrower_card_num_field = new JTextField("Card Number");
        borrower_card_num_field.setBounds(175, 50, 150, 20);

        borrower_name_field = new JTextField("Name");
        borrower_name_field.setBounds(175, 100, 150, 20);

        borrower_address_field = new JTextField("Address");
        borrower_address_field.setBounds(175, 150,150, 20);

        borrower_phone_field = new JTextField("Phone Number");
        borrower_phone_field.setBounds(175, 200, 150, 20);

        borrower_submit_button = new JButton("SUBMIT");
        borrower_submit_button.setBounds(175, 250, 150, 20);
        borrower_submit_button.addActionListener(this);

        borrower_frame.add(borrower_card_num_field);
        borrower_frame.add(borrower_name_field);
        borrower_frame.add(borrower_address_field);
        borrower_frame.add(borrower_phone_field);
        borrower_frame.add(borrower_submit_button);

        borrower_frame.setVisible(true);
    }

    /* inserts borrower info into MySQL */
    private void borrower_query(String card_num, String name, String address, String phone)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException cnfe)
        {
            System.out.println("Driver not loaded");
            return;
        }

        try
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost", "root", "password");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into LIBRARY.BORROWER(card_num, name, address, phone) " +
                    "values (" + card_num + ", '" + name + "', '" + address + "', '" + phone + "')");

            conn.close();
            stmt.close();
        }
        catch(SQLException se)
        {
            System.out.println("Error");
            return;
        }
    }

    /* determines if button was pressed */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == borrower_submit_button)
        {
            String card_num = borrower_card_num_field.getText();
            String name = borrower_name_field.getText();
            String address = borrower_address_field.getText();
            String phone = borrower_phone_field.getText();

            borrower_frame.dispose();
            borrower_query(card_num, name, address, phone);
            new LibraryForm();
        }
    }
}
