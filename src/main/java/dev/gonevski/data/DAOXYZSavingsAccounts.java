package dev.gonevski.data;

import dev.gonevski.entities.XYZSavingsAccount;
import dev.gonevski.utilities.XYZLinkedList;

public interface DAOXYZSavingsAccounts {
    // Create
    XYZSavingsAccount addSavings(XYZSavingsAccount AddedXYZSavingsAccount);
    // Read by Savings Account ID
    XYZSavingsAccount getSavingsById(int XYZSavingsAccountId);
    // Read by Owner ID
    XYZSavingsAccount getSavingsByOwner(int XYZSavingsAccountOwner);

    // List Operations to get list of all accounts in the Savings Account Database
    XYZLinkedList<XYZSavingsAccount> getAllSavings();

    // Update
    XYZSavingsAccount updateSavings(XYZSavingsAccount UpdatedXYZSavingsAccount);
    // Delete
    boolean deleteSavings(int XYZSavingsAccountId);

}
