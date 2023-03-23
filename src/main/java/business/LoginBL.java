package business;

import dao.AdminDAO;
import dao.DoctorDAO;
import dao.DonorDAO;
import domain.Doctor;
import domain.Donor;
import domain.Role;

import java.util.List;

public class LoginBL {
    private DonorDAO donorDAO = new DonorDAO();
    private AdminDAO adminDAO = new AdminDAO();
    private DoctorDAO doctorDAO = new DoctorDAO();

    public int login(String email, String password, Role role) {
        if (role.equals(Role.Doctor)) {
            List<Doctor> docs = doctorDAO.readAll();
            for (Doctor d : docs) {
                if (d.getEmail().equals(email) && d.getPassword().equals(password)) {
                    return d.getId();
                }
            }
        } else if (role.equals(Role.Donor)) {
            List<Donor> donors = donorDAO.readAll();
            for (Donor d : donors) {
                if (d.getEmail().equals(email) && d.getPassword().equals(password)) {
                    return d.getId();
                }
            }
        } else {
            if (email.equals("admin") && password.equals("admin")) {
                return 1;
            }
        }
        return 0;
    }

    public Donor loginDonor(int id) {
        return donorDAO.read(id);
    }

    public Doctor loginDoctor(int id) {
        return doctorDAO.read(id);
    }
}
