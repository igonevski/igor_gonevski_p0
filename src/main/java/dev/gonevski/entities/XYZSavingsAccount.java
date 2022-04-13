package dev.gonevski.entities;

public class XYZSavingsAccount {

    private int savingsId;
    private int ownerId;
    private String savingsName;
    private double savingsBalance;

    //region CONSTRUCTORS
    public XYZSavingsAccount() {

    }

    public XYZSavingsAccount(int savingsId, int ownerId, String savingsName, double savingsBalance) {
        this.savingsId = savingsId;
        this.ownerId = ownerId;
        this.savingsName = savingsName;
        this.savingsBalance = savingsBalance;
    }
    //endregion

    //region GETTERS & SETTERS
    public int getSavingsId() {
        return savingsId;
    }

    public void setSavingsId(int savingsId) {
        this.savingsId = savingsId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getSavingsName() {
        return savingsName;
    }

    public void setSavingsName(String savingsName) {
        this.savingsName = savingsName;
    }

    public double getSavingsBalance() {
        return savingsBalance;
    }

    public void setSavingsBalance(double savingsBalance) {
        this.savingsBalance = savingsBalance;
    }
    //endregion

    @Override
    public String toString() {
        return "SavingsAccount{" +
                "savingsId=" + savingsId +
                ", ownerId='" + ownerId + '\'' +
                ", savingsName='" + savingsName + '\'' +
                ", savingsBalance=" + savingsBalance +
                '}';
    }

}
