package BankManagement;


import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMoney implements ActionListener {

    private JFrame frame;

    private JLabel heading, addAccountNumber,addAmount;
    private JTextField forAddAccountNumber, forAddAmount;
    private JButton submit,back;
AddMoney()
{
    frame = new JFrame("ADD AMOUNT");
    frame.setSize(600, 400);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    frame.setLayout(null);

    heading = new JLabel("ADD AMOUNT ");
    heading.setHorizontalAlignment(JLabel.CENTER);
    heading.setBounds(190, 20, 200, 30);

    addAccountNumber = new JLabel("Enter your Account Number");
    addAccountNumber.setBounds(50, 80, 500, 30);

    forAddAccountNumber= new JTextField();
    forAddAccountNumber.setBounds(50, 110, 500, 50);

    addAmount = new JLabel("Enter your Amount to be Added : ");
    addAmount.setBounds(50, 160, 500, 30);

    forAddAmount = new JTextField();
    forAddAmount.setBounds(50, 200, 500, 50);

    submit = new JButton("Add");
    submit.setBounds(220, 270, 120, 30);
    submit.addActionListener(this);

    back = new JButton("Back");
    back.setBounds(220, 310, 120, 30);
    back.addActionListener(this);

    frame.add(heading);
    frame.add(addAccountNumber);
    frame.add(forAddAccountNumber);
    frame.add(addAmount);
    frame.add(forAddAmount);
    frame.add(submit);
    frame.add(back);

    frame.validate();
    frame.setVisible(true);

}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==back)
        {
            new MainMenuPanel();
            this.frame.dispose();
        }

       else if (e.getSource() == submit && (forAddAmount.getText().length()<=0  || forAddAccountNumber.getText().length()<=0))
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


                String query = "SELECT * FROM PUBLICACCOUNT;";
                Statement stmt = con.createStatement();
                int pass=0;

                ResultSet resultSet = stmt.executeQuery(query);

                while (resultSet.next())
                {


                    String accountNumber = resultSet.getString("ACCOUNT_NUMBER");

                    if (accountNumber.equals(forAddAccountNumber.getText()))
                    {
                        String query1 = "UPDATE PUBLICACCOUNT  SET AMOUNT = AMOUNT+? WHERE ACCOUNT_NUMBER=?;";
                        PreparedStatement ptmt = con.prepareStatement(query1);

                        ptmt.setString(1, forAddAmount.getText());
                        ptmt.setString(2, forAddAccountNumber.getText());
                        ptmt.executeUpdate();
                        System.out.println("Added Amount");
                        JOptionPane.showMessageDialog(frame, "AMOUNT ADDED", "AMOUNT ADDED", JOptionPane.PLAIN_MESSAGE);
                        con.close();
                        pass=1;
                        frame.dispose();
                        new MainMenuPanel();
                        break;
                    }
                }
                if(pass==0)
                {
                    JOptionPane.showMessageDialog(frame, "ACCOUNT NOT FOUND", "Error", JOptionPane.ERROR_MESSAGE);

                }
            }

             catch (Exception d) {d.printStackTrace();}
        }

        }

}

