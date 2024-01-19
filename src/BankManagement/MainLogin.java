package BankManagement;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class MainLogin implements ActionListener
{

    private JFrame frame;
        private JButton forAdmin, forUser;
        private JLabel heading;

        MainLogin() {
        frame = new JFrame("MAIN LOGIN PAGE");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.setLayout(null);

        heading = new JLabel("WELCOME TO XYZ BANK");
        heading.setHorizontalAlignment(JLabel.CENTER);
        heading.setBounds(190, 20, 200, 30);

            forAdmin = new JButton("FOR ADMIN ONLY");
            forAdmin.setBounds(220, 100, 150, 50);
            forAdmin.addActionListener(this);

            forUser = new JButton("FOR USER");
            forUser.setBounds(220, 200, 150, 50);
            forUser.addActionListener(this);

            frame.add(heading);
            frame.add(forAdmin);
            frame.add(forUser);

            frame.validate();
            frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == forAdmin )
        {
            this.frame.dispose();
            new LoginPage();
        }
        if (e.getSource() == forUser )
        {
            this.frame.dispose();
            new NormalUserLogin();
        }

    }


    }

