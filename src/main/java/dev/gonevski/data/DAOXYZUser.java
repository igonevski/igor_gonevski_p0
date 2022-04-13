package dev.gonevski.data;

import dev.gonevski.entities.XYZUser;
import dev.gonevski.utilities.XYZLinkedList;

public interface DAOXYZUser {
    // Create
    XYZUser addUser(XYZUser AddedXYZUser);
    // Read
    XYZUser getUserById(int XYZUserId);
    XYZUser getUserByName(String XYZUserName);
    // List Operation to return a list of all users in the database
    XYZLinkedList<XYZUser> getAllUsers();
    // Update
    XYZUser updateUser(XYZUser UpdatedXYZUser);
    // Delete
    boolean deleteUser(int XYZUserId);
}
