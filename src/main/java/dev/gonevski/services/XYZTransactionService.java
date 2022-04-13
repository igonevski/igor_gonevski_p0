package dev.gonevski.services;

import dev.gonevski.entities.XYZCheckingAccount;
import dev.gonevski.entities.XYZSavingsAccount;

public interface XYZTransactionService {
    public void transfer(XYZCheckingAccount c, XYZSavingsAccount s, double cBalance, double sBalance);
    public void transfer(XYZSavingsAccount s, XYZCheckingAccount c, double sBalance, double cBalance);
}
