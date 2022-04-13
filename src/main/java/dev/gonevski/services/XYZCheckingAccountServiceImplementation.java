package dev.gonevski.services;

import dev.gonevski.api.Main;

import dev.gonevski.data.DAOXYZCheckingAccounts;
import dev.gonevski.data.DAOXYZCheckingAccountsImplementation;
import dev.gonevski.data.DAOXYZTransactionsImplementation;
import dev.gonevski.entities.XYZCheckingAccount;
import dev.gonevski.data.DAOXYZTransactions;
import dev.gonevski.entities.XYZTransaction;
import dev.gonevski.utilities.XYZLinkedList;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class XYZCheckingAccountServiceImplementation implements XYZCheckingAccountService {

    // Dependency Injection - Building an object that uses another object within it.
    static DAOXYZCheckingAccounts checkDAO = new DAOXYZCheckingAccountsImplementation();

    // Dependency Injection. Building an object that uses another object within it
    public XYZCheckingAccountServiceImplementation(DAOXYZCheckingAccounts checkDAO) {
        this.checkDAO = checkDAO;
    }

    // Dependency Injection - Building an object that uses another object within it.
    static DAOXYZTransactions transDAO = new DAOXYZTransactionsImplementation();

    // Dependency Injection. Building an object that uses another object within it
    public void XYZTransactionServiceImplementation(DAOXYZTransactions transDAO) {
        this.transDAO = transDAO;
    }


    @Override
    public void withdraw(XYZCheckingAccount c, double balance) {
        Scanner input = new Scanner(System.in);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        int option;
        Main.checkingView = true;
        double withdrawal = 0;
        boolean validWithdrawal = false;
        boolean cancelWithdrawal = false;

        System.out.println("Enter an amount to withdraw in the following format: ##.##");
        while (!validWithdrawal && !cancelWithdrawal) {
            try {
                withdrawal = input.nextDouble();

                while (withdrawal > balance || withdrawal <= 0) {
                    System.out.println("ERROR: Invalid withdrawal amount.");
                    option = Main.accountActionErrorMenu();

                    if (option == 1) {
                        System.out.println("Enter a new amount.");
                        withdrawal = input.nextDouble();
                    } else if (option == 2) {
                        cancelWithdrawal = true;
                        withdrawal = 0;
                        break;
                    }
                }

                if (withdrawal <= balance && withdrawal != 0) {
                    validWithdrawal = true;
                }

            } catch (InputMismatchException e) {
                System.out.println("ERROR: Invalid input. Enter an amount to withdraw" +
                        "in the following format: ##.##");
                input.next();
            }
        }
        c.setCheckingBalance(balance - withdrawal);
        this.checkDAO.updateChecking(c);

        if (validWithdrawal) {
            long currentTime = System.currentTimeMillis();
            XYZTransaction transaction = new XYZTransaction(0, Main.loggedInUserId, "Withdrawal", "Checking", "N/A", withdrawal, currentTime);
            this.transDAO.addTransaction(transaction);
            System.out.println("Withdrawal success: " + formatter.format(withdrawal));
        }

        Main.checkingBalance = c.getCheckingBalance();
        Main.checkingView = false;
    }

    @Override
    public void deposit(XYZCheckingAccount c, double balance) {
        Scanner input = new Scanner(System.in);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        int option;
        Main.checkingView = true;
        double deposit = 0;
        boolean validDeposit = false;
        boolean cancelDeposit = false;

        System.out.println("Enter an amount to deposit in the following format: ##.##");
        while (!validDeposit && !cancelDeposit) {
            try {
                deposit = input.nextDouble();

                while (deposit <= 0) {
                    System.out.println("ERROR: Cannot deposit a negative or null amount.");
                    option = Main.accountActionErrorMenu();

                    if (option == 1) {
                        System.out.println("Enter a new amount.");
                        deposit = input.nextDouble();
                    } else if (option == 2) {
                        cancelDeposit = true;
                        deposit = 0;
                        break;
                    }
                }

                while (deposit > 10000) {
                    System.out.println("ERROR: Cannot deposit more than $10,000 at once.");
                    option = Main.accountActionErrorMenu();

                    if (option == 1) {
                        System.out.println("Enter a new amount.");
                        deposit = input.nextDouble();
                    } else if (option == 2) {
                        cancelDeposit = true;
                        deposit = 0;
                        break;
                    }
                }

                if (deposit > 0 && deposit <= 10000) {
                    validDeposit = true;
                }

            } catch (InputMismatchException e) {
                System.out.println("ERROR: Invalid input. Enter an amount to deposit" +
                        "in the following format: ##.##");
                input.next();
            }
        }
        c.setCheckingBalance(balance + deposit);
        this.checkDAO.updateChecking(c);

        if (validDeposit) {
            long currentTime = System.currentTimeMillis();
            XYZTransaction transaction = new XYZTransaction(0, Main.loggedInUserId, "Deposit", "N/A", "Checking", deposit, currentTime);
            this.transDAO.addTransaction(transaction);
            System.out.println("Deposit success: " + formatter.format(deposit));
        }

        Main.checkingBalance = c.getCheckingBalance();
        Main.checkingView = false;
    }

    @Override
    public void viewHistory(XYZCheckingAccount c) {
        Main.checkingView = true;
        System.out.println("===================" +
                "\nTRANSACTION HISTORY" +
                "\n===================");
        XYZLinkedList<XYZTransaction> cTransactions = this.transDAO.getCheckingTransactions(Main.loggedInUserId);

        if (cTransactions.getSize() == 0) {
            System.out.println("ERROR: You have no transactions for this account.");
        } else {
            System.out.println(cTransactions);
        }
        Main.checkingView = false;
    }

    @Override
    public void createChecking() {
        Scanner input = new Scanner(System.in);
        XYZCheckingAccount cAccount = this.checkDAO.getCheckingByOwner(Main.loggedInUserId);
        Main.creationView = true;

//        if (cAccount.getOwnerId() != 0) {
//            System.out.println("ERROR: You already have a Checking account!");
//            Main.creationView = false;
//            return;
//        }

        System.out.println("Enter a friendly name for this account:");
        String newCheckingName = input.nextLine();
        System.out.println("Enter how much you would like to have starting out in this account:");
        String newCheckingBalance = input.nextLine();
        double newCheckingBalanceAmount = Double.parseDouble(newCheckingBalance);


        XYZCheckingAccount newChecking = new XYZCheckingAccount(0, Main.loggedInUserId, newCheckingName, newCheckingBalanceAmount);
        this.checkDAO.addChecking(newChecking);
        System.out.println("Account '" + newCheckingName + "' created!");
        Main.accountsView = false;
        Main.creationView = false;
    }

}
