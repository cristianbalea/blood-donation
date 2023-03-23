package dao;

import db.ConnectionFactory;
import domain.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO implements BasicDAO<Admin, Integer> {
    @Override
    public void create(Admin admin) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(
                    "insert into donateblood.admin (email, password) VALUES (?, ?)");
            statement.setString(1, admin.getEmail());
            statement.setString(2, admin.getPassword());

            statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(con);
        }
    }

    @Override
    public Admin read(Integer id) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        Admin admin = new Admin();
        try {
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(
                    "select * from donateblood.admin where id = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                admin.setId(resultSet.getInt(1));
                admin.setEmail(resultSet.getString(2));
                admin.setPassword(resultSet.getString(3));

                return admin;
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
    public List<Admin> readAll() {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        List<Admin> admins = new ArrayList<>();
        try {
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(
                    "select * from donateblood.admin");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getInt(1));
                admin.setEmail(resultSet.getString(2));
                admin.setPassword(resultSet.getString(3));
                admins.add(admin);
            }

            return admins;
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
    public void update(Admin admin) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(
                    "update donateblood.admin " +
                            "set email = ?, password = ? " +
                            "where id = ?");
            statement.setString(1, admin.getEmail());
            statement.setString(2, admin.getPassword());
            statement.setInt(3, admin.getId());

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
                    "delete from donateblood.admin " +
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
