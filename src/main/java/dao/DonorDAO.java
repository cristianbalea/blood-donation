package dao;

import db.ConnectionFactory;
import domain.Donor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DonorDAO implements BasicDAO<Donor, Integer> {
    @Override
    public void create(Donor donor) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(
                    "insert into donateblood.donor (cnp, firstname, lastname, email, password, area, bloodtype) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, donor.getCnp());
            statement.setString(2, donor.getFirstName());
            statement.setString(3, donor.getLastName());
            statement.setString(4, donor.getEmail());
            statement.setString(5, donor.getPassword());
            statement.setString(6, donor.getArea());
            statement.setString(7, donor.getBloodType());

            statement.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(con);
        }
    }

    @Override
    public Donor read(Integer id) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        Donor donor = new Donor();
        try {
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(
                    "select * from donateblood.donor where id = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if(resultSet.next()) {
                donor.setId(resultSet.getInt(1));
                donor.setCnp(resultSet.getString(2));
                donor.setFirstName(resultSet.getString(3));
                donor.setLastName(resultSet.getString(4));
                donor.setEmail(resultSet.getString(5));
                donor.setPassword(resultSet.getString(6));
                donor.setArea(resultSet.getString(7));
                donor.setBloodType(resultSet.getString(8));
                return donor;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(con);
        }
        return null;
    }

    @Override
    public List<Donor> readAll() {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        List<Donor> donors = new ArrayList<>();
        try {
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(
                    "select * from donateblood.donor");
            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Donor donor = new Donor();
                donor.setId(resultSet.getInt(1));
                donor.setCnp(resultSet.getString(2));
                donor.setFirstName(resultSet.getString(3));
                donor.setLastName(resultSet.getString(4));
                donor.setEmail(resultSet.getString(5));
                donor.setPassword(resultSet.getString(6));
                donor.setArea(resultSet.getString(7));
                donor.setBloodType(resultSet.getString(8));
                donors.add(donor);
            }

            return donors;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(con);
        }
        return null;
    }

    @Override
    public void update(Donor donor) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(
                    "update donateblood.donor " +
                            "SET cnp = ?, firstname = ?, lastname = ?, email = ?, password = ?, area = ?, bloodtype = ?  " +
                            "where id = ?");
            statement.setString(1, donor.getCnp());
            statement.setString(2, donor.getFirstName());
            statement.setString(3, donor.getLastName());
            statement.setString(4, donor.getEmail());
            statement.setString(5, donor.getPassword());
            statement.setString(6, donor.getArea());
            statement.setString(7, donor.getBloodType());
            statement.setInt(8, donor.getId());

            statement.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
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
                    "delete from donateblood.donor " +
                            "where id = ?");
            statement.setInt(1, id);

            statement.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(con);
        }
    }
}
