package dev.gonevski.data;

import dev.gonevski.entities.XYZTransaction;
import dev.gonevski.utilities.XYZLinkedList;

public interface DAOXYZTransactions {
    // Create
    XYZTransaction addTransaction(XYZTransaction AddedXYZTransaction);
    // Read by Transaction ID
    XYZTransaction getTransactionById(int XYZTransactionId);
    // Read by Owner ID
    XYZTransaction getTransactionByOwner(int XYZTransactionOwnerId);
    // List Operations for the Transactions Database
    XYZLinkedList<XYZTransaction> getCheckingTransactions(int XYZTransactionOwnerId);
    XYZLinkedList<XYZTransaction> getSavingsTransactions(int XYZTransactionOwnerId);
    XYZLinkedList<XYZTransaction> getAllTransactions();
    // Update
    XYZTransaction updateTransaction(XYZTransaction UpdatedXYZTransaction);
    // Delete
    boolean deleteTransaction(int XYZTransactionId);
}
