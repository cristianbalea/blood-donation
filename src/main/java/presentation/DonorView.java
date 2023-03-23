package presentation;

import dao.AppointmentDAO;
import dao.DonorDAO;
import dao.LocationDAO;
import domain.Appointment;
import domain.Donor;
import domain.Location;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DonorView implements ActionListener {
    private JFrame frame = new JFrame("Donor Page");
    private JPanel panel = new JPanel();
    private JButton btnLoc = new JButton("See locations");
    private JButton btnBack = new JButton("Back");
    private JButton btnDelete = new JButton("Delete account");
    private JButton btnEdit = new JButton("Edit account");

    private JTextField textCnp = new JTextField();
    private JTextField textFirstName = new JTextField();
    private JTextField textLastName = new JTextField();
    private JTextField textEmail = new JTextField();
    private JTextField textPass = new JTextField();
    private JTextField textArea = new JTextField();
    private JTextField textBloodType = new JTextField();
    private Donor donor;

    private JTextField textLocation = new JTextField("Location ID");
    private JTextField textDate = new JTextField("YYYY-MM-DD");
    private JButton btnCreateAppointment = new JButton("Create appointment");
    private JButton btnAppointments = new JButton("My appointments");

    private JTextField textApp = new JTextField("Appointment ID to cancel");
    private JButton btnCancel = new JButton("Cancel appointment");

    public DonorView(Donor donor) {
        this.donor = donor;

        textCnp.setText(donor.getCnp());
        textFirstName.setText(donor.getFirstName());
        textLastName.setText(donor.getLastName());
        textEmail.setText(donor.getEmail());
        textPass.setText(donor.getPassword());
        textArea.setText(donor.getArea());
        textBloodType.setText(donor.getBloodType());

        panel.add(textCnp);
        panel.add(textFirstName);
        panel.add(textLastName);
        panel.add(textEmail);
        panel.add(textPass);
        panel.add(textArea);
        panel.add(textBloodType);
        panel.add(btnEdit);

        panel.add(textLocation);
        panel.add(textDate);
        panel.add(btnCreateAppointment);

        panel.add(btnLoc);
        panel.add(btnDelete);
        panel.add(btnBack);
        panel.add(btnAppointments);

        panel.add(textApp);
        panel.add(btnCancel);

        btnLoc.addActionListener(this);
        btnDelete.addActionListener(this);
        btnBack.addActionListener(this);
        btnEdit.addActionListener(this);
        btnCreateAppointment.addActionListener(this);
        btnAppointments.addActionListener(this);
        btnCancel.addActionListener(this);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        frame.setVisible(true);
        frame.setSize(300, 500);
        frame.setContentPane(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            WelcomeView welcomeView = new WelcomeView();
            frame.dispose();
        }
        if (e.getSource() == btnLoc) {
            LocationView locationView = new LocationView();
        }
        if (e.getSource() == btnDelete) {
            DonorDAO donorDAO = new DonorDAO();
            donorDAO.delete(donor.getId());
            JOptionPane.showMessageDialog(btnDelete, "Your account was deleted");
            frame.dispose();
            WelcomeView welcomeView = new WelcomeView();
        }
        if (e.getSource() == btnEdit) {
            donor.setCnp(textCnp.getText());
            donor.setFirstName(textFirstName.getText());
            donor.setLastName(textLastName.getText());
            donor.setEmail(textEmail.getText());
            donor.setPassword(textPass.getText());
            donor.setArea(textArea.getText());
            donor.setBloodType(textBloodType.getText());

            DonorDAO donorDAO = new DonorDAO();
            donorDAO.update(donor);
            JOptionPane.showMessageDialog(btnEdit, "Edit successfully");
        }
        if (e.getSource() == btnCreateAppointment) {
            Appointment app = new Appointment();
            app.setDonor(donor);
            app.setDate(Date.valueOf(textDate.getText()));

            int locId = Integer.parseInt(textLocation.getText());
            LocationDAO locationDAO = new LocationDAO();
            try {
                Location l = locationDAO.read(locId);
                System.out.println(l);
                if (l.getMaxNumberOfDonors() > 0) {
                    l.setMaxNumberOfDonors(l.getMaxNumberOfDonors() - 1);
                    locationDAO.update(l);
                    app.setLocation(l);
                    System.out.println(app.toString());
                    AppointmentDAO appointmentDAO = new AppointmentDAO();
                    appointmentDAO.create(app);
                    JOptionPane.showMessageDialog(btnCreateAppointment, "Appointment created successfully");
                } else {
                    JOptionPane.showMessageDialog(btnCreateAppointment, "No spots available for donation");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        if (e.getSource() == btnAppointments) {
            AppointmentView appointmentView = new AppointmentView(donor);
        }
        if (e.getSource() == btnCancel) {
            AppointmentDAO appointmentDAO = new AppointmentDAO();
            List<Appointment> app = appointmentDAO.readAll()
                    .stream().filter(a -> a.getDonor().getId().equals(donor.getId())).toList();
            int appointment_id = Integer.parseInt(textApp.getText());

            boolean okay = false;

            for (Appointment a : app) {
                if(a.getId().equals(appointment_id)) {
                    appointmentDAO.delete(appointment_id);
                    okay = true;
                    break;
                }
            }
            if(okay) {
                JOptionPane.showMessageDialog(btnCancel, "Appointment cancelled successfully");
            }
            else {
                JOptionPane.showMessageDialog(btnCancel, "Wrong Appointment ID");
            }
        }
    }
}
