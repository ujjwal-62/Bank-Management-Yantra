package BankManagement;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class UserFeatures implements ActionListener
{

    private JFrame frame;
    private JButton balanceEnquiery, withdrawl,changepin ,back;
    private JLabel heading;

    UserFeatures() {
        frame = new JFrame("USER OPTIONS");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.setLayout(null);

        heading = new JLabel("YOUR AVAILABLE OPTIONS ARE :");
        heading.setHorizontalAlignment(JLabel.CENTER);
        heading.setBounds(190, 20, 200, 30);

        balanceEnquiery = new JButton("BALANCE ENQIERY");
        balanceEnquiery.setBounds(220, 100, 150, 50);
        balanceEnquiery.addActionListener(this);

        withdrawl = new JButton("CASH WITHDRAWL");
        withdrawl.setBounds(220, 200, 150, 50);
        withdrawl.addActionListener(this);

         changepin= new JButton("CHANGE PIN");
        changepin.setBounds(220, 300, 150, 50);
        changepin.addActionListener(this);

        back = new JButton("GO TO HOMEPAGE");
        back.setBounds(200, 400, 180, 30);
        back.addActionListener(this);


        frame.add(heading);
        frame.add(back);
        frame.add(withdrawl);
        frame.add(balanceEnquiery);
        frame.add(changepin);

        frame.validate();
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == balanceEnquiery )
        {
            this.frame.dispose();
            new BalanceDetail();
        }
        if (e.getSource() == withdrawl )
        {
            this.frame.dispose();
            new WithDraw();
        }
        if (e.getSource() == changepin )
        {
            this.frame.dispose();

            new ChangePin();
        }
         if ((e.getSource() == back) )
        {
            this.frame.dispose();
            new MainLogin();
        }
    }

    public static void main(String[] args) {
        new MainLogin();
    }
}
