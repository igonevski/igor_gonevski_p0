package dev.gonevski.daotests;

import dev.gonevski.data.DAOXYZSavingsAccountsImplementation;
import dev.gonevski.entities.XYZSavingsAccount;
import dev.gonevski.data.DAOXYZSavingsAccounts;
import dev.gonevski.utilities.*;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)// to run tests in order

public class DAOXYZSavingsAccountsTests {

    static DAOXYZSavingsAccounts saveDAO = new DAOXYZSavingsAccountsImplementation();
    static XYZSavingsAccount testXYZSavingsAccount = null; // Creating a test table

    @Test
    @Order(1)
    void addSavingsTest() {
        XYZSavingsAccount newXYZSavingsAccount = new XYZSavingsAccount(0, 1,"Igor",500.00);
        XYZSavingsAccount savedXYZSavingsAccount = saveDAO.addSavings(newXYZSavingsAccount);
        DAOXYZSavingsAccountsTests.testXYZSavingsAccount = savedXYZSavingsAccount;
        Assertions.assertNotEquals(0, savedXYZSavingsAccount.getSavingsId());
    }

    @Test
    @Order(2)
    void getSavingsByIdTest() {
        XYZSavingsAccount retrievedXYZSavingsAccount = saveDAO.getSavingsById(testXYZSavingsAccount.getSavingsId());
        System.out.println(retrievedXYZSavingsAccount);
        Assertions.assertEquals("Igor", retrievedXYZSavingsAccount.getSavingsName());
    }

    @Test
    @Order(3)
    void getSavingsByOwnerTest() {
        XYZSavingsAccount retrievedXYZSavingsAccount = saveDAO.getSavingsByOwner(testXYZSavingsAccount.getOwnerId());
        System.out.println(retrievedXYZSavingsAccount);
        Assertions.assertEquals("Igor", retrievedXYZSavingsAccount.getSavingsName());
    }

    @Test
    @Order(4)
    void updateSavingsTest() {
        DAOXYZSavingsAccountsTests.testXYZSavingsAccount.setSavingsName("Olivera");
        XYZSavingsAccount updatedXYZSavingsAccount = saveDAO.updateSavings(testXYZSavingsAccount);// the new title should be saved to the database
        saveDAO.getSavingsById(testXYZSavingsAccount.getSavingsId());
        Assertions.assertEquals("Olivera", updatedXYZSavingsAccount.getSavingsName());
    }

    @Test
    @Order(5)
    void deleteSavingsTest() {
        boolean result = saveDAO.deleteSavings(testXYZSavingsAccount.getSavingsId()); // true if successful
        Assertions.assertTrue(result);
    }

    @Test
    @Order(6)
    void getAllSavingsTest() {
        XYZSavingsAccount newXYZSavingsAccount1 = new XYZSavingsAccount(0,11,"acko1",123.00);
        XYZSavingsAccount newXYZSavingsAccount2 = new XYZSavingsAccount(0,12,"acko2",456.00);
        XYZSavingsAccount newXYZSavingsAccount3 = new XYZSavingsAccount(0,13,"acko3",789.00);
        saveDAO.addSavings(newXYZSavingsAccount1);
        saveDAO.addSavings(newXYZSavingsAccount2);
        saveDAO.addSavings(newXYZSavingsAccount3);
        XYZLinkedList<XYZSavingsAccount> allXYZSavingsAccounts = saveDAO.getAllSavings();
        int totalXYZSavingsAccounts = allXYZSavingsAccounts.getSize(); // if list did not work and was not tested this might fail regardless
        Assertions.assertTrue(totalXYZSavingsAccounts >= 3);
    }
}
