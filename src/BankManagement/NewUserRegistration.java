package BankManagement;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

public class NewUserRegistration implements ActionListener
{
    private JFrame frame;

    private JLabel heading, loginAccountNumber, loginPassword;
    private JTextField forLoginAccountNumber, forLoginPassword;
    private JButton submit;

    NewUserRegistration()
    {
        frame = new JFrame("NEW REGISTRATION PAGE");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.setLayout(null);

        heading = new JLabel("NEW USER RESISTRATION ");
        heading.setHorizontalAlignment(JLabel.CENTER);
        heading.setBounds(190, 20, 200, 30);

        loginAccountNumber = new JLabel("Enter your Account Number");
        loginAccountNumber.setBounds(50, 80, 500, 30);

        forLoginAccountNumber = new JTextField();
        forLoginAccountNumber.setBounds(50, 110, 500, 50);

        loginPassword = new JLabel("Enter your Password ");
        loginPassword.setBounds(50, 160, 500, 30);

        forLoginPassword = new JTextField();
        forLoginPassword.setBounds(50, 200, 500, 50);

        submit = new JButton("Submit");
        submit.setBounds(220, 270, 120, 30);
        submit.addActionListener(this);

        frame.add(heading);
        frame.add(loginAccountNumber);
        frame.add(forLoginAccountNumber);
        frame.add(loginPassword);
        frame.add(forLoginPassword);
        frame.add(submit);

        frame.validate();
        frame.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit && (forLoginPassword.getText().length()<=0  || forLoginAccountNumber.getText().length()<=0))
        {
            JOptionPane.showMessageDialog(frame, "Access Denied \nPlease fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);

        }

               else
        {

            try {
                String url = "jdbc:mysql://localhost:3306/bankmanagement";
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, "root", "1234");

                if (con.isClosed()) {
                    System.out.println("connection closed");
                } else System.out.println("Connection created");


                String query = "SELECT * FROM ADMINISTRATOR_DETAILS;";
                Statement stmt = con.createStatement();
                int pass=1;

                ResultSet resultSet = stmt.executeQuery(query);

                // Step 5: Process the results
                while (resultSet.next())
                {


                    String accountNumber = resultSet.getString("ACCOUNT_NUMBER");
                    if (accountNumber.equals(forLoginAccountNumber.getText()))
                    {
                        JOptionPane.showMessageDialog(frame, "THIS ACCOUNT NUMBER IS ALREADY TAKEN", "Error", JOptionPane.ERROR_MESSAGE);
                        pass=1;
                        break;
                    } else
                    {
                        pass=0;
                    }

                }

                if (pass==0) {

                    String query1 = "INSERT INTO ADMINISTRATOR_DETAILS (ACCOUNT_NUMBER, ACCOUNT_PASSWORD) VALUES (?,?);";
                    PreparedStatement ptmt = con.prepareStatement(query1);

                    ptmt.setString(1, forLoginAccountNumber.getText());
                    ptmt.setString(2, forLoginPassword.getText());
                    ptmt.executeUpdate();
                    System.out.println("INserted");
                    JOptionPane.showMessageDialog(frame, "NEW ACCOUNT CREATED", "ACCOUNT CREATED", JOptionPane.PLAIN_MESSAGE);
                    frame.dispose();
                    new MainMenuPanel();

                }

            } catch (Exception d) {d.printStackTrace();}

        }



        }

}


