package BankManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


class NewAccountMenu implements ActionListener{

    private JFrame frame;
    private JPanel NewAccountPanel;
    private JButton Submit, Back;
    private JLabel Name , TypesOfAccount, Amount, AccountNo;
    private JTextField NameNewAccount, TypesOfAccountNewAccount, AmountNewAccount, AccountNoNewAccount;
    private CardLayout cardLayout;

    NewAccountMenu() {


        frame = new JFrame("CREATE NEW ACCOUNT");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        NewAccountPanel = new JPanel(cardLayout);

        // Main Menu Panel
        JPanel menuPanel = new JPanel(new GridLayout(11, 1));

        JLabel titleLabel = new JLabel("FILL ALL THE DETAILS :");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        menuPanel.add(titleLabel);

        Name = new JLabel("Enter the Person Name :");
        Name.setBounds(50,10,100,20);
        menuPanel.add(Name);

        NameNewAccount = new JTextField();
        NameNewAccount.setBounds(50,20,100,20);
        menuPanel.add(NameNewAccount);

        AccountNo= new JLabel("Enter the Account Number :");
        AccountNo.setBounds(50,30,100,20);
        menuPanel.add(AccountNo);

        AccountNoNewAccount = new JTextField();
        AccountNoNewAccount.setBounds(50,40,100,20);
        menuPanel.add(AccountNoNewAccount);

        TypesOfAccount = new JLabel("Enter the type of Account :");
        TypesOfAccount.setBounds(50,50,100,20);
        menuPanel.add(TypesOfAccount);

        TypesOfAccountNewAccount = new JTextField();
        TypesOfAccountNewAccount.setBounds(50,60,100,20);
        menuPanel.add(TypesOfAccountNewAccount);

        Amount = new JLabel("Enter the Initial Ammount :");
        Amount.setBounds(50,70,100,20);
        menuPanel.add(Amount);

        AmountNewAccount = new JTextField();
        AmountNewAccount.setBounds(50,80,100,20);
        menuPanel.add(AmountNewAccount);

        Submit = new JButton("Submit");
        Submit.setBounds(100,100,25,10);
        menuPanel.add(Submit);
        Submit.addActionListener(this);

        Back = new JButton("Back to Homepage");
        Back.setBounds(100,120,25,10);
        menuPanel.add(Back);
        Back.addActionListener(this);

        NewAccountPanel.add(menuPanel, "MainMenu");

        frame.add(NewAccountPanel);




        frame.validate();

        frame.setVisible(true);


    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==Back)
        {
           new MainMenuPanel();
           this.frame.dispose();
        }
        else if (e.getSource() == Submit && (NameNewAccount.getText().length()<=0  || AccountNoNewAccount.getText().length()<=0||TypesOfAccountNewAccount.getText().length()<=0 || AmountNewAccount.getText().length()<=0))
        {
            JOptionPane.showMessageDialog(frame, "Access Denied \nPlease fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);

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
                int pass=1;
                Statement stmt=con.createStatement();
                ResultSet resultSet = stmt.executeQuery(query);
                while (resultSet.next())
                {
                    String accountNumber = resultSet.getString("ACCOUNT_NUMBER");
                    if (accountNumber.equals(AccountNoNewAccount.getText()))
                    {JOptionPane.showMessageDialog(frame, "User with this account already exist", "Error", JOptionPane.PLAIN_MESSAGE);
                        pass=0;
                        break;}

                }

                if (pass==1)
                {      String query1 = "INSERT INTO PUBLICACCOUNT (ACCOUNT_NUMBER, ACCOUNT_NAME, TYPE_OF_ACCOUNT, AMOUNT) VALUES (?,?,?,?);";
                    PreparedStatement ptmt = con.prepareStatement(query1);

                    ptmt.setString(1, AccountNoNewAccount.getText());
                    ptmt.setString(2, NameNewAccount.getText());
                    ptmt.setString(3, TypesOfAccountNewAccount.getText());
                    ptmt.setString(4, AmountNewAccount.getText());
                    ptmt.executeUpdate();
                    JOptionPane.showMessageDialog(frame, "New Account Created", "Account Create", JOptionPane.PLAIN_MESSAGE);
                con.close();
                System.out.println("ACCOUNT INSERTED");
                    frame.dispose();
                    new MainMenuPanel();

                }}
            catch (Exception d)
            {d.printStackTrace();}




        }




    }

}


