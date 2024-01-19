package BankManagement;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;

public class NormalUserLogin implements ActionListener {
    private JFrame frame;

    private JLabel heading, loginAccountNumber, loginPin;
    private JTextField forLoginAccountNumber, forLoginPin;
    private JButton submit,back;




    NormalUserLogin() {
        frame = new JFrame("NORMAL USER LOGIN");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.setLayout(null);

        JLabel heading = new JLabel("WELCOME TO XYZ BANK");
        heading.setHorizontalAlignment(JLabel.CENTER);
        heading.setBounds(190, 20, 200, 30);

        loginAccountNumber = new JLabel("Enter your Account Number");
        loginAccountNumber.setBounds(50, 80, 500, 30);

        forLoginAccountNumber = new JTextField();
        forLoginAccountNumber.setBounds(50, 110, 500, 50);

        loginPin = new JLabel("Enter your PIN ");
        loginPin.setBounds(50, 160, 500, 30);

        forLoginPin = new JTextField();
        forLoginPin.setBounds(50, 200, 500, 50);

        submit = new JButton("Submit");
        submit.setBounds(220, 270, 120, 30);
        submit.addActionListener(this);

        back = new JButton("GO TO HOMEPAGE");
        back.setBounds(190, 310, 180, 30);
        back.addActionListener(this);


        frame.add(heading);
        frame.add(loginAccountNumber);
        frame.add(forLoginAccountNumber);
        frame.add(loginPin);
        frame.add(forLoginPin);
        frame.add(submit);
        frame.add(back);



        frame.validate();
        frame.setVisible(true);


    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit && (forLoginPin .getText().length()<=0  || forLoginAccountNumber.getText().length()<=0))
        {
            JOptionPane.showMessageDialog(frame, "Access Denied \nPlease fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);

        }
        else if ((e.getSource() == back) )
        {
            this.frame.dispose();
            new MainLogin();
        }

        else
        {

            try {
                String url ="jdbc:mysql://localhost:3306/bankmanagement";
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con= DriverManager.getConnection(url,"root","1234");

                if(con.isClosed())
                {
                    System.out.println("connection closed");
                }
                else System.out.println("Connection created");

                String query = "SELECT * FROM PUBLICACCOUNT;";
                int pass=0;
                Statement stmt=con.createStatement();
                ResultSet resultSet = stmt.executeQuery(query);
                while (resultSet.next())
                {
                    String accountNumber = resultSet.getString("ACCOUNT_NUMBER");
                    String accountPin = resultSet.getString("ACCOUNT_PIN");
                    if (accountNumber.equals(forLoginAccountNumber.getText()) && accountPin.equals(forLoginPin.getText()))
                    {System.out.println("you are authorised ");

                        frame.dispose();
                        new UserFeatures();
                        pass=1;
                        break;}

                }
                if (pass==0)
                {JOptionPane.showMessageDialog(frame, "User Not Found....Create New User", "Error", JOptionPane.ERROR_MESSAGE);}

                con.close();
                System.out.println("connection closed");
            }
            catch (Exception d)
            {d.printStackTrace();}




        }}

}
