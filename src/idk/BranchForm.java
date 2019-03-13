package idk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BranchForm implements ActionListener
{
    /* window for branch form */
    private JFrame branch_frame;

    /* submit button */
    private JButton branch_submit_button;

    /* fields for the branch_frame window */
    private JTextField branch_branch_id_field, branch_name_field, branch_address_field;

    /* creates window to enter branch info */
    public BranchForm()
    {
        branch_frame = new JFrame("Branch Form");
        branch_frame.setSize(500, 500);
        branch_frame.setLayout(null);
        branch_frame.getContentPane().setBackground(new Color(169, 169, 169));
        branch_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        branch_branch_id_field = new JTextField("Branch ID");
        branch_branch_id_field.setBounds(175, 50, 150, 20);

        branch_name_field = new JTextField("Branch Name");
        branch_name_field.setBounds(175, 100, 150, 20);

        branch_address_field = new JTextField("Address");
        branch_address_field.setBounds(175, 150, 150, 20);

        branch_submit_button = new JButton("SUBMIT");
        branch_submit_button.setBounds(175, 200, 150, 20);
        branch_submit_button.addActionListener(this);

        branch_frame.add(branch_branch_id_field);
        branch_frame.add(branch_name_field);
        branch_frame.add(branch_address_field);
        branch_frame.add(branch_submit_button);

        branch_frame.setVisible(true);
    }

    /* inserts branch info into MySQL */
    private void branch_query(String branch_id, String name, String address)
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
            stmt.executeUpdate("insert into LIBRARY.BRANCH(branch_id, name, address) " +
                    "values (" + branch_id + ", '" + name + "', '" + address + "')");

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
        if(e.getSource() == branch_submit_button)
        {
            String branch_id = branch_branch_id_field.getText();
            String name = branch_name_field.getText();
            String address = branch_address_field.getText();

            branch_frame.dispose();
            branch_query(branch_id, name, address);
            new LibraryForm();
        }
    }
}
