package presentation;

import dao.LocationDAO;
import domain.Location;

import javax.swing.*;
import java.util.List;

public class LocationView {

    public LocationView() {
        LocationDAO locationDAO = new LocationDAO();
        DefaultListModel<String> locs = new DefaultListModel<>();
        JList<String> list = new JList<>(locs);
        JScrollPane scrollPane = new JScrollPane(list);
        List<Location> locations = locationDAO.readAll();
        JPanel panel = new JPanel();
        JFrame frame = new JFrame("Locations");

        scrollPane.setBounds(10, 10, 400, 400);

        for (Location location : locations) {
            String s = location.getName() + ", ID: " + location.getId() + " , Orar: " +
                    location.getOpenHour() + " - " + location.getClosingHour() + " , Adresa: " +
                    location.getAddress();
            System.out.println(s);
            locs.addElement(s);
        }

        panel.add(scrollPane);

        panel.setLayout(null);

        frame.setVisible(true);
        frame.setContentPane(panel);
        frame.setSize(440, 460);
    }
}
