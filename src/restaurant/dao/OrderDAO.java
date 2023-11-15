package restaurant.dao;

import restaurant.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    public void insertOrder(int customerId, int employeeId, String orderDate, double totalAmount, String status) {
        String sql = "INSERT INTO Orders(CustomerID, EmployeeID, OrderDate, TotalAmount, Status) VALUES(?,?,?,?,?)";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            pstmt.setInt(2, employeeId);
            pstmt.setString(3, orderDate);
            pstmt.setDouble(4, totalAmount);
            pstmt.setString(5, status);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertOrder(Order order) {
        String sql = "INSERT INTO Orders(CustomerID, EmployeeID, OrderDate, TotalAmount, Status) VALUES(?,?,?,?,?)";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, order.getCustomerId());
            pstmt.setInt(2, order.getEmployeeId());
            pstmt.setString(3, order.getOrderDate());
            pstmt.setDouble(4, order.getTotalAmount());
            pstmt.setString(5, order.getStatus());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders";

        try (Connection conn = DatabaseConnector.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Order order = new Order(rs.getInt("OrderID"), rs.getInt("CustomerID"),
                        rs.getInt("EmployeeID"), rs.getString("OrderDate"),
                        rs.getDouble("TotalAmount"), rs.getString("Status"));
                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orders;
    }

    public void updateOrder(int orderId, double totalAmount, String status) {
        String sql = "UPDATE Orders SET TotalAmount = ?, Status = ? WHERE OrderID = ?";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, totalAmount);
            pstmt.setString(2, status);
            pstmt.setInt(3, orderId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateOrder(Order order) {
        String sql = "UPDATE Orders SET TotalAmount = ?, Status = ? WHERE OrderID = ?";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, order.getTotalAmount());
            pstmt.setString(2, order.getStatus());
            pstmt.setInt(3, order.getOrderId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteOrder(int orderId) {
        String sql = "DELETE FROM Orders WHERE OrderID = ?";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
