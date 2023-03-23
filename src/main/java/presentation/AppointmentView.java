package presentation;

import dao.AppointmentDAO;
import domain.Appointment;
import domain.Donor;

import javax.swing.*;
import java.util.List;

public class AppointmentView {

    public AppointmentView(Donor donor) {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame("Appointments");
        AppointmentDAO appointmentDAO = new AppointmentDAO();
        DefaultListModel<String> appoint = new DefaultListModel<>();
        JList<String> list = new JList<>(appoint);
        JScrollPane scrollPane = new JScrollPane(list);
        List<Appointment> apps = appointmentDAO.readAll();

        scrollPane.setBounds(10, 10, 450, 400);

        apps = apps.stream().filter(a -> a.getDonor().getId().equals(donor.getId())).toList();

        for (Appointment a : apps) {
            String s = "ID: " + a.getId() + " Location: " + a.getLocation().getName() + " Donor: " +
                    a.getDonor().getLastName() + " " + a.getDonor().getFirstName() + " Date: " + a.getDate();
            System.out.println(s);
            appoint.addElement(s);
        }

        panel.add(scrollPane);

        panel.setLayout(null);

        frame.setVisible(true);
        frame.setContentPane(panel);
        frame.setSize(490, 460);
    }
}
