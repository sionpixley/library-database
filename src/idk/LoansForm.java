package idk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoansForm implements ActionListener
{
    /* window for loans form */
    private JFrame loans_frame;

    /* submit button */
    private JButton loans_submit_button;

    /* fields for the loans_frame window */
    private JTextField loans_book_id_field, loans_branch_id_field, loans_card_num_field, loans_date_out_field;
    private JTextField loans_due_date_field;

    /* creates window to enter loans info */
    public LoansForm()
    {
        loans_frame = new JFrame("Loans Form");
        loans_frame.setSize(500, 500);
        loans_frame.setLayout(null);
        loans_frame.getContentPane().setBackground(new Color(169, 169, 169));
        loans_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loans_book_id_field = new JTextField("Book ID");
        loans_book_id_field.setBounds(175, 50, 150, 20);

        loans_branch_id_field = new JTextField("Branch ID");
        loans_branch_id_field.setBounds(175, 100, 150, 20);

        loans_card_num_field = new JTextField("Card Number");
        loans_card_num_field.setBounds(175, 150, 150, 20);

        loans_date_out_field = new JTextField("Date Out");
        loans_date_out_field.setBounds(175, 200, 150, 20);

        loans_due_date_field = new JTextField("Due Date");
        loans_due_date_field.setBounds(175, 250, 150, 20);

        loans_submit_button = new JButton("SUBMIT");
        loans_submit_button.setBounds(175, 300, 150, 20);
        loans_submit_button.addActionListener(this);

        loans_frame.add(loans_book_id_field);
        loans_frame.add(loans_branch_id_field);
        loans_frame.add(loans_card_num_field);
        loans_frame.add(loans_date_out_field);
        loans_frame.add(loans_due_date_field);
        loans_frame.add(loans_submit_button);

        loans_frame.setVisible(true);
    }

    /* inserts loans info into MySQL */
    private void loans_query(String book_id, String branch_id, String card_num, String date_out, String due_date)
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
            stmt.executeUpdate("insert into LIBRARY.BOOK_LOANS(book_id, branch_id, card_num, date_out, due_date) " +
                    "values (" + book_id + ", " + branch_id + ", " + card_num + ", '" + date_out + "', '" + due_date + "')");

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
        if(e.getSource() == loans_submit_button)
        {
            String book_id = loans_book_id_field.getText();
            String branch_id = loans_branch_id_field.getText();
            String card_num = loans_card_num_field.getText();
            String date_out = loans_date_out_field.getText();
            String due_date = loans_due_date_field.getText();

            loans_frame.dispose();
            loans_query(book_id, branch_id, card_num, date_out, due_date);
            new LibraryForm();
        }
    }
}
