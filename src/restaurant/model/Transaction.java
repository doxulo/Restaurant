package restaurant.model;

public class Transaction {
    private int transactionId;
    private int orderId;
    private String paymentType;
    private double amount;
    private String transactionDate;

    
    public Transaction(int transactionId, int orderId, String paymentType, double amount, String transactionDate) {
        this.transactionId = transactionId;
        this.orderId = orderId;
        this.paymentType = paymentType;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    
    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", orderId=" + orderId +
                ", paymentType='" + paymentType + '\'' +
                ", amount=" + amount +
                ", transactionDate='" + transactionDate + '\'' +
                '}';
    }
}
