package BankManagement;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BalanceDetail implements ActionListener {
     int a=0;
    private JFrame frame;

    private JLabel heading, addAccountNumber, text;
    private JTextField forAddAccountNumber;
    private JButton show, back;
    private Font labelFont;

    BalanceDetail() {
        frame = new JFrame("BALANCE DETAIL");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.setLayout(null);

        heading = new JLabel("BALANCE DETAIL ");
        heading.setHorizontalAlignment(JLabel.CENTER);
        heading.setBounds(190, 20, 200, 30);

        addAccountNumber = new JLabel("Enter your Account Number");
        addAccountNumber.setBounds(50, 80, 500, 30);

        forAddAccountNumber = new JTextField();
        forAddAccountNumber.setBounds(50, 110, 500, 50);

        text = new JLabel();
        text.setBounds(50, 170, 500, 80);
         labelFont= text.getFont();
        text.setFont(new Font(labelFont.getFontName(), labelFont.getStyle(), 20));

        show = new JButton("SHOW DETAILS");
        show.setBounds(210, 270, 150, 30);
        show.addActionListener(this);

        back = new JButton("Back");
        back.setBounds(240, 310, 90, 30);
        back.addActionListener(this);

        frame.add(heading);
        frame.add(addAccountNumber);
        frame.add(forAddAccountNumber);
        frame.add(text);
        frame.add(show);
        frame.add(back);

        frame.validate();
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back&&a==1) {

            this.frame.dispose();
            new MainMenuPanel();

        } else if (e.getSource() == back&&a==0) {

            this.frame.dispose();
            new UserFeatures();

        }

        else if (e.getSource() == show && forAddAccountNumber.getText().length() <= 0) {
            JOptionPane.showMessageDialog(frame, "Access Denied \nPlease fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);

        } else {

            try {
                String url = "jdbc:mysql://localhost:3306/bankmanagement";
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, "root", "1234");

                if (con.isClosed()) {
                    System.out.println("connection closed");
                } else System.out.println("Connection created");


                String query = "SELECT * FROM PUBLICACCOUNT;";
                Statement stmt = con.createStatement();
                int pass = 0;

                ResultSet resultSet = stmt.executeQuery(query);

                while (resultSet.next()) {


                    String accountNumber = resultSet.getString("ACCOUNT_NUMBER");
                    String currentAmount = resultSet.getString("AMOUNT");


                    if (accountNumber.equals(forAddAccountNumber.getText())) {

                        text.setText("<html>Account Number: " + accountNumber + "<br/>Amount: " + currentAmount + "</html>");
                        con.close();
                        pass = 1;
                        break;
                    }

                }


                if (pass == 0) {
                    JOptionPane.showMessageDialog(frame, "ACCOUNT NOT FOUND", "Error", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception d) {
                d.printStackTrace();
            }
        }
    }

}




