package dev.gonevski.services;

import dev.gonevski.entities.XYZCheckingAccount;

public interface XYZCheckingAccountService {
    public void withdraw(XYZCheckingAccount c, double balance);
    public void deposit(XYZCheckingAccount c, double balance);
    public void viewHistory(XYZCheckingAccount c);
    public void createChecking();
}
