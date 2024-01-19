package BankManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AllAccountHolderList implements ActionListener {

    private JFrame frame;

    private JButton  Back;
    private JLabel  headingOfPanel;
    private JTextArea textArea;
    private JScrollPane scrollPane;



    AllAccountHolderList()
    {
        frame = new JFrame("List of All Account Holders");
        frame.setSize(450, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);



        headingOfPanel=new JLabel("List of All Account Holder");
        headingOfPanel.setHorizontalAlignment(JLabel.CENTER);
        headingOfPanel.setBounds(50,20,300,30);
        frame.add(headingOfPanel);


        textArea = new JTextArea();

        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(18,60,400,500);
        frame.add(scrollPane);

        Back = new JButton("Back");
        Back.setBounds(140,580,100,25);
        frame.add(Back);
        Back.addActionListener(this);

        try {
            String url ="jdbc:mysql://localhost:3306/bankmanagement";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(url,"root","1234");

            if(con.isClosed())
            {
                System.out.println("connection closed");
            }
            else System.out.println("Connection created");

            String query = "SELECT * FROM PUBLICACCOUNT ORDER BY ACCOUNT_NUMBER ASC;";
            Statement stmt=con.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            textArea.setText("  AccountNumber   |     AccountName     |    AccountType   |    AccountAmount "+"\n"+"---------------------------------------------------------------------------------------------------");
            while (resultSet.next())
            {
                String accountNumber = resultSet.getString("ACCOUNT_NUMBER");
               String accountName = resultSet.getString("ACCOUNT_NAME");
                String accountType = resultSet.getString("TYPE_OF_ACCOUNT");
                String accountAmount = resultSet.getString("AMOUNT");
                String s="          "+accountNumber+"                 "+accountName+"                "+accountType+"                           "+accountAmount;


                textArea.append("\n"+s);
                textArea.setEditable(false);
            }


            }
        catch (Exception d)
        {d.printStackTrace();}



        frame.validate();
        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Back)
        { this.frame.dispose();
            new MainMenuPanel();

        }

    }
    
}
