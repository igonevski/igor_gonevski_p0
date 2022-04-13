package dev.gonevski.entities;

public class XYZCheckingAccount {

    private int checkingId;
    private int ownerId;
    private String checkingName;
    private double checkingBalance;

    //region CONSTRUCTORS
    public XYZCheckingAccount() {

    }

    public XYZCheckingAccount(int checkingId, int ownerId, String checkingName, double checkingBalance) {
        this.checkingId = checkingId;
        this.ownerId = ownerId;
        this.checkingName = checkingName;
        this.checkingBalance = checkingBalance;
    }
    //endregion

    //region GETTERS & SETTERS
    public int getCheckingId() {
        return checkingId;
    }

    public void setCheckingId(int checkingId) {
        this.checkingId = checkingId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getCheckingName() {
        return checkingName;
    }

    public void setCheckingName(String checkingName) {
        this.checkingName = checkingName;
    }

    public double getCheckingBalance() {
        return checkingBalance;
    }

    public void setCheckingBalance(double checkingBalance) {
        this.checkingBalance = checkingBalance;
    }
    //endregion

    @Override
    public String toString() {
        return "CheckingAccount{" +
                "checkingId=" + checkingId +
                ", ownerId='" + ownerId + '\'' +
                ", checkingName='" + checkingName + '\'' +
                ", checkingBalance=" + checkingBalance +
                '}';
    }

}

