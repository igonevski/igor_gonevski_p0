package dev.gonevski.services;

import dev.gonevski.entities.XYZSavingsAccount;

public interface XYZSavingsAccountService {
    public void withdraw(XYZSavingsAccount s, double balance);
    public void deposit(XYZSavingsAccount s, double balance);
    public void viewHistory(XYZSavingsAccount s);
    public void createSavings();
}
