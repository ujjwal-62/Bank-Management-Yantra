package BankManagement;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;

public class LoginPage implements ActionListener {
    private JFrame frame;

    private JLabel heading, loginAccountNumber, loginPassword, newUserRegistration;
    private JTextField forLoginAccountNumber, forLoginPassword;
    private JButton submit,back;




    LoginPage() {
        frame = new JFrame("LOGIN PAGE");
        frame.setSize(600, 440);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.setLayout(null);

        JLabel heading = new JLabel("Administrator Login Page");
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

        back = new JButton("GO TO HOMEPAGE");
        back.setBounds(190, 310, 180, 30);
        back.addActionListener(this);

         newUserRegistration= new JLabel("New User : Register Here");
         newUserRegistration.setBounds(210, 350, 180, 30);
         newUserRegistration.addMouseListener(new MouseListener() {
             @Override

             public void mouseClicked(MouseEvent e) {
                        frame.dispose();
                      new  NewUserRegistration();

             }

             @Override
             public void mousePressed(MouseEvent e) {}

             @Override
             public void mouseReleased(MouseEvent e) {}

             @Override
             public void mouseEntered(MouseEvent e) {}

             @Override
             public void mouseExited(MouseEvent e) {}
         });


        frame.add(heading);
        frame.add(loginAccountNumber);
        frame.add(forLoginAccountNumber);
        frame.add(loginPassword);
        frame.add(forLoginPassword);
        frame.add(submit);
        frame.add(newUserRegistration);
        frame.add(back);


        frame.validate();
        frame.setVisible(true);


    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit && (forLoginPassword.getText().length()<=0  || forLoginAccountNumber.getText().length()<=0))
        {
            JOptionPane.showMessageDialog(frame, "Access Denied \nPlease fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);

        } else if ((e.getSource() == back) )
        {
            this.frame.dispose();
            new MainLogin();
        } else
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

                String query = "SELECT * FROM ADMINISTRATOR_DETAILS;";
                int pass=0;
                Statement stmt=con.createStatement();
                ResultSet resultSet = stmt.executeQuery(query);
                while (resultSet.next())
                {
                    String accountNumber = resultSet.getString("ACCOUNT_NUMBER");
                    String accountPassword = resultSet.getString("ACCOUNT_PASSWORD");
                    if (accountNumber.equals(forLoginAccountNumber.getText()) && accountPassword.equals(forLoginPassword.getText()))
                    {System.out.println("you are authorised ");

                        frame.dispose();
                        new MainMenuPanel();
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
