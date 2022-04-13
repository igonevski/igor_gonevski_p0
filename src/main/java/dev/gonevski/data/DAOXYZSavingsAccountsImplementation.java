package dev.gonevski.data;

import dev.gonevski.entities.XYZSavingsAccount;
import dev.gonevski.utilities.*;
import java.sql.*;

public class DAOXYZSavingsAccountsImplementation implements DAOXYZSavingsAccounts {

    @Override
    public XYZSavingsAccount addSavings(XYZSavingsAccount AddedXYZSavingsAccount) {
        try {
            Connection conn = XYZConnectionUtility.createConnection();
            String sql = "insert into xyz_savings_accounts values (default,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, AddedXYZSavingsAccount.getOwnerId()); // for the 1st question mark what is the value
            ps.setString(2, AddedXYZSavingsAccount.getSavingsName());
            ps.setDouble(3, AddedXYZSavingsAccount.getSavingsBalance());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys(); // ResultSet a virtual table of results
            rs.next();// move to the first record of the result set
            int generatedId = rs.getInt("xyz_savings_id");
            AddedXYZSavingsAccount.setSavingsId(generatedId);
            return AddedXYZSavingsAccount;
        }
        catch (SQLException e) {
            e.printStackTrace();
            XYZLogger.log(e.getMessage(), XYZLogLevel.ERROR);
            return null;
        }
    }
    // Read
    @Override
    public XYZSavingsAccount getSavingsById(int XYZSavingsAccountId) {
        try {
            Connection conn = XYZConnectionUtility.createConnection();
            String sql = "select * from xyz_savings_accounts where xyz_savings_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, XYZSavingsAccountId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                XYZSavingsAccount newXYZSavingsAccount = new XYZSavingsAccount();
                newXYZSavingsAccount.setSavingsId(rs.getInt("xyz_savings_id"));
                newXYZSavingsAccount.setOwnerId(rs.getInt("xyz_owner_id"));
                newXYZSavingsAccount.setSavingsName(rs.getString("xyz_savings_account_name"));
                newXYZSavingsAccount.setSavingsBalance(rs.getDouble("xyz_savings_account_balance"));
                return newXYZSavingsAccount;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            XYZLogger.log(e.getMessage(), XYZLogLevel.ERROR);
        }
        return null;
    }
    @Override
    public XYZSavingsAccount getSavingsByOwner(int XYZSavingsAccountOwnerId) {
        try {
            Connection conn = XYZConnectionUtility.createConnection();
            String sql = "select * from xyz_savings_accounts where xyz_owner_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, XYZSavingsAccountOwnerId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                XYZSavingsAccount newXYZSavingsAccount = new XYZSavingsAccount();
                newXYZSavingsAccount.setSavingsId(rs.getInt("xyz_savings_id"));
                newXYZSavingsAccount.setOwnerId(rs.getInt("xyz_owner_id"));
                newXYZSavingsAccount.setSavingsName(rs.getString("xyz_savings_account_name"));
                newXYZSavingsAccount.setSavingsBalance(rs.getDouble("xyz_savings_account_balance"));
                return newXYZSavingsAccount;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            XYZLogger.log(e.getMessage(), XYZLogLevel.ERROR);
        }
        return null;
    }

    @Override
    public XYZLinkedList<XYZSavingsAccount> getAllSavings() {
        try {
            Connection conn = XYZConnectionUtility.createConnection();
            String sql = "select * from xyz_savings_accounts";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            XYZLinkedList<XYZSavingsAccount> allXYZSavingsAccounts = new XYZLinkedList<>();
            while (rs.next()){
                XYZSavingsAccount newXYZSavingsAccount = new XYZSavingsAccount();
                newXYZSavingsAccount.setSavingsId(rs.getInt("xyz_savings_id"));
                newXYZSavingsAccount.setOwnerId(rs.getInt("xyz_owner_id"));
                newXYZSavingsAccount.setSavingsName(rs.getString("xyz_savings_account_name"));
                newXYZSavingsAccount.setSavingsBalance(rs.getDouble("xyz_savings_account_balance"));
                allXYZSavingsAccounts.add(newXYZSavingsAccount);
            }
            return allXYZSavingsAccounts;
        }
        catch (SQLException e) {
            e.printStackTrace();
            XYZLogger.log(e.getMessage(), XYZLogLevel.ERROR);
            return null;
        }
    }


    // Update
    @Override
    public XYZSavingsAccount updateSavings(XYZSavingsAccount UpdatedXYZSavingsAccount) {
        try {
            Connection conn = XYZConnectionUtility.createConnection();
            String sql = "update xyz_savings_accounts set xyz_owner_id = ?, xyz_savings_account_name = ?, xyz_savings_account_balance = ? where xyz_savings_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, UpdatedXYZSavingsAccount.getOwnerId());
            ps.setString(2, UpdatedXYZSavingsAccount.getSavingsName());
            ps.setDouble(3, UpdatedXYZSavingsAccount.getSavingsBalance());
            ps.setInt(4, UpdatedXYZSavingsAccount.getSavingsId());
            ps.executeUpdate();
            return  UpdatedXYZSavingsAccount;

        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        }
    }
    // Delete
    @Override
    public boolean deleteSavings(int XYZSavingsAccountId) {
        try {
            Connection conn = XYZConnectionUtility.createConnection();
            String sql = "delete from xyz_savings_accounts where xyz_savings_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, XYZSavingsAccountId);
            ps.execute();
            return  true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
