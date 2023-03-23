package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeView implements ActionListener {
    private JLabel labelTitle = new JLabel("Welcome!");
    private JButton btnSignIn = new JButton("Sign In");
    private JButton btnSignUp = new JButton("Sign Up");
    private JFrame frame = new JFrame("Welcome");
    private JPanel panel = new JPanel();

    public WelcomeView() {
        labelTitle.setBounds(175, 50, 200, 40);
        btnSignIn.setBounds(100, 120, 150, 40);
        btnSignUp.setBounds(250, 120, 150, 40);

        panel.add(labelTitle);
        panel.add(btnSignUp);
        panel.add(btnSignIn);

        labelTitle.setFont(new Font("Times New Roman", Font.PLAIN, 30));

        btnSignUp.addActionListener(this);
        btnSignIn.addActionListener(this);

        panel.setLayout(null);

        frame.setVisible(true);
        frame.setSize(500, 300);
        frame.setContentPane(panel);
    }

    public static void main(String[] args) {
        WelcomeView welcomeView = new WelcomeView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSignIn) {
            LoginView loginView = new LoginView();
            frame.dispose();
        }
        if (e.getSource() == btnSignUp) {
            RegisterView registerView = new RegisterView();
            frame.dispose();
        }
    }
}
