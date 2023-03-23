package dao;

import db.ConnectionFactory;
import domain.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LocationDAO implements BasicDAO<Location, Integer> {
    @Override
    public void create(Location location) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(
                    "insert into donateblood.location (name, address, area, openHour, closingHour, opened, maxNumberOfDonors) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, location.getName());
            statement.setString(2, location.getAddress());
            statement.setString(3, location.getArea());
            statement.setInt(4, location.getOpenHour());
            statement.setInt(5, location.getClosingHour());
            statement.setBoolean(6, location.getOpened());
            statement.setInt(7, location.getMaxNumberOfDonors());

            statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(con);
        }
    }

    @Override
    public Location read(Integer id) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        Location location = new Location();
        try {
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(
                    "select * from donateblood.location where id = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                location.setId(resultSet.getInt(1));
                location.setName(resultSet.getString(2));
                location.setAddress(resultSet.getString(3));
                location.setArea(resultSet.getString(4));
                location.setOpenHour(resultSet.getInt(5));
                location.setClosingHour(resultSet.getInt(6));
                location.setOpened(resultSet.getBoolean(7));
                location.setMaxNumberOfDonors(resultSet.getInt(8));
                return location;
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
    public List<Location> readAll() {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        List<Location> locations = new ArrayList<>();
        try {
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(
                    "select * from donateblood.location");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Location location = new Location();
                location.setId(resultSet.getInt(1));
                location.setName(resultSet.getString(2));
                location.setAddress(resultSet.getString(3));
                location.setArea(resultSet.getString(4));
                location.setOpenHour(resultSet.getInt(5));
                location.setClosingHour(resultSet.getInt(6));
                location.setOpened(resultSet.getBoolean(7));
                location.setMaxNumberOfDonors(resultSet.getInt(8));
                locations.add(location);
            }

            return locations;
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
    public void update(Location location) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(
                    "update donateblood.location " +
                            "SET name = ?, address = ?, area = ?, openHour = ?, closingHour = ?, opened = ?, maxNumberOfDonors = ? " +
                            "where id = ?");
            statement.setString(1, location.getName());
            statement.setString(2, location.getAddress());
            statement.setString(3, location.getArea());
            statement.setInt(4, location.getOpenHour());
            statement.setInt(5, location.getClosingHour());
            statement.setBoolean(6, location.getOpened());
            statement.setInt(7, location.getMaxNumberOfDonors());
            statement.setInt(8, location.getId());

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
                    "delete from donateblood.location " +
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
