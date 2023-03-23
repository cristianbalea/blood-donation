package presentation;

import dao.DoctorDAO;
import dao.LocationDAO;
import domain.Doctor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminView implements ActionListener {
    private JFrame frame = new JFrame("Admin Page");
    private JPanel panel = new JPanel();
    private JTextField textSearch = new JTextField();
    private JButton btnSearch = new JButton("Search");
    private JLabel labelCnp = new JLabel("CNP");
    private JTextField textCnp = new JTextField();
    private JLabel labelFirstName = new JLabel("First name");
    private JTextField textFirstName = new JTextField();
    private JLabel labelLastName = new JLabel("Last name");
    private JTextField textLastName = new JTextField();
    private JLabel labelEmail = new JLabel("Email");
    private JTextField textEmail = new JTextField();
    private JLabel labelPass = new JLabel("Password");
    private JTextField textPass = new JTextField();
    private JLabel labelLoc = new JLabel("Location id");
    private JTextField textLoc = new JTextField();
    private JButton btnEdit = new JButton("Edit");
    private JButton btnCreate = new JButton("Create");
    private JButton btnDelete = new JButton("Delete");
    private JButton btnLoc = new JButton("See locations");
    private JButton btnBack = new JButton("Back");

    public AdminView() {
        textSearch.setBounds(10, 20, 200, 30);
        btnSearch.setBounds(210, 20, 100, 30);

        labelCnp.setBounds(10, 70, 150, 30);
        textCnp.setBounds(170, 70, 200, 30);
        labelFirstName.setBounds(10, 110, 150, 30);
        textFirstName.setBounds(170, 110, 200, 30);
        labelLastName.setBounds(10, 150, 150, 30);
        textLastName.setBounds(170, 150, 200, 30);
        labelEmail.setBounds(10, 190, 150, 30);
        textEmail.setBounds(170, 190, 200, 30);
        labelPass.setBounds(10, 230, 150, 30);
        textPass.setBounds(170, 230, 200, 30);
        labelLoc.setBounds(10, 270, 150, 30);
        textLoc.setBounds(170, 270, 200, 30);

        btnEdit.setBounds(150, 310, 200, 30);
        btnCreate.setBounds(150, 340, 200, 30);
        btnDelete.setBounds(150, 370, 200, 30);
        btnLoc.setBounds(150, 410, 200, 30);
        btnBack.setBounds(150, 440, 200, 30);

        panel.add(textSearch);
        panel.add(btnSearch);
        panel.add(labelCnp);
        panel.add(textCnp);
        panel.add(labelFirstName);
        panel.add(textFirstName);
        panel.add(labelLastName);
        panel.add(textLastName);
        panel.add(textEmail);
        panel.add(labelEmail);
        panel.add(labelPass);
        panel.add(textPass);
        panel.add(labelLoc);
        panel.add(textLoc);
        panel.add(btnEdit);
        panel.add(btnCreate);
        panel.add(btnDelete);
        panel.add(btnLoc);
        panel.add(btnBack);

        btnEdit.addActionListener(this);
        btnSearch.addActionListener(this);
        btnLoc.addActionListener(this);
        btnCreate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnBack.addActionListener(this);

        panel.setLayout(null);

        frame.setVisible(true);
        frame.setSize(500, 550);
        frame.setContentPane(panel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnEdit) {
            DoctorDAO doctorDAO = new DoctorDAO();
            LocationDAO locationDAO = new LocationDAO();
            Doctor doctor = new Doctor();
            doctor.setCnp(textCnp.getText());
            doctor.setFirstName(textFirstName.getText());
            doctor.setLastName(textLastName.getText());
            doctor.setEmail(textEmail.getText());
            doctor.setPassword(textPass.getText());
            doctor.setLocation(locationDAO.read(Integer.parseInt(textLoc.getText())));
            doctor.setId(Integer.parseInt(textSearch.getText()));
            doctorDAO.update(doctor);
            JOptionPane.showMessageDialog(btnEdit, "Doctor updated");
        }
        if (e.getSource() == btnBack) {
            WelcomeView welcomeView = new WelcomeView();
            frame.dispose();
        }
        if (e.getSource() == btnLoc) {
            LocationView locationView = new LocationView();
        }
        if (e.getSource() == btnCreate) {
            DoctorDAO doctorDAO = new DoctorDAO();
            LocationDAO locationDAO = new LocationDAO();
            Doctor doctor = new Doctor();
            doctor.setCnp(textCnp.getText());
            doctor.setFirstName(textFirstName.getText());
            doctor.setLastName(textLastName.getText());
            doctor.setEmail(textEmail.getText());
            doctor.setPassword(textPass.getText());
            doctor.setLocation(locationDAO.read(Integer.parseInt(textLoc.getText())));
            doctorDAO.create(doctor);
            JOptionPane.showMessageDialog(btnCreate, "Doctod created");
        }
        if (e.getSource() == btnDelete) {
            DoctorDAO doctorDAO = new DoctorDAO();
            doctorDAO.delete(Integer.parseInt(textSearch.getText()));
            JOptionPane.showMessageDialog(btnDelete, "Doctor deleted");
        }
        if (e.getSource() == btnSearch) {
            DoctorDAO doctorDAO = new DoctorDAO();
            try {
                Doctor doctor = doctorDAO.read(Integer.parseInt(textSearch.getText()));
                textCnp.setText(doctor.getCnp());
                textFirstName.setText(doctor.getFirstName());
                textLastName.setText(doctor.getLastName());
                textEmail.setText(doctor.getEmail());
                textPass.setText(doctor.getPassword());
                textLoc.setText(String.valueOf(doctor.getLocation().getId()));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(btnSearch, "Doctor not found");
            }

        }
    }
}
