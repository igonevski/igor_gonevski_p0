package dev.gonevski.data;

import dev.gonevski.entities.XYZUser;
import dev.gonevski.utilities.*;
import java.sql.*;

public class DAOXYZUserImplementation implements DAOXYZUser {

    @Override
    public XYZUser addUser(XYZUser AddedXYZUser) {
        try {
            Connection conn = XYZConnectionUtility.createConnection();
            String sql = "insert into xyz_user values (default,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, AddedXYZUser.getUserLogin()); // for the 1st question mark what is the value
            ps.setString(2, AddedXYZUser.getUserPassword());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys(); // ResultSet a virtual table of results
            rs.next();// move to the first record of the result set
            int generatedId = rs.getInt("xyz_user_id");
            AddedXYZUser.setUserId(generatedId);
            return AddedXYZUser;
        }
        catch (SQLException e) {
            e.printStackTrace();
            XYZLogger.log(e.getMessage(), XYZLogLevel.ERROR);
            return null;
        }
    }
    @Override
    public XYZUser getUserById(int XYZUserId) {
        try {
            Connection conn = XYZConnectionUtility.createConnection();
            String sql = "select * from xyz_user where xyz_user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, XYZUserId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                XYZUser newXYZUser = new XYZUser();
                newXYZUser.setUserId(rs.getInt("xyz_user_id"));
                newXYZUser.setUserLogin(rs.getString("xyz_user_login"));
                newXYZUser.setUserPassword(rs.getString("xyz_user_password"));
                return newXYZUser;
            }
//            return null;
        }
        catch (SQLException e) {
            e.printStackTrace();
            XYZLogger.log(e.getMessage(), XYZLogLevel.ERROR);
        }
        return null;
    }
    @Override
    public XYZUser getUserByName(String XYZUserName) {
        try {
            Connection conn = XYZConnectionUtility.createConnection();
            String sql = "select * from xyz_user where xyz_user_login = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, XYZUserName);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                XYZUser newXYZUser = new XYZUser();
                newXYZUser.setUserId(rs.getInt("xyz_user_id"));
                newXYZUser.setUserLogin(rs.getString("xyz_user_login"));
                newXYZUser.setUserPassword(rs.getString("xyz_user_password"));
                return newXYZUser;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            XYZLogger.log(e.getMessage(), XYZLogLevel.ERROR);
        }
        return null;
    }


    @Override
    public XYZLinkedList<XYZUser> getAllUsers() {
        try {
            Connection conn = XYZConnectionUtility.createConnection();
            String sql = "select * from xyz_user";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            XYZLinkedList<XYZUser> allXYZUsers = new XYZLinkedList<>();
            while (rs.next()){
                XYZUser newXYZUser = new XYZUser();
                newXYZUser.setUserId(rs.getInt("xyz_user_id"));
                newXYZUser.setUserLogin(rs.getString("xyz_user_login"));
                newXYZUser.setUserPassword(rs.getString("xyz_user_password"));
                allXYZUsers.add(newXYZUser);
            }
            return allXYZUsers;

        }
        catch (SQLException e) {
            e.printStackTrace();
            XYZLogger.log(e.getMessage(), XYZLogLevel.ERROR);
        }
        return null;
    }

    public XYZUser updateUser(XYZUser UpdatedXYZUser) {
        try {
            Connection conn = XYZConnectionUtility.createConnection();
            String sql = "update xyz_user set xyz_user_login = ?, xyz_user_password = ? where xyz_user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, UpdatedXYZUser.getUserLogin());
            ps.setString(2, UpdatedXYZUser.getUserPassword());
            ps.setInt(3, UpdatedXYZUser.getUserId());
            ps.executeUpdate();
            return  UpdatedXYZUser;

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }
    @Override
    public boolean deleteUser(int XYZUserId) {
        try {
            Connection conn = XYZConnectionUtility.createConnection();
            String sql = "delete from xyz_user where xyz_user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, XYZUserId);
            ps.execute();
            return  true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
