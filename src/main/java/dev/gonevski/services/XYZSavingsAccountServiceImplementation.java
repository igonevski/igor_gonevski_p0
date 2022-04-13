package dev.gonevski.services;

import dev.gonevski.api.Main;

import dev.gonevski.data.DAOXYZSavingsAccounts;
import dev.gonevski.data.DAOXYZSavingsAccountsImplementation;
import dev.gonevski.data.DAOXYZTransactionsImplementation;
import dev.gonevski.entities.XYZSavingsAccount;
import dev.gonevski.data.DAOXYZTransactions;
import dev.gonevski.entities.XYZTransaction;
import dev.gonevski.utilities.XYZLinkedList;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class XYZSavingsAccountServiceImplementation implements XYZSavingsAccountService {
    // Dependency Injection - Building an object that uses another object within it.
    static DAOXYZSavingsAccounts saveDAO = new DAOXYZSavingsAccountsImplementation();

    // Dependency Injection. Building an object that uses another object within it
    public XYZSavingsAccountServiceImplementation(DAOXYZSavingsAccounts saveDAO) {
        this.saveDAO = saveDAO;
    }

    // Dependency Injection - Building an object that uses another object within it.
    static DAOXYZTransactions transDAO = new DAOXYZTransactionsImplementation();

    // Dependency Injection. Building an object that uses another object within it
    public void XYZTransactionServiceImplementation(DAOXYZTransactions transDAO) {
        this.transDAO = transDAO;
    }

    @Override
    public void withdraw(XYZSavingsAccount s, double balance) {
        Scanner input = new Scanner(System.in);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        int option;
        Main.savingsView = true;
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
        s.setSavingsBalance(balance - withdrawal);
        this.saveDAO.updateSavings(s);

        if (validWithdrawal) {
            long currentTime = System.currentTimeMillis();
            XYZTransaction transaction = new XYZTransaction(0, Main.loggedInUserId, "Withdrawal", "Savings", "N/A", withdrawal, currentTime);
            this.transDAO.addTransaction(transaction);
            System.out.println("Withdrawal success: " + formatter.format(withdrawal));
        }

        Main.savingsBalance = s.getSavingsBalance();
        Main.savingsView = false;
    }

    @Override
    public void deposit(XYZSavingsAccount s, double balance) {
        Scanner input = new Scanner(System.in);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        int option;
        Main.savingsView = true;
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
        s.setSavingsBalance(balance + deposit);
        this.saveDAO.updateSavings(s);

        if (validDeposit) {
            long currentTime = System.currentTimeMillis();
            XYZTransaction transaction = new XYZTransaction(0, Main.loggedInUserId, "Deposit", "N/A", "Savings", deposit, currentTime);
            this.transDAO.addTransaction(transaction);
            System.out.println("Deposit success: " + formatter.format(deposit));
        }

        Main.savingsBalance = s.getSavingsBalance();
        Main.savingsView = false;
    }

    @Override
    public void viewHistory(XYZSavingsAccount s) {
        Main.savingsView = true;
        System.out.println("===================" +
                "\nTRANSACTION HISTORY" +
                "\n===================");
        XYZLinkedList<XYZTransaction> sTransactions = this.transDAO.getSavingsTransactions(Main.loggedInUserId);

        if (sTransactions.getSize() == 0) {
            System.out.println("ERROR: You have no transactions for this account.");
        } else {
            System.out.println(sTransactions);
        }
        Main.savingsView = false;
    }

    @Override
    public void createSavings() {
        Scanner input = new Scanner(System.in);
        XYZSavingsAccount sAccount = this.saveDAO.getSavingsByOwner(Main.loggedInUserId);
        Main.creationView = true;

//        if (sAccount.getSavingsId() != 0) {
//            System.out.println("ERROR: You already have a Savings account!");
//            Main.creationView = false;
//            return;
//        }

        System.out.println("Enter a friendly name for this account:");
        String newSavingsName = input.nextLine();
        System.out.println("Enter how much you would like to have starting out in this account:");
        String newSavingsBalance = input.nextLine();
        double newSavingsBalanceAmount = Double.parseDouble(newSavingsBalance);

        XYZSavingsAccount newSavings = new XYZSavingsAccount(0, Main.loggedInUserId, newSavingsName, newSavingsBalanceAmount);
        this.saveDAO.addSavings(newSavings);
        System.out.println("Account '" + newSavingsName + "' created!");
        Main.accountsView = false;
        Main.creationView = false;
    }
}
