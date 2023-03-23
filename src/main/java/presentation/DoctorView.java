package presentation;

import dao.AppointmentDAO;
import domain.Appointment;
import domain.Doctor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DoctorView implements ActionListener {
    AppointmentDAO appointmentDAO;
    DefaultListModel<String> appoint;
    List<Appointment> apps;
    JList<String> list;
    JScrollPane scrollPane;
    private JFrame frame = new JFrame("Doctor Page");
    private JPanel panel = new JPanel();
    private JButton btnBack = new JButton("Back");
    private JTextField textConfirm = new JTextField("Appointment ID to confirm");
    private JButton btnConfirm = new JButton("Confirm");

    public DoctorView(Doctor doctor) {
        appointmentDAO = new AppointmentDAO();
        appoint = new DefaultListModel<>();
        list = new JList<>(appoint);
        scrollPane = new JScrollPane(list);
        apps = appointmentDAO.readAll();

        scrollPane.setBounds(10, 10, 450, 300);
        btnBack.setBounds(150, 400, 200, 30);
        textConfirm.setBounds(10, 330, 200, 30);
        btnConfirm.setBounds(210, 330, 150, 30);

        apps = apps.stream().filter(a -> a.getLocation().getId().equals(doctor.getLocation().getId())).toList();

        for (Appointment a : apps) {
            String s = "ID: " + a.getId() + " Location: " + a.getLocation().getName() + " Donor: " +
                    a.getDonor().getLastName() + " " + a.getDonor().getFirstName() + " Date: " + a.getDate()
                    + " Confirmed: " + (a.isConfirmed() ? "Yes" : "No");
            System.out.println(s);
            appoint.addElement(s);
        }

        panel.add(scrollPane);
        panel.add(btnBack);
        panel.add(textConfirm);
        panel.add(btnConfirm);

        btnBack.addActionListener(this);
        btnConfirm.addActionListener(this);

        panel.setLayout(null);

        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setContentPane(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            WelcomeView welcomeView = new WelcomeView();
            frame.dispose();
        }
        if (e.getSource() == btnConfirm) {
            int app_id = Integer.parseInt(textConfirm.getText());

            boolean okay = false;

            for (Appointment a : apps) {
                if (a.getId().equals(app_id)) {
                    Appointment aux = appointmentDAO.read(app_id);
                    aux.setConfirmed(true);
                    appointmentDAO.update(aux);
                    okay = true;
                    break;
                }
            }

            if (okay) JOptionPane.showMessageDialog(btnConfirm, "Appointment confirmed");
            else JOptionPane.showMessageDialog(btnConfirm, "Failed to confirm");
        }
    }
}
