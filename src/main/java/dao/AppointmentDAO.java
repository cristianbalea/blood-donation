package dao;

import db.ConnectionFactory;
import domain.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO implements BasicDAO<Appointment, Integer> {
    @Override
    public void create(Appointment appointment) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(
                    "insert into donateblood.appointment " +
                            "(location, donor, date, confirmed) " +
                            "VALUES (?, ?, ?, ?)");
            statement.setInt(1, appointment.getLocation().getId());
            statement.setInt(2, appointment.getDonor().getId());
            statement.setDate(3, appointment.getDate());
            statement.setBoolean(3, appointment.isConfirmed());

            statement.executeUpdate();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(con);
        }
    }

    @Override
    public Appointment read(Integer id) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        LocationDAO locationDAO = new LocationDAO();
        DonorDAO donorDAO = new DonorDAO();

        Appointment appointment = new Appointment();
        try {
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(
                    "select * from donateblood.appointment where id = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                appointment.setId(resultSet.getInt(1));
                appointment.setLocation(locationDAO.read(resultSet.getInt(2)));
                appointment.setDonor(donorDAO.read(resultSet.getInt(3)));
                appointment.setDate(resultSet.getDate(4));
                appointment.setConfirmed(resultSet.getBoolean(5));

                return appointment;
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
    public List<Appointment> readAll() {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        LocationDAO locationDAO = new LocationDAO();
        DonorDAO donorDAO = new DonorDAO();

        List<Appointment> appointments = new ArrayList<>();
        try {
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(
                    "select * from donateblood.appointment");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Appointment appointment = new Appointment();
                appointment.setId(resultSet.getInt(1));
                appointment.setLocation(locationDAO.read(resultSet.getInt(2)));
                appointment.setDonor(donorDAO.read(resultSet.getInt(3)));
                appointment.setDate(resultSet.getDate(4));
                appointment.setConfirmed(resultSet.getBoolean(5));
                appointments.add(appointment);
            }

            return appointments;
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
    public void update(Appointment appointment) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(
                    "update donateblood.appointment " +
                            "set location = ?, donor = ?, date = ? , confirmed = ? " +
                            "where id = ?");
            statement.setInt(1, appointment.getLocation().getId());
            statement.setInt(2, appointment.getDonor().getId());
            statement.setDate(3, appointment.getDate());
            statement.setBoolean(4, appointment.isConfirmed());
            statement.setInt(5, appointment.getId());

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
                    "delete from donateblood.appointment " +
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
