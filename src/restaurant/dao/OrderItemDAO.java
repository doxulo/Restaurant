package restaurant.dao;

import restaurant.model.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAO {

    
    public void insertOrderItem(OrderItem orderItem) {
        String sql = "INSERT INTO OrderItems (OrderItemID, OrderID, ItemID, Quantity) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderItem.getOrderItemId());
            pstmt.setInt(2, orderItem.getOrderId());
            pstmt.setInt(3, orderItem.getItemId());
            pstmt.setInt(4, orderItem.getQuantity());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        List<OrderItem> orderItems = new ArrayList<>();
        String sql = "SELECT * FROM OrderItems WHERE OrderID = ?";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                OrderItem orderItem = new OrderItem(
                        rs.getInt("OrderItemID"),
                        orderId,
                        rs.getInt("ItemID"),
                        rs.getInt("Quantity"));
                orderItems.add(orderItem);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orderItems;
    }

    
    public List<OrderItem> getAllOrderItems() {
        List<OrderItem> orderItems = new ArrayList<>();
        String sql = "SELECT * FROM OrderItems";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                OrderItem orderItem = new OrderItem(
                        rs.getInt("OrderItemID"),
                        rs.getInt("OrderID"),
                        rs.getInt("ItemID"),
                        rs.getInt("Quantity"));
                orderItems.add(orderItem);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orderItems;
    }
    

    public void updateOrderItem(OrderItem orderItem) {
        String sql = "UPDATE OrderItems SET OrderID = ?, ItemID = ?, Quantity = ? WHERE OrderItemID = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderItem.getOrderId());
            pstmt.setInt(2, orderItem.getItemId());
            pstmt.setInt(3, orderItem.getQuantity());
            pstmt.setInt(4, orderItem.getOrderItemId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    

    public void deleteOrderItem(int orderItemId) {
        String sql = "DELETE FROM OrderItems WHERE OrderItemID = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderItemId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
