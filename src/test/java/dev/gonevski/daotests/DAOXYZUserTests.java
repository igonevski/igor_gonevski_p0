package dev.gonevski.daotests;

import dev.gonevski.data.DAOXYZUserImplementation;
import dev.gonevski.entities.XYZUser;
import dev.gonevski.data.DAOXYZUser;
import dev.gonevski.utilities.XYZLinkedList;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)// to run tests in order

public class DAOXYZUserTests {

    static DAOXYZUser userDAO = new DAOXYZUserImplementation();
    static XYZUser testXYZUser = null; // Creating a test table

    @Test
    @Order(1)
    void addUserTest() {
        XYZUser newXYZUser = new XYZUser(0, "acko", "packo");
        XYZUser savedXYZUser = userDAO.addUser(newXYZUser);
        DAOXYZUserTests.testXYZUser = savedXYZUser;
        Assertions.assertNotEquals(0, savedXYZUser.getUserId());
    }

    @Test
    @Order(2)
    void getUserByIdTest() {
        XYZUser retrievedXYZUser = userDAO.getUserById(testXYZUser.getUserId());
        System.out.println(retrievedXYZUser);
        Assertions.assertEquals("acko", retrievedXYZUser.getUserLogin());
    }

    @Test
    @Order(3)
    void getUserByLoginTest() {
        XYZUser retrievedXYZUser = userDAO.getUserByName(testXYZUser.getUserLogin());
        System.out.println(retrievedXYZUser);
        Assertions.assertEquals("acko", retrievedXYZUser.getUserLogin());
    }

    @Test
    @Order(4)
    void updateUserTest() {
        DAOXYZUserTests.testXYZUser.setUserLogin("ocko");
        XYZUser updatedXYZUser = userDAO.updateUser(testXYZUser);// the new title should be saved to the database
        userDAO.getUserById(testXYZUser.getUserId());
        Assertions.assertEquals("ocko", updatedXYZUser.getUserLogin());
    }

    @Test
    @Order(5)
    void deleteUserTest() {
        boolean result = userDAO.deleteUser(testXYZUser.getUserId()); // true if successful
        Assertions.assertTrue(result);
    }

    @Test
    @Order(6)
    void getAllUsersTest() {
        XYZUser newXYZUserAccount1 = new XYZUser(0,"acko1","packo1");
        XYZUser newXYZUserAccount2 = new XYZUser(0,"acko2","packo2");
        XYZUser newXYZUserAccount3 = new XYZUser(0,"acko3","packo3");
        userDAO.addUser(newXYZUserAccount1);
        userDAO.addUser(newXYZUserAccount2);
        userDAO.addUser(newXYZUserAccount3);
        XYZLinkedList<XYZUser> allXYZUsers = userDAO.getAllUsers();
        int totalXYZUsers = allXYZUsers.getSize(); // if list did not work and was not tested this might fail regardless
        Assertions.assertTrue(totalXYZUsers >= 3);
    }
}
