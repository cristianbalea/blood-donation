package dao;

import db.ConnectionFactory;
import domain.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO implements BasicDAO<Doctor, Integer> {
    @Override
    public void create(Doctor doctor) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(
                    "insert into donateblood.doctor (cnp, firstname, lastname, email, password, location) " +
                            "VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, doctor.getCnp());
            statement.setString(2, doctor.getFirstName());
            statement.setString(3, doctor.getLastName());
            statement.setString(4, doctor.getEmail());
            statement.setString(5, doctor.getPassword());
            statement.setInt(6, doctor.getLocation().getId());

            statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(con);
        }
    }

    @Override
    public Doctor read(Integer id) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        LocationDAO locationDAO = new LocationDAO();

        Doctor doctor = new Doctor();
        try {
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(
                    "select * from donateblood.doctor where id = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                doctor.setId(resultSet.getInt(1));
                doctor.setCnp(resultSet.getString(2));
                doctor.setFirstName(resultSet.getString(3));
                doctor.setLastName(resultSet.getString(4));
                doctor.setEmail(resultSet.getString(5));
                doctor.setPassword(resultSet.getString(6));
                doctor.setLocation(locationDAO.read(resultSet.getInt(7)));
                return doctor;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(con);
        }
        return null;
    }

    @Override
    public List<Doctor> readAll() {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        LocationDAO locationDAO = new LocationDAO();

        List<Doctor> doctors = new ArrayList<>();
        try {
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(
                    "select * from donateblood.doctor");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(resultSet.getInt(1));
                doctor.setCnp(resultSet.getString(2));
                doctor.setFirstName(resultSet.getString(3));
                doctor.setLastName(resultSet.getString(4));
                doctor.setEmail(resultSet.getString(5));
                doctor.setPassword(resultSet.getString(6));
                doctor.setLocation(locationDAO.read(resultSet.getInt(7)));
                doctors.add(doctor);
            }

            return doctors;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(con);
        }
        return null;
    }

    @Override
    public void update(Doctor doctor) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(
                    "update donateblood.doctor " +
                            "set cnp = ?, firstname = ?, lastname = ?, email = ?, password = ?, location = ? " +
                            "where id = ?");
            statement.setString(1, doctor.getCnp());
            statement.setString(2, doctor.getFirstName());
            statement.setString(3, doctor.getLastName());
            statement.setString(4, doctor.getEmail());
            statement.setString(5, doctor.getPassword());
            statement.setInt(6, doctor.getLocation().getId());
            statement.setInt(7, doctor.getId());

            statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(con);
        }
    }

    @Override
    public void delete(Integer id) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(
                    "delete from donateblood.doctor " +
                            "where id = ?");
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(con);
        }
    }
}
