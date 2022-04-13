package dev.gonevski.data;

import dev.gonevski.entities.XYZTransaction;
import dev.gonevski.utilities.*;
import java.sql.*;

public class DAOXYZTransactionsImplementation implements DAOXYZTransactions {

    @Override
    public XYZTransaction addTransaction(XYZTransaction AddedXYZTransaction) {
        try {
            Connection conn = XYZConnectionUtility.createConnection();
            String sql = "insert into xyz_transactions values (default,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, AddedXYZTransaction.getOwnerId()); // for the 1st question mark what is the value
            ps.setString(2, AddedXYZTransaction.getType());
            ps.setString(3, AddedXYZTransaction.getFromAccount());
            ps.setString(4, AddedXYZTransaction.getToAccount());
            ps.setDouble(5, AddedXYZTransaction.getAmount());
            ps.setLong(6, AddedXYZTransaction.getTimestamp());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys(); // ResultSet a virtual table of results
            rs.next();// move to the first record of the result set
            int generatedId = rs.getInt("xyz_transaction_id");
            AddedXYZTransaction.setTransactionId(generatedId);
            return AddedXYZTransaction;
        }
        catch (SQLException e) {
            e.printStackTrace();
            XYZLogger.log(e.getMessage(), XYZLogLevel.ERROR);
            return null;
        }
    }
    // Read
    @Override
    public XYZTransaction getTransactionById(int XYZTransactionId) {
        try {
            Connection conn = XYZConnectionUtility.createConnection();
            String sql = "select * from xyz_transactions where xyz_transaction_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, XYZTransactionId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                XYZTransaction newXYZTransaction = new XYZTransaction();
                newXYZTransaction.setTransactionId(rs.getInt("xyz_transaction_id"));
                newXYZTransaction.setOwnerId(rs.getInt("xyz_owner_id"));
                newXYZTransaction.setType(rs.getString("xyz_transaction_type"));
                newXYZTransaction.setFromAccount(rs.getString("xyz_transaction_source_account"));
                newXYZTransaction.setToAccount(rs.getString("xyz_transaction_destination_account"));
                newXYZTransaction.setAmount(rs.getDouble("xyz_transaction_amount"));
                newXYZTransaction.setTimestamp(rs.getLong("xyz_transaction_timestamp"));
                return newXYZTransaction;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            XYZLogger.log(e.getMessage(), XYZLogLevel.ERROR);
        }
        return null;
    }
    @Override
    public XYZTransaction getTransactionByOwner(int XYZTransactionOwnerId) {
        try {
            Connection conn = XYZConnectionUtility.createConnection();
            String sql = "select * from xyz_transactions where xyz_owner_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, XYZTransactionOwnerId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                XYZTransaction newXYZTransaction = new XYZTransaction();
                newXYZTransaction.setTransactionId(rs.getInt("xyz_transaction_id"));
                newXYZTransaction.setOwnerId(rs.getInt("xyz_owner_id"));
                newXYZTransaction.setType(rs.getString("xyz_transaction_type"));
                newXYZTransaction.setFromAccount(rs.getString("xyz_transaction_source_account"));
                newXYZTransaction.setToAccount(rs.getString("xyz_transaction_destination_account"));
                newXYZTransaction.setAmount(rs.getDouble("xyz_transaction_amount"));
                newXYZTransaction.setTimestamp(rs.getLong("xyz_transaction_timestamp"));
                return newXYZTransaction;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            XYZLogger.log(e.getMessage(), XYZLogLevel.ERROR);
        }
        return null;
    }
    @Override
    public XYZLinkedList<XYZTransaction> getCheckingTransactions(int XYZTransactionOwnerId){
        try {
            Connection conn = XYZConnectionUtility.createConnection();
            String sql = "select * from xyz_transactions where xyz_owner_id = ? and (xyz_transaction_source_account = 'Checking' or xyz_transaction_destination_account = 'Checking')";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, XYZTransactionOwnerId);

            ResultSet rs = ps.executeQuery();

            XYZLinkedList<XYZTransaction> allXYZTransactions = new XYZLinkedList<>();
            while (rs.next()){
                XYZTransaction newXYZTransaction = new XYZTransaction();
                newXYZTransaction.setTransactionId(rs.getInt("xyz_transaction_id"));
                newXYZTransaction.setOwnerId(rs.getInt("xyz_owner_id"));
                newXYZTransaction.setFromAccount(rs.getString("xyz_transaction_source_account"));
                newXYZTransaction.setToAccount(rs.getString("xyz_transaction_destination_account"));
                newXYZTransaction.setType(rs.getString("xyz_transaction_type"));
                newXYZTransaction.setAmount(rs.getDouble("xyz_transaction_amount"));
                newXYZTransaction.setTimestamp(rs.getLong("xyz_transaction_timestamp"));
                allXYZTransactions.add(newXYZTransaction);
            }
            return allXYZTransactions;
        }
        catch (SQLException e) {
            e.printStackTrace();
            XYZLogger.log(e.getMessage(), XYZLogLevel.ERROR);
            return null;
        }
    }
    @Override
    public XYZLinkedList<XYZTransaction> getSavingsTransactions(int XYZTransactionOwnerId){
        try {
            Connection conn = XYZConnectionUtility.createConnection();
            String sql = "select * from xyz_transactions where xyz_owner_id = ? and (xyz_transaction_source_account = 'Savings' or xyz_transaction_destination_account = 'Savings')";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, XYZTransactionOwnerId);

            ResultSet rs = ps.executeQuery();

            XYZLinkedList<XYZTransaction> allXYZTransactions = new XYZLinkedList<>();
            while (rs.next()){
                XYZTransaction newXYZTransaction = new XYZTransaction();
                newXYZTransaction.setTransactionId(rs.getInt("xyz_transaction_id"));
                newXYZTransaction.setOwnerId(rs.getInt("xyz_owner_id"));
                newXYZTransaction.setFromAccount(rs.getString("xyz_transaction_source_account"));
                newXYZTransaction.setToAccount(rs.getString("xyz_transaction_destination_account"));
                newXYZTransaction.setType(rs.getString("xyz_transaction_type"));
                newXYZTransaction.setAmount(rs.getDouble("xyz_transaction_amount"));
                newXYZTransaction.setTimestamp(rs.getLong("xyz_transaction_timestamp"));
                allXYZTransactions.add(newXYZTransaction);
            }
            return allXYZTransactions;
        }
        catch (SQLException e) {
            e.printStackTrace();
            XYZLogger.log(e.getMessage(), XYZLogLevel.ERROR);
            return null;
        }
    }
    @Override
    public XYZLinkedList<XYZTransaction> getAllTransactions(){
        try {
            Connection conn = XYZConnectionUtility.createConnection();
            String sql = "select * from xyz_transactions";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            XYZLinkedList<XYZTransaction> allXYZTransactions = new XYZLinkedList<>();
            while (rs.next()){
                XYZTransaction newXYZTransaction = new XYZTransaction();
                newXYZTransaction.setTransactionId(rs.getInt("xyz_transaction_id"));
                newXYZTransaction.setOwnerId(rs.getInt("xyz_owner_id"));
                newXYZTransaction.setFromAccount(rs.getString("xyz_transaction_source_account"));
                newXYZTransaction.setToAccount(rs.getString("xyz_transaction_destination_account"));
                newXYZTransaction.setType(rs.getString("xyz_transaction_type"));
                newXYZTransaction.setAmount(rs.getDouble("xyz_transaction_amount"));
                newXYZTransaction.setTimestamp(rs.getLong("xyz_transaction_timestamp"));
                allXYZTransactions.add(newXYZTransaction);
            }
            return allXYZTransactions;
        }
        catch (SQLException e) {
            e.printStackTrace();
            XYZLogger.log(e.getMessage(), XYZLogLevel.ERROR);
            return null;
        }
    }

    // Update
    @Override
    public XYZTransaction updateTransaction(XYZTransaction UpdatedXYZTransaction) {
        try {
            Connection conn = XYZConnectionUtility.createConnection();
            String sql = "update xyz_transactions set xyz_owner_id = ?, xyz_transaction_type = ?, xyz_transaction_source_account = ?, xyz_transaction_destination_account = ?, xyz_transaction_amount = ?, xyz_transaction_timestamp = ? where xyz_transaction_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, UpdatedXYZTransaction.getOwnerId()); // for the 1st question mark what is the value
            ps.setString(2, UpdatedXYZTransaction.getType());
            ps.setString(3, UpdatedXYZTransaction.getFromAccount());
            ps.setString(4, UpdatedXYZTransaction.getToAccount());
            ps.setDouble(5, UpdatedXYZTransaction.getAmount());
            ps.setLong(6, UpdatedXYZTransaction.getTimestamp());
            ps.setInt(7, UpdatedXYZTransaction.getTransactionId());
            ps.executeUpdate();
            return  UpdatedXYZTransaction;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return  null;
        }
    }
    // Delete
    @Override
    public boolean deleteTransaction(int XYZTransactionId) {
        try {
            Connection conn = XYZConnectionUtility.createConnection();
            String sql = "delete from xyz_transactions where xyz_transaction_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, XYZTransactionId);
            ps.execute();
            return  true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
