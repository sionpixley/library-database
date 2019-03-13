package idk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AuthorForm implements ActionListener
{
    /* window for the author form */
    private JFrame author_frame;

    /* submit button */
    private JButton author_submit_button;

    /* fields for the author_frame window */
    private JTextField author_book_id_field, author_author_name_field;

    /* creates window to enter author info */
    public AuthorForm()
    {
        author_frame = new JFrame("Author Form");
        author_frame.setSize(500, 500);
        author_frame.setLayout(null);
        author_frame.getContentPane().setBackground(new Color(169, 169, 169));
        author_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        author_book_id_field = new JTextField("Book ID");
        author_book_id_field.setBounds(175, 50, 150, 20);

        author_author_name_field = new JTextField("Author Name");
        author_author_name_field.setBounds(175, 100, 150, 20);

        author_submit_button = new JButton("SUBMIT");
        author_submit_button.setBounds(175, 150, 150, 20);
        author_submit_button.addActionListener(this);

        author_frame.add(author_book_id_field);
        author_frame.add(author_author_name_field);
        author_frame.add(author_submit_button);

        author_frame.setVisible(true);
    }

    /* inserts author info into MySQL */
    private void author_query(String book_id, String author_name)
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
            stmt.executeUpdate("insert into LIBRARY.BOOK_AUTHORS(book_id, author_name) " +
                    "values (" + book_id + ", '" + author_name + "')");

            conn.close();
            stmt.close();
        }
        catch(SQLException se)
        {
            System.out.println("Error");
            return;
        }
    }

    /* determines if the button was pressed */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == author_submit_button)
        {
            String book_id = author_book_id_field.getText();
            String author_name = author_author_name_field.getText();

            author_frame.dispose();
            author_query(book_id, author_name);
            new LibraryForm();
        }
    }
}
