package idk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LibraryForm implements ActionListener
{
    /* initial window to select the various forms */
    private JFrame initial_frame;

    /* buttons for initial window to navigate to the different forms */
    private JButton new_book_button, new_author_button, new_publisher_button, new_copies_button, new_loans_button;
    private JButton new_branch_button, new_borrower_button;

    /* creates the initial window */
    public LibraryForm()
    {
        initial_frame = new JFrame("Library Forms");
        initial_frame.setSize(500, 500);
        initial_frame.setLayout(null);
        initial_frame.getContentPane().setBackground(new Color(169,169, 169));
        initial_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new_book_button = new JButton("New Book");
        new_book_button.setBounds(175, 50, 150, 20);
        new_book_button.addActionListener(this);

        new_author_button = new JButton("New Author");
        new_author_button.setBounds(175, 100, 150, 20);
        new_author_button.addActionListener(this);

        new_publisher_button = new JButton("New Publisher");
        new_publisher_button.setBounds(175, 150, 150, 20);
        new_publisher_button.addActionListener(this);

        new_copies_button = new JButton("New Copies");
        new_copies_button.setBounds(175, 200, 150, 20);
        new_copies_button.addActionListener(this);

        new_loans_button = new JButton("New Loans");
        new_loans_button.setBounds(175, 250, 150, 20);
        new_loans_button.addActionListener(this);

        new_branch_button = new JButton("New Branch");
        new_branch_button.setBounds(175, 300, 150, 20);
        new_branch_button.addActionListener(this);

        new_borrower_button = new JButton("New Borrower");
        new_borrower_button.setBounds(175, 350, 150, 20);
        new_borrower_button.addActionListener(this);

        initial_frame.add(new_book_button);
        initial_frame.add(new_author_button);
        initial_frame.add(new_publisher_button);
        initial_frame.add(new_copies_button);
        initial_frame.add(new_loans_button);
        initial_frame.add(new_branch_button);
        initial_frame.add(new_borrower_button);

        initial_frame.setVisible(true);
    }

    /* finds what button was pressed and does a specific action */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == new_book_button)
        {
            initial_frame.dispose();
            new BookForm();
        }
        else if(e.getSource() == new_author_button)
        {
            initial_frame.dispose();
            new AuthorForm();
        }
        else if(e.getSource() == new_publisher_button)
        {
            initial_frame.dispose();
            new PublisherForm();
        }
        else if(e.getSource() == new_copies_button)
        {
            initial_frame.dispose();
            new CopiesForm();
        }
        else if(e.getSource() == new_loans_button)
        {
            initial_frame.dispose();
            new LoansForm();
        }
        else if(e.getSource() == new_branch_button)
        {
            initial_frame.dispose();
            new BranchForm();
        }
        else if(e.getSource() == new_borrower_button)
        {
            initial_frame.dispose();
            new BorrowerForm();
        }
    }
}
