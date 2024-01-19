package BankManagement;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class ChangePin implements ActionListener
{

    private JFrame frame;
    private JButton Submit,Back;
    private JTextField pinText,forAddAccountNumber;
    private JLabel heading, label1,addAccountNumber;

    ChangePin() {
        frame = new JFrame("New Pin ");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.setLayout(null);

        heading = new JLabel("CHANGE YOUR PIN");
        heading.setHorizontalAlignment(JLabel.CENTER);
        heading.setBounds(90, 20, 300, 30);

        addAccountNumber = new JLabel("Enter your Account Number");
        addAccountNumber.setBounds(60, 50, 300, 30);

        forAddAccountNumber= new JTextField();
        forAddAccountNumber.setBounds(60, 80, 300, 30);

        label1 = new JLabel("Enter the NEW PIN :");
        label1.setBounds(60, 110, 300, 30);

        pinText = new JTextField();
        pinText.setBounds(60, 140, 300, 30);

        Submit = new JButton("SUBMIT");
        Submit.setBounds(150, 180, 150, 30);
        Submit.addActionListener(this);

        Back = new JButton("Back to Homepage");
        Back.setBounds(150,250,150,30);
        Back.addActionListener(this);

        frame.add(heading);
        frame.add(label1);
        frame.add(pinText);
        frame.add(Submit);
        frame.add(Back);
        frame.add(forAddAccountNumber);
        frame.add(addAccountNumber);

        frame.validate();
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {


        if (e.getSource() == Back )
        {
            this.frame.dispose();
            new MainLogin();
        }
        else if (e.getSource() == Submit && (pinText.getText().length()<=0  || forAddAccountNumber.getText().length()<=0))
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
                    String currentAmount = resultSet.getString("AMOUNT");


                    if (accountNumber.equals(forAddAccountNumber.getText()))
                    {
                        if ( pinText.getText().length() ==4)
                        {
                            String query1 = "UPDATE PUBLICACCOUNT  SET ACCOUNT_PIN = ? WHERE ACCOUNT_NUMBER=?;";
                            PreparedStatement ptmt = con.prepareStatement(query1);

                            ptmt.setString(1, pinText.getText());
                            ptmt.setString(2, forAddAccountNumber.getText());
                            ptmt.executeUpdate();
                            System.out.println("PIN CHANGED");
                            JOptionPane.showMessageDialog(frame, "PIN CHANGED /N YOUR NEW PIN IS :"+pinText.getText(), "PIN CHANGED", JOptionPane.PLAIN_MESSAGE);
                            con.close();
                            pass=1;
                            frame.dispose();
                            new UserFeatures();
                            break;
                        }
                        else {
                            JOptionPane.showMessageDialog(frame, "Enter 4 digits only", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
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

