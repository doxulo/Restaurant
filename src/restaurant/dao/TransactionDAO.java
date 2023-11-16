package restaurant.dao;

import restaurant.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    
    public void insertTransaction(Transaction transaction) {
        String sql = "INSERT INTO Transactions (TransactionID, OrderID, PaymentType, Amount, TransactionDate) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, transaction.getTransactionId());
            pstmt.setInt(2, transaction.getOrderId());
            pstmt.setString(3, transaction.getPaymentType());
            pstmt.setDouble(4, transaction.getAmount());
            pstmt.setString(5, transaction.getTransactionDate());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM Transactions";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Transaction transaction = new Transaction(
                        rs.getInt("TransactionID"),
                        rs.getInt("OrderID"),
                        rs.getString("PaymentType"),
                        rs.getDouble("Amount"),
                        rs.getString("TransactionDate"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return transactions;
    }

    
    public void updateTransaction(Transaction transaction) {
        String sql = "UPDATE Transactions SET OrderID = ?, PaymentType = ?, Amount = ?, TransactionDate = ? WHERE TransactionID = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, transaction.getOrderId());
            pstmt.setString(2, transaction.getPaymentType());
            pstmt.setDouble(3, transaction.getAmount());
            pstmt.setString(4, transaction.getTransactionDate());
            pstmt.setInt(5, transaction.getTransactionId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    
    public void deleteTransaction(int transactionId) {
        String sql = "DELETE FROM Transactions WHERE TransactionID = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, transactionId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
