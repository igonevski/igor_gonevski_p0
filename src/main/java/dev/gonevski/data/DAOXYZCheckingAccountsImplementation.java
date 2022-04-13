package dev.gonevski.data;

import dev.gonevski.entities.XYZCheckingAccount;
import dev.gonevski.utilities.*;
import java.sql.*;

public class DAOXYZCheckingAccountsImplementation implements DAOXYZCheckingAccounts {

    @Override
    public XYZCheckingAccount addChecking(XYZCheckingAccount AddedXYZCheckingAccount) {
        try {
            Connection conn = XYZConnectionUtility.createConnection();
            String sql = "insert into xyz_checking_accounts values (default,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, AddedXYZCheckingAccount.getOwnerId()); // for the 1st question mark what is the value
            ps.setString(2, AddedXYZCheckingAccount.getCheckingName());
            ps.setDouble(3, AddedXYZCheckingAccount.getCheckingBalance());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys(); // ResultSet a virtual table of results
            rs.next();// move to the first record of the result set
            int generatedId = rs.getInt("xyz_checking_id");
            AddedXYZCheckingAccount.setCheckingId(generatedId);
            return AddedXYZCheckingAccount;
        }
        catch (SQLException e) {
            e.printStackTrace();
            XYZLogger.log(e.getMessage(), XYZLogLevel.ERROR);
            return null;
        }
    }
    // Read
    @Override
    public XYZCheckingAccount getCheckingById(int XYZCheckingAccountId) {
        try {
            Connection conn = XYZConnectionUtility.createConnection();
            String sql = "select * from xyz_checking_accounts where xyz_checking_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, XYZCheckingAccountId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                XYZCheckingAccount newXYZCheckingAccount = new XYZCheckingAccount();
                newXYZCheckingAccount.setCheckingId(rs.getInt("xyz_checking_id"));
                newXYZCheckingAccount.setOwnerId(rs.getInt("xyz_owner_id"));
                newXYZCheckingAccount.setCheckingName(rs.getString("xyz_checking_account_name"));
                newXYZCheckingAccount.setCheckingBalance(rs.getDouble("xyz_checking_account_balance"));
                return newXYZCheckingAccount;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            XYZLogger.log(e.getMessage(), XYZLogLevel.ERROR);
        }
        return null;
    }
    @Override
    public XYZCheckingAccount getCheckingByOwner(int XYZCheckingAccountOwnerId) {
        try {
            Connection conn = XYZConnectionUtility.createConnection();
            String sql = "select * from xyz_checking_accounts where xyz_owner_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, XYZCheckingAccountOwnerId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) { // move to first record
                XYZCheckingAccount newXYZCheckingAccount = new XYZCheckingAccount();
                newXYZCheckingAccount.setCheckingId(rs.getInt("xyz_checking_id"));
                newXYZCheckingAccount.setOwnerId(rs.getInt("xyz_owner_id"));
                newXYZCheckingAccount.setCheckingName(rs.getString("xyz_checking_account_name"));
                newXYZCheckingAccount.setCheckingBalance(rs.getDouble("xyz_checking_account_balance"));
                return newXYZCheckingAccount;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            XYZLogger.log(e.getMessage(), XYZLogLevel.ERROR);
        }
        return null;
    }
    @Override
    public XYZLinkedList<XYZCheckingAccount> getAllChecking() {
        try {
            Connection conn = XYZConnectionUtility.createConnection();
            String sql = "select * from xyz_checking_accounts";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            XYZLinkedList<XYZCheckingAccount> allXYZCheckingAccounts = new XYZLinkedList<>();
            while (rs.next()){
                XYZCheckingAccount newXYZCheckingAccount = new XYZCheckingAccount();
                newXYZCheckingAccount.setCheckingId(rs.getInt("xyz_checking_id"));
                newXYZCheckingAccount.setOwnerId(rs.getInt("xyz_owner_id"));
                newXYZCheckingAccount.setCheckingName(rs.getString("xyz_checking_account_name"));
                newXYZCheckingAccount.setCheckingBalance(rs.getDouble("xyz_checking_account_balance"));
                allXYZCheckingAccounts.add(newXYZCheckingAccount);
            }
            return allXYZCheckingAccounts;
        }
        catch (SQLException e) {
            e.printStackTrace();
            XYZLogger.log(e.getMessage(), XYZLogLevel.ERROR);
            return null;
        }
    }

    // Update
    @Override
    public XYZCheckingAccount updateChecking(XYZCheckingAccount UpdatedXYZCheckingAccount) {
        try {
            Connection conn = XYZConnectionUtility.createConnection();
            String sql = "update xyz_checking_accounts set xyz_owner_id = ?, xyz_checking_account_name = ?, xyz_checking_account_balance = ? where xyz_checking_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, UpdatedXYZCheckingAccount.getOwnerId());
            ps.setString(2, UpdatedXYZCheckingAccount.getCheckingName());
            ps.setDouble(3, UpdatedXYZCheckingAccount.getCheckingBalance());
            ps.setInt(4, UpdatedXYZCheckingAccount.getCheckingId());
            ps.executeUpdate();
            return  UpdatedXYZCheckingAccount;

        }
        catch (SQLException e) {
            e.printStackTrace();
            return  null;
        }
    }
    // Delete
    @Override
    public boolean deleteChecking(int XYZCheckingAccountId) {
        try {
            Connection conn = XYZConnectionUtility.createConnection();
            String sql = "delete from xyz_checking_accounts where xyz_checking_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, XYZCheckingAccountId);
            ps.execute();
            return  true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
