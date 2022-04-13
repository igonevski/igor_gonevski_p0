package dev.gonevski.data;

import dev.gonevski.entities.XYZCheckingAccount;
import dev.gonevski.utilities.XYZLinkedList;

public interface DAOXYZCheckingAccounts {
    // Create
    XYZCheckingAccount addChecking(XYZCheckingAccount AddedXYZCheckingAccount);
    // Read by Checking Account ID
    XYZCheckingAccount getCheckingById(int XYZCheckingAccountId);
    // Read by Owner ID
    XYZCheckingAccount getCheckingByOwner(int XYZCheckingAccountOwnerId);

    // List operations to get list of all checking accounts from the database
    XYZLinkedList<XYZCheckingAccount> getAllChecking();

    // Update
    XYZCheckingAccount updateChecking(XYZCheckingAccount UpdatedXYZCheckingAccount);
    // Delete
    boolean deleteChecking(int XYZCheckingAccountId);
}
