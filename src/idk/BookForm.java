package idk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BookForm implements ActionListener
{
    /* window for the book form */
    private JFrame book_frame;

    /* submit button */
    private JButton book_submit_button;

    /* fields for the book_frame window */
    private JTextField book_book_id_field, book_title_field, book_publisher_field;

    /* creates window to enter book info */
    public BookForm()
    {
        book_frame = new JFrame("Book Form");
        book_frame.setSize(500, 500);
        book_frame.setLayout(null);
        book_frame.getContentPane().setBackground(new Color(169, 169, 169));
        book_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        book_book_id_field = new JTextField("Book ID");
        book_book_id_field.setBounds(175, 50, 150, 20);

        book_title_field = new JTextField("Book Title");
        book_title_field.setBounds(175, 100, 150, 20);

        book_publisher_field = new JTextField("Book Publisher");
        book_publisher_field.setBounds(175, 150, 150, 20);

        book_submit_button = new JButton("SUBMIT");
        book_submit_button.setBounds(175, 200, 150, 20);
        book_submit_button.addActionListener(this);

        book_frame.add(book_book_id_field);
        book_frame.add(book_title_field);
        book_frame.add(book_publisher_field);
        book_frame.add(book_submit_button);

        book_frame.setVisible(true);
    }

    /* inserts book info into MySQL */
    private void book_query(String book_id, String title, String publisher_name)
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
            stmt.executeUpdate("insert into LIBRARY.BOOK(book_id, title, publisher_name) " +
                    "values (" + book_id + ", '" + title + "', '" + publisher_name + "')");

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
        if(e.getSource() == book_submit_button)
        {
            String book_id = book_book_id_field.getText();
            String title = book_title_field.getText();
            String publisher_name = book_publisher_field.getText();

            book_frame.dispose();
            book_query(book_id, title, publisher_name);
            new LibraryForm();
        }
    }
}
