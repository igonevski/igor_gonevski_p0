package dev.gonevski.daotests;

import dev.gonevski.data.DAOXYZCheckingAccountsImplementation;
import dev.gonevski.entities.XYZCheckingAccount;
import dev.gonevski.data.DAOXYZCheckingAccounts;
import org.junit.jupiter.api.*;
import dev.gonevski.utilities.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)// to run tests in order

public class DAOXYZCheckingAccountsTests {

    static DAOXYZCheckingAccounts checkDAO = new DAOXYZCheckingAccountsImplementation();
    static XYZCheckingAccount testXYZCheckingAccount = null; // Creating a test table

    @Test
    @Order(1)
    void addCheckingTest(){
        XYZCheckingAccount newXYZCheckingAccount = new XYZCheckingAccount(0, 1,"SomethingNew",500.00);
        XYZCheckingAccount savedXYZCheckingAccount = checkDAO.addChecking(newXYZCheckingAccount);
        DAOXYZCheckingAccountsTests.testXYZCheckingAccount = savedXYZCheckingAccount;
        Assertions.assertNotEquals(0, savedXYZCheckingAccount.getCheckingId());
    }

    @Test
    @Order(2)
    void getCheckingByIdTest(){
        XYZCheckingAccount retrievedXYZCheckingAccount = checkDAO.getCheckingById(testXYZCheckingAccount.getCheckingId());
        System.out.println(retrievedXYZCheckingAccount);
        Assertions.assertEquals("SomethingNew", retrievedXYZCheckingAccount.getCheckingName());
    }

    @Test
    @Order(3)
    void getCheckingByOwnerTest(){
        XYZCheckingAccount retrievedXYZCheckingAccount = checkDAO.getCheckingByOwner(testXYZCheckingAccount.getOwnerId());
        System.out.println(retrievedXYZCheckingAccount);
        Assertions.assertEquals("SomethingNew", retrievedXYZCheckingAccount.getCheckingName());
    }

    @Test
    @Order(4)
    void updateCheckingTest(){
        DAOXYZCheckingAccountsTests.testXYZCheckingAccount.setCheckingName("SomethingNewToo");
        XYZCheckingAccount updatedXYZCheckingAccount = checkDAO.updateChecking(testXYZCheckingAccount);// the new title should be saved to the database
        checkDAO.getCheckingById(testXYZCheckingAccount.getCheckingId());
        Assertions.assertEquals("Olivera", updatedXYZCheckingAccount.getCheckingName());
    }

    @Test
    @Order(5)
    void deleteCheckingTest(){
        boolean result = checkDAO.deleteChecking(testXYZCheckingAccount.getCheckingId()); // true if successful
        Assertions.assertTrue(result);
    }
    @Test
    @Order(6)
    void getAllCheckingTest(){
        XYZCheckingAccount newXYZCheckingAccount1 = new XYZCheckingAccount(0,11,"acko1",123.00);
        XYZCheckingAccount newXYZCheckingAccount2 = new XYZCheckingAccount(0,12,"acko2",456.00);
        XYZCheckingAccount newXYZCheckingAccount3 = new XYZCheckingAccount(0,13,"acko3",789.00);
        checkDAO.addChecking(newXYZCheckingAccount1);
        checkDAO.addChecking(newXYZCheckingAccount2);
        checkDAO.addChecking(newXYZCheckingAccount3);
        XYZLinkedList<XYZCheckingAccount> allXYZCheckingAccounts = checkDAO.getAllChecking();
        int totalXYZCheckingAccounts = allXYZCheckingAccounts.getSize(); // if list did not work and was not tested this might fail regardless
        Assertions.assertTrue(totalXYZCheckingAccounts >= 3);
    }
}
