package idk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PublisherForm implements ActionListener
{
    /* window for the publisher form */
    private JFrame publisher_frame;

    /* submit button */
    private JButton publisher_submit_button;

    /* fields for the publisher_frame window */
    private JTextField publisher_name_field, publisher_address_field, publisher_phone_field;

    /* creates window to enter publisher info */
    public PublisherForm()
    {
        publisher_frame = new JFrame("Publisher Form");
        publisher_frame.setSize(500, 500);
        publisher_frame.setLayout(null);
        publisher_frame.getContentPane().setBackground(new Color(169, 169, 169));
        publisher_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        publisher_name_field = new JTextField("Publisher Name");
        publisher_name_field.setBounds(175, 50, 150, 20);

        publisher_address_field = new JTextField("Address");
        publisher_address_field.setBounds(175, 100, 150, 20);

        publisher_phone_field = new JTextField("Phone Number");
        publisher_phone_field.setBounds(175, 150, 150, 20);

        publisher_submit_button = new JButton("SUBMIT");
        publisher_submit_button.setBounds(175, 200, 150, 20);
        publisher_submit_button.addActionListener(this);

        publisher_frame.add(publisher_name_field);
        publisher_frame.add(publisher_address_field);
        publisher_frame.add(publisher_phone_field);
        publisher_frame.add(publisher_submit_button);

        publisher_frame.setVisible(true);
    }

    /* inserts publisher info into MySQL */
    private void publisher_query(String name, String address, String phone)
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
            stmt.executeUpdate("insert into LIBRARY.PUBLISHER(name, address, phone) " +
                    "values ('" + name + "', '" + address + "', '" + phone + "')");

            conn.close();
            stmt.close();
        }
        catch(SQLException se)
        {
            System.out.println("Error");
            return;
        }
    }

    /*determines if button was pressed */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == publisher_submit_button)
        {
            String name = publisher_name_field.getText();
            String address = publisher_address_field.getText();
            String phone = publisher_phone_field.getText();

            publisher_frame.dispose();
            publisher_query(name, address, phone);
            new LibraryForm();
        }
    }
}
