package presentation;

import business.LoginBL;
import domain.Role;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView implements ActionListener {
    private JFrame frame = new JFrame("Login");
    private JLabel labelUser = new JLabel("Username:");
    private JLabel labelPass = new JLabel("Password:");
    private JButton btnLogin = new JButton("Login");
    private JButton btnBack = new JButton("Back");
    private JTextField textUser = new JTextField("");
    private JPasswordField textPass = new JPasswordField();
    private JRadioButton radioDonor = new JRadioButton("I'm a donor");
    private JRadioButton radioDoctor = new JRadioButton("I'm a doctor");
    private JRadioButton radioAdmin = new JRadioButton("I'm an admin");
    private ButtonGroup radioBtns = new ButtonGroup();
    private JPanel panel = new JPanel();

    LoginView() {
        labelUser.setBounds(10, 20, 200, 30);
        textUser.setBounds(130, 20, 250, 30);
        labelPass.setBounds(10, 60, 200, 30);
        textPass.setBounds(130, 60, 250, 30);
        btnLogin.setBounds(150, 110, 100, 30);
        btnBack.setBounds(250, 110, 100, 30);
        radioDonor.setBounds(100, 150, 100, 30);
        radioDoctor.setBounds(100, 180, 100, 30);
        radioAdmin.setBounds(100, 210, 100, 30);

        panel.add(labelUser);
        panel.add(labelPass);
        panel.add(btnBack);
        panel.add(btnLogin);
        panel.add(textPass);
        panel.add(textUser);
        panel.add(radioAdmin);
        panel.add(radioDoctor);
        panel.add(radioDonor);

        radioBtns.add(radioAdmin);
        radioBtns.add(radioDoctor);
        radioBtns.add(radioDonor);
        radioDonor.setSelected(true);

        btnLogin.addActionListener(this);
        btnBack.addActionListener(this);

        panel.setLayout(null);
        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setSize(500, 300);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            WelcomeView welcomeView = new WelcomeView();
            frame.dispose();
        }
        if (e.getSource() == btnLogin) {
            LoginBL loginBL = new LoginBL();
            int i = loginBL.login(getEmail(), getPassword(), getRole());
            if (i != 0) {
                if (getRole().equals(Role.Doctor)) {
                    DoctorView doctorView = new DoctorView(loginBL.loginDoctor(i));
                    frame.dispose();
                } else if (getRole().equals(Role.Donor)) {
                    DonorView donorView = new DonorView(loginBL.loginDonor(i));
                    frame.dispose();
                } else {
                    AdminView adminView = new AdminView();
                    frame.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(btnLogin, "User not found");
            }

        }
    }

    public String getEmail() {
        return textUser.getText();
    }

    public String getPassword() {
        return textPass.getText();
    }

    public Role getRole() {
        if (radioAdmin.isSelected()) {
            return Role.Admin;
        } else if (radioDonor.isSelected()) {
            return Role.Donor;
        } else return Role.Doctor;
    }
}