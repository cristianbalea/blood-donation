package presentation;

import dao.DonorDAO;
import domain.Donor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterView implements ActionListener {
    private JLabel labelCnp = new JLabel("*CNP:");
    private JLabel labelFirstName = new JLabel("*First name:");
    private JLabel labelLastName = new JLabel("*Last name:");
    private JLabel labelEmail = new JLabel("*Email:");
    private JLabel labelPass = new JLabel("*Password:");
    private JLabel labelArea = new JLabel("*Area:");
    private JLabel labelBloodType = new JLabel("Blood Type(01, A2, B3, AB4):");
    private JTextField textCnp = new JTextField();
    private JTextField textFirstName = new JTextField();
    private JTextField textLastName = new JTextField();
    private JTextField textEmail = new JTextField();
    private JPasswordField textPass = new JPasswordField();
    private JTextField textArea = new JTextField();
    private JTextField textBloodType = new JTextField();
    private JButton btnRegister = new JButton("Register");
    private JButton btnBack = new JButton("Back");
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();

    public RegisterView() {
        labelCnp.setBounds(10, 20, 100, 30);
        labelFirstName.setBounds(10, 60, 100, 30);
        labelLastName.setBounds(10, 100, 100, 30);
        labelEmail.setBounds(10, 140, 100, 30);
        labelPass.setBounds(10, 180, 100, 30);
        labelArea.setBounds(10, 220, 100, 30);
        labelBloodType.setBounds(10, 260, 200, 30);
        textCnp.setBounds(130, 20, 250, 30);
        textFirstName.setBounds(130, 60, 250, 30);
        textLastName.setBounds(130, 100, 250, 30);
        textEmail.setBounds(130, 140, 250, 30);
        textPass.setBounds(130, 180, 250, 30);
        textArea.setBounds(130, 220, 250, 30);
        textBloodType.setBounds(180, 260, 170, 30);
        btnRegister.setBounds(50, 300, 200, 30);
        btnBack.setBounds(250, 300, 200, 30);

        panel.add(labelCnp);
        panel.add(labelFirstName);
        panel.add(labelLastName);
        panel.add(labelEmail);
        panel.add(labelPass);
        panel.add(labelArea);
        panel.add(labelBloodType);
        panel.add(textCnp);
        panel.add(textFirstName);
        panel.add(textLastName);
        panel.add(textEmail);
        panel.add(textPass);
        panel.add(textArea);
        panel.add(textBloodType);
        panel.add(btnRegister);
        panel.add(btnBack);

        panel.setLayout(null);

        btnBack.addActionListener(this);
        btnRegister.addActionListener(this);

        frame.setVisible(true);
        frame.setContentPane(panel);
        frame.setSize(500, 400);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            WelcomeView welcomeView = new WelcomeView();
            frame.dispose();
        }
        if (e.getSource() == btnRegister) {
            DonorDAO donorDAO = new DonorDAO();
            Donor donor = new Donor();
            if (getCnp().equals("") || getFirstName().equals("") || getLastName().equals("") || getEmail().equals("") || getPass().equals("") || getArea().equals("")) {
                JOptionPane.showMessageDialog(btnRegister, "All marked fields must be completed!");
            } else {
                donor.setCnp(getCnp());
                donor.setFirstName(getFirstName());
                donor.setLastName(getLastName());
                donor.setEmail(getEmail());
                donor.setPassword(getPass());
                donor.setArea(getArea());
                donor.setBloodType(getBloodType());

                try {
                    donorDAO.create(donor);
                    JOptionPane.showMessageDialog(btnRegister, "Sign Up successful!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(btnRegister, "User already exists");
                }

            }
        }
    }

    public String getCnp() {
        return textCnp.getText();
    }

    public String getFirstName() {
        return textFirstName.getText();
    }

    public String getLastName() {
        return textLastName.getText();
    }

    public String getEmail() {
        return textEmail.getText();
    }

    public String getPass() {
        return textPass.getText();
    }

    public String getArea() {
        return textArea.getText();
    }

    public String getBloodType() {
        return textBloodType.getText();
    }
}
