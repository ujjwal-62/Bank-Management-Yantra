package BankManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.util.EventListener;


class ModifyDetail implements ItemListener,ActionListener {

    private JCheckBox a2, a3, a4,a5;
    private JFrame frame;
    private JPanel NewAccountPanel;
    private JButton Submit, Back;
    private JLabel Name, TypesOfAccount, Amount, AccountNo,AccountPin;
    private JTextField NameNewAccount, TypesOfAccountNewAccount, AmountNewAccount, AccountNoNewAccount,Pin;
    private CardLayout cardLayout;

    ModifyDetail() {


        frame = new JFrame("CREATE NEW ACCOUNT");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        NewAccountPanel = new JPanel(cardLayout);

        // Main Menu Panel
        JPanel menuPanel = new JPanel(new GridLayout(17, 1));

        JLabel titleLabel = new JLabel("Check the details to be Corrected :");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        menuPanel.add(titleLabel);

        AccountNo = new JLabel("Enter the Account Number :");
        AccountNo.setBounds(50, 30, 100, 20);
        menuPanel.add(AccountNo);


        AccountNoNewAccount = new JTextField();
        AccountNoNewAccount.setBounds(50, 40, 100, 20);
        menuPanel.add(AccountNoNewAccount);



        a2 = new JCheckBox("NAME");
        a3 = new JCheckBox("TYPE OF ACCOUNT");
        a4 = new JCheckBox("AMOUNT");
        a5 = new JCheckBox("PIN");



        menuPanel.add(a2);
        menuPanel.add(a3);
        menuPanel.add(a4);
        menuPanel.add(a5);


        a2.addItemListener(this);
        a3.addItemListener(this);
        a4.addItemListener(this);
        a5.addItemListener(this);

        Name = new JLabel("Enter the Person Name :");
        Name.setBounds(50, 10, 100, 20);
        menuPanel.add(Name);
        Name.setVisible(false);

        NameNewAccount = new JTextField();
        NameNewAccount.setBounds(50, 20, 100, 20);
        menuPanel.add(NameNewAccount);
        NameNewAccount.setVisible(false);



        TypesOfAccount = new JLabel("Enter the type of Account :");
        TypesOfAccount.setBounds(50, 50, 100, 20);
        menuPanel.add(TypesOfAccount);
        TypesOfAccount.setVisible(false);

        TypesOfAccountNewAccount = new JTextField();
        TypesOfAccountNewAccount.setBounds(50, 60, 100, 20);
        menuPanel.add(TypesOfAccountNewAccount);
        TypesOfAccountNewAccount.setVisible(false);

        Amount = new JLabel("Enter the Initial Ammount :");
        Amount.setBounds(50, 70, 100, 20);
        menuPanel.add(Amount);
        Amount.setVisible(false);

        AmountNewAccount = new JTextField();
        AmountNewAccount.setBounds(50, 80, 100, 20);
        menuPanel.add(AmountNewAccount);
        AmountNewAccount.setVisible(false);

        AccountPin = new JLabel("Enter the NEW PIN :");
        AccountPin.setBounds(50, 90, 100, 20);
        menuPanel.add(AccountPin);
        AccountPin.setVisible(false);

        Pin = new JTextField();
        Pin.setBounds(50, 100, 100, 20);
        menuPanel.add(Pin);
        Pin.setVisible(false);

        Submit = new JButton("Submit");
        Submit.setBounds(100, 120, 25, 10);
        menuPanel.add(Submit);
        Submit.addActionListener(this);

        Back = new JButton("Back to Homepage");
        Back.setBounds(100, 140, 25, 10);
        menuPanel.add(Back);
        Back.addActionListener(this);

        NewAccountPanel.add(menuPanel, "MainMenu");

        frame.add(NewAccountPanel);


        frame.validate();

        frame.setVisible(true);


    }

    @Override
    public void itemStateChanged(ItemEvent e) {

        if (a2.isSelected())
        {
           Name.setVisible(true);
            NameNewAccount.setVisible(true);

        }else
        {
            Name.setVisible(false);
            NameNewAccount.setVisible(false);
            NameNewAccount.setText(null);
        }

        if (a3.isSelected())
        {
            TypesOfAccount.setVisible(true);
            TypesOfAccountNewAccount.setVisible(true);
        }else
        {
            TypesOfAccount.setVisible(false);
            TypesOfAccountNewAccount.setVisible(false);
            TypesOfAccountNewAccount.setText(null);
        }
        if (a4.isSelected())
        {
            Amount.setVisible(true);
            AmountNewAccount.setVisible(true);
        }else
        {
            Amount.setVisible(false);
            AmountNewAccount.setVisible(false);
            AmountNewAccount.setText(null);
        }
        if (a5.isSelected())
        {
            AccountPin.setVisible(true);
            Pin.setVisible(true);
        }else
        {
            AccountPin.setVisible(false);
            AccountPin.setVisible(false);
            Pin.setText(null);
        }

        }





    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Back) {
            new MainMenuPanel();
            this.frame.dispose();
        }
        else
        {    if (e.getSource() == Submit && AccountNoNewAccount.getText().length() <= 0)
                  {JOptionPane.showMessageDialog(frame, "Enter Account Number ", "Error", JOptionPane.ERROR_MESSAGE);}
             else
             {
                 if (NameNewAccount.getText().length() <= 0 && TypesOfAccountNewAccount.getText().length() <= 0 && AmountNewAccount.getText().length() <=0)
                 {
                     JOptionPane.showMessageDialog(frame, "Access Denied \nPlease check at least one box and fill", "Error", JOptionPane.ERROR_MESSAGE);
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

                             if (accountNumber.equals(AccountNoNewAccount.getText()))
                             {
                                 if(NameNewAccount.getText().length()>0) {
                                     String query1 = "UPDATE PUBLICACCOUNT  SET ACCOUNT_NAME = ? WHERE ACCOUNT_NUMBER=?;";
                                     PreparedStatement ptmt = con.prepareStatement(query1);
                                     ptmt.setString(1, NameNewAccount.getText());
                                     ptmt.setString(2, AccountNoNewAccount.getText());
                                     ptmt.executeUpdate();
                                 }
                                 if(TypesOfAccountNewAccount.getText().length()>0)
                                 {
                                 String query1 = "UPDATE PUBLICACCOUNT  SET TYPE_OF_ACCOUNT = ? WHERE ACCOUNT_NUMBER=?;";
                                 PreparedStatement ptmt = con.prepareStatement(query1);
                                 ptmt.setString(1, TypesOfAccountNewAccount.getText());
                                     ptmt.setString(2, AccountNoNewAccount.getText());
                                 ptmt.executeUpdate();
                                 }

                                 if(AmountNewAccount.getText().length()>0)
                                 {
                                     String query1 = "UPDATE PUBLICACCOUNT  SET AMOUNT = ? WHERE ACCOUNT_NUMBER=?;";
                                     PreparedStatement ptmt = con.prepareStatement(query1);
                                     ptmt.setString(1, AmountNewAccount.getText());
                                     ptmt.setString(2, AccountNoNewAccount.getText());
                                     ptmt.executeUpdate();
                                 }

                                 if(Pin.getText().length()>0)
                                     {
                                         String query1 = "UPDATE PUBLICACCOUNT  SET ACCOUNT_PIN = ? WHERE ACCOUNT_NUMBER=?;";
                                         PreparedStatement ptmt = con.prepareStatement(query1);
                                         ptmt.setString(1, Pin.getText());
                                         ptmt.setString(2, AccountNoNewAccount.getText());
                                         ptmt.executeUpdate();

                                     }
                                 



                                 System.out.println("CHANGES DONE");
                                 JOptionPane.showMessageDialog(frame, "CHANGES DONE", "AMOUNT ADDED", JOptionPane.PLAIN_MESSAGE);
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

    }

}

