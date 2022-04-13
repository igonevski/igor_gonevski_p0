package dev.gonevski.services;

import dev.gonevski.api.Main;
import dev.gonevski.entities.XYZCheckingAccount;
import dev.gonevski.entities.XYZSavingsAccount;

import dev.gonevski.data.DAOXYZUserImplementation;
import dev.gonevski.entities.XYZUser;
import dev.gonevski.data.DAOXYZUser;
import dev.gonevski.utilities.XYZList;

import dev.gonevski.data.DAOXYZCheckingAccounts;
import dev.gonevski.data.DAOXYZCheckingAccountsImplementation;
import dev.gonevski.data.DAOXYZTransactionsImplementation;
import dev.gonevski.entities.XYZCheckingAccount;
import dev.gonevski.data.DAOXYZTransactions;
import dev.gonevski.entities.XYZTransaction;
import dev.gonevski.utilities.XYZList;

import dev.gonevski.data.DAOXYZSavingsAccounts;
import dev.gonevski.data.DAOXYZSavingsAccountsImplementation;
import dev.gonevski.data.DAOXYZTransactionsImplementation;
import dev.gonevski.entities.XYZSavingsAccount;
import dev.gonevski.data.DAOXYZTransactions;
import dev.gonevski.entities.XYZTransaction;
import dev.gonevski.utilities.XYZList;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class XYZTransactionServiceImplementation implements XYZTransactionService {

    // Dependency Injection - Building an object that uses another object within it.
    static DAOXYZTransactions transDAO = new DAOXYZTransactionsImplementation();

    // Dependency Injection. Building an object that uses another object within it
    public XYZTransactionServiceImplementation(DAOXYZTransactions transDAO) {
        this.transDAO = transDAO;
    }

    // Dependency Injection - Building an object that uses another object within it.
    static DAOXYZSavingsAccounts saveDAO = new DAOXYZSavingsAccountsImplementation();

    // Dependency Injection. Building an object that uses another object within it
    public void XYZSavingsAccountServiceImplementation(DAOXYZSavingsAccounts saveDAO) {
        this.saveDAO = saveDAO;
    }

    // Dependency Injection - Building an object that uses another object within it.
    static DAOXYZCheckingAccounts checkDAO = new DAOXYZCheckingAccountsImplementation();

    // Dependency Injection. Building an object that uses another object within it
    public void XYZCheckingAccountServiceImplementation(DAOXYZCheckingAccounts checkDAO) {
        this.checkDAO = checkDAO;
    }

    @Override
    public void transfer(XYZCheckingAccount c, XYZSavingsAccount s, double cBalance, double sBalance) {
        Scanner input = new Scanner(System.in);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        int option;
        Main.checkingView = true;
        double transfer = 0;
        boolean validTransfer = false;
        boolean cancelTransfer = false;

        if (s.getSavingsId() == 0) {
            System.out.println("ERROR: You don't have a Savings account to transfer to! Please create one.");
            Main.accountsView = false;
            Main.checkingView = false;
            return;
        }

        System.out.println("Enter an amount to transfer in the following format: ##.##");
        while (!validTransfer && !cancelTransfer) {
            try {
                transfer = input.nextDouble();

                while (transfer > cBalance || transfer <= 0) {
                    System.out.println("ERROR: Invalid transfer amount.");
                    option = Main.accountActionErrorMenu();

                    if (option == 1) {
                        System.out.println("Enter a new amount:");
                        transfer = input.nextDouble();
                    } else if (option == 2) {
                        cancelTransfer = true;
                        transfer = 0;
                        break;
                    }
                }

                if (transfer <= cBalance && transfer != 0) {
                    validTransfer = true;
                }

            } catch (InputMismatchException e) {
                System.out.println("ERROR: Invalid input. Enter an amount to transfer" +
                        " in the following format: ##.##");
                input.next();
            }
        }
        c.setCheckingBalance(cBalance - transfer);
        s.setSavingsBalance(sBalance + transfer);
        this.checkDAO.updateChecking(c);
        this.saveDAO.updateSavings(s);

        if (validTransfer) {
            long currentTime = System.currentTimeMillis();
            XYZTransaction transaction = new XYZTransaction(0, Main.loggedInUserId, "Transfer", "Checking", "Savings", transfer, currentTime);
            this.transDAO.addTransaction(transaction);
            System.out.println("Transfer success: " + formatter.format(transfer));
        }

        Main.checkingBalance = c.getCheckingBalance();
        Main.checkingView = false;
    }

    @Override
    public void transfer(XYZSavingsAccount s, XYZCheckingAccount c, double sBalance, double cBalance) {
        Scanner input = new Scanner(System.in);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        int option;
        Main.savingsView = true;
        double transfer = 0;
        boolean validTransfer = false;
        boolean cancelTransfer = false;

        if (c.getCheckingId() == 0) {
            System.out.println("ERROR: You don't have a Checking account to transfer to! Please create one.");
            Main.accountsView = false;
            Main.savingsView = false;
            return;
        }

        System.out.println("Enter an amount to transfer in the following format: ##.##");
        while (!validTransfer && !cancelTransfer) {
            try {
                transfer = input.nextDouble();

                while (transfer > sBalance || transfer <= 0) {
                    System.out.println("ERROR: Invalid transfer amount.");
                    option = Main.accountActionErrorMenu();

                    if (option == 1) {
                        System.out.println("Enter a new amount:");
                        transfer = input.nextDouble();
                    } else if (option == 2) {
                        cancelTransfer = true;
                        transfer = 0;
                        break;
                    }
                }

                if (transfer <= sBalance && transfer != 0) {
                    validTransfer = true;
                }

            } catch (InputMismatchException e) {
                System.out.println("ERROR: Invalid input. Enter an amount to transfer" +
                        " in the following format: ##.##");
                input.next();
            }
        }
        s.setSavingsBalance(sBalance - transfer);
        c.setCheckingBalance(cBalance + transfer);
        this.saveDAO.updateSavings(s);
        this.checkDAO.updateChecking(c);

        if (validTransfer) {
            long currentTime = System.currentTimeMillis();
            XYZTransaction transaction = new XYZTransaction(0, Main.loggedInUserId, "Transfer", "Savings", "Checking", transfer, currentTime);
            this.transDAO.addTransaction(transaction);
            System.out.println("Transfer success: " + formatter.format(transfer));
        }

        Main.savingsBalance = s.getSavingsBalance();
        Main.savingsView = false;
    }
}
