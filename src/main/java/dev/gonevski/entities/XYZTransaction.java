package dev.gonevski.entities;

import java.text.NumberFormat;

public class XYZTransaction {

        private int transactionId;
        private int ownerId;
        private String type;
        private String fromAccount;
        private String toAccount;
        private double amount;
        private long timestamp;
        static NumberFormat formatter = NumberFormat.getCurrencyInstance();

        public XYZTransaction() {

        }

        public XYZTransaction(int transactionId, int ownerId, String type, String fromAccount, String toAccount, double amount, long timestamp) {
            this.transactionId = transactionId;
            this.ownerId = ownerId;
            this.type = type;
            this.fromAccount = fromAccount;
            this.toAccount = toAccount;
            this.amount = amount;
            this.timestamp = timestamp;
        }

        public int getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(int transactionId) {
            this.transactionId = transactionId;
        }

        public int getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(int ownerId) {
            this.ownerId = ownerId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getFromAccount() {
            return fromAccount;
        }

        public void setFromAccount(String fromAccount) {
            this.fromAccount = fromAccount;
        }

        public String getToAccount() {
            return toAccount;
        }

        public void setToAccount(String toAccount) {
            this.toAccount = toAccount;
        }

        public double getAmount() {  return amount;  }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return  "Transaction{" +
                    "transactionId=" + transactionId +
                    ", ownerID='" + ownerId + '\'' +
                    ", type='" + type + '\'' +
                    ", toAccount='" + toAccount + '\'' +
                    ", fromAccount='" + fromAccount + '\'' +
                    ", amount='" + formatter.format(amount) + '\'' +
                    ", timestamp='" + timestamp +
                    '}';
        }

}
