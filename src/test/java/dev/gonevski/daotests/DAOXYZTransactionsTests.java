package dev.gonevski.daotests;

import dev.gonevski.data.DAOXYZTransactionsImplementation;
import dev.gonevski.entities.XYZTransaction;
import dev.gonevski.data.DAOXYZTransactions;
import dev.gonevski.utilities.XYZLinkedList;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)// to run tests in order

public class DAOXYZTransactionsTests {

    static DAOXYZTransactions transDAO = new DAOXYZTransactionsImplementation();
    static XYZTransaction testXYZTransaction = null; // Creating a test table

    @Test
    @Order(1)
    void addTransactionTest () {
        XYZTransaction newXYZTransaction = new XYZTransaction(0, 1, "Transfer", "Checking", "Savings", 500.00, System.currentTimeMillis());
        XYZTransaction savedXYZTransaction = transDAO.addTransaction(newXYZTransaction);
        DAOXYZTransactionsTests.testXYZTransaction = savedXYZTransaction;
        Assertions.assertNotEquals(0, savedXYZTransaction.getTransactionId());
    }

    @Test
    @Order(2)
    void getTransactionTest() {
        XYZTransaction retrievedXYZTransaction = transDAO.getTransactionById(testXYZTransaction.getTransactionId());
        System.out.println(retrievedXYZTransaction);
        Assertions.assertEquals(1, retrievedXYZTransaction.getOwnerId());
    }

    @Test
    @Order(3)
    void getTransactionByOwnerTest() {
        XYZTransaction retrievedXYZTransaction = transDAO.getTransactionByOwner(testXYZTransaction.getOwnerId());
        System.out.println(retrievedXYZTransaction);
        Assertions.assertEquals(1, retrievedXYZTransaction.getOwnerId());
    }

    @Test
    @Order(4)
    void updateTransactionTest() {
        DAOXYZTransactionsTests.testXYZTransaction.setOwnerId(2);
        XYZTransaction updatedXYZTransaction = transDAO.updateTransaction(testXYZTransaction);// the new title should be saved to the database
        transDAO.getTransactionById(testXYZTransaction.getTransactionId());
        Assertions.assertEquals(2, updatedXYZTransaction.getOwnerId());
    }

    @Test
    @Order(5)
    void deleteTransactionTest() {
        boolean result = transDAO.deleteTransaction(testXYZTransaction.getTransactionId()); // true if successful
        Assertions.assertTrue(result);
    }

//    @Test
//    @Order(6)
//    void getCheckingTransactionsTest() {}
//
//    @Test
//    @Order(7)
//    void getSavingsTransactionsTest() {}
//
    @Test
    @Order(6)
    void getAllTransactionsTest() {
        XYZTransaction newXYZTransaction1 = new XYZTransaction(0,11,"deposit","N/A","Checking",123.00, System.currentTimeMillis());
        XYZTransaction newXYZTransaction2 = new XYZTransaction(0,12,"withdrawl","Savings","N/A",456.00, System.currentTimeMillis());
        XYZTransaction newXYZTransaction3 = new XYZTransaction(0,13,"transfer","Checking","Savings",789.00, System.currentTimeMillis());
        transDAO.addTransaction(newXYZTransaction1);
        transDAO.addTransaction(newXYZTransaction2);
        transDAO.addTransaction(newXYZTransaction3);
        XYZLinkedList<XYZTransaction> allXYZTransactions = transDAO.getAllTransactions();
        int totalXYZTransactions = allXYZTransactions.getSize(); // if list did not work and was not tested this might fail regardless
        Assertions.assertTrue(totalXYZTransactions >= 3);
    }
}
