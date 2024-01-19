package BankManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MainMenuPanel implements ActionListener {
    private JFrame frame;
    public JPanel mainPanel;
    private JButton newAccountBtn, depositBtn, withdrawBtn, balanceEnquiryBtn,
            accountHolderListBtn, closeAccountBtn, modifyAccountBtn,  backButton;
    private CardLayout cardLayout;

    MainMenuPanel() {
        frame = new JFrame("BANK MANAGEMENT SYSTEM");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Main Menu Panel
        JPanel menuPanel = new JPanel(new GridLayout(9, 1));

        JLabel titleLabel = new JLabel("BANK MANAGEMENT SYSTEM");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        menuPanel.add(titleLabel);


        newAccountBtn = new JButton("NEW ACCOUNT");
        depositBtn = new JButton("DEPOSIT AMOUNT");
        withdrawBtn = new JButton("WITHDRAW AMOUNT");
        balanceEnquiryBtn = new JButton("BALANCE ENQUIRY");
        accountHolderListBtn = new JButton("ALL ACCOUNT HOLDER LIST");
        closeAccountBtn = new JButton("CLOSE AN ACCOUNT");
        modifyAccountBtn = new JButton("MODIFY AN ACCOUNT");
        backButton = new JButton("BACK");

        menuPanel.add(newAccountBtn);
        menuPanel.add(depositBtn);
        menuPanel.add(withdrawBtn);
        menuPanel.add(balanceEnquiryBtn);
        menuPanel.add(accountHolderListBtn);
        menuPanel.add(closeAccountBtn);
        menuPanel.add(modifyAccountBtn);
        menuPanel.add(backButton);

        mainPanel.add(menuPanel, "MainMenu");

        frame.add(mainPanel);

        frame.validate();

        newAccountBtn.addActionListener(this);
        depositBtn.addActionListener(this);
        withdrawBtn.addActionListener(this);
        balanceEnquiryBtn.addActionListener(this);
        accountHolderListBtn.addActionListener(this);
        closeAccountBtn.addActionListener(this);
        modifyAccountBtn.addActionListener(this);
        backButton.addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newAccountBtn) {
            this.frame.dispose();
            new  NewAccountMenu();


        } else if (e.getSource() == depositBtn) {
            this.frame.dispose();
            new AddMoney();
        } else if (e.getSource() == withdrawBtn) {
            this.frame.dispose();
           new WithDraw().a=1;

        } else if (e.getSource() == balanceEnquiryBtn) {
            this.frame.dispose();
            new BalanceDetail().a=1;
        } else if (e.getSource() == accountHolderListBtn) {
            this.frame.dispose();
            new AllAccountHolderList();

        } else if (e.getSource() == closeAccountBtn) {
            this.frame.dispose();
            new CloseAccount();

        } else if (e.getSource() == modifyAccountBtn) {
            this.frame.dispose();
            new ModifyDetail();


        } else if (e.getSource() == backButton) {
            // Perform exit operation
            this.frame.dispose();
            new MainLogin();
        }
    }
}
