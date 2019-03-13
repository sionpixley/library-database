package idk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CopiesForm implements ActionListener
{
    /* window for the copies form */
    private JFrame copies_frame;

    /* submit button */
    private JButton copies_submit_button;

    /* fields for the copies_frame window */
    private JTextField copies_book_id_field, copies_branch_id_field, copies_num_copies_field;

    /* creates the window to enter copies info */
    public CopiesForm()
    {
        copies_frame = new JFrame("Copies Form");
        copies_frame.setSize(500, 500);
        copies_frame.setLayout(null);
        copies_frame.getContentPane().setBackground(new Color(169, 169, 169));
        copies_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        copies_book_id_field = new JTextField("Book ID");
        copies_book_id_field.setBounds(175, 50, 150, 20);

        copies_branch_id_field = new JTextField("Branch ID");
        copies_branch_id_field.setBounds(175, 100, 150, 20);

        copies_num_copies_field = new JTextField("Number of Copies");
        copies_num_copies_field.setBounds(175, 150, 150, 20);

        copies_submit_button = new JButton("SUBMIT");
        copies_submit_button.setBounds(175, 200, 150, 20);
        copies_submit_button.addActionListener(this);

        copies_frame.add(copies_book_id_field);
        copies_frame.add(copies_branch_id_field);
        copies_frame.add(copies_num_copies_field);
        copies_frame.add(copies_num_copies_field);
        copies_frame.add(copies_submit_button);

        copies_frame.setVisible(true);
    }

    /* inserts copies info into MySQL */
    private void copies_query(String book_id, String branch_id, String num_copies)
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
            stmt.executeUpdate("insert into LIBRARY.BOOK_COPIES(book_id, branch_id, num_copies) " +
                    "values (" + book_id + ", " + branch_id + ", " + num_copies + ")");

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
        if(e.getSource() == copies_submit_button)
        {
            String book_id = copies_book_id_field.getText();
            String branch_id = copies_branch_id_field.getText();
            String num_copies = copies_num_copies_field.getText();

            copies_frame.dispose();
            copies_query(book_id, branch_id, num_copies);
            new LibraryForm();
        }
    }
}
