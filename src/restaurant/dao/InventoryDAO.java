package restaurant.dao;

import restaurant.model.Inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {

    // Insert Inventory Item
    public void insertInventoryItem(Inventory item) {
        String sql = "INSERT INTO Inventory (InventoryID, ItemName, Quantity, ReorderLevel) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, item.getInventoryId());
            pstmt.setString(2, item.getItemName());
            pstmt.setInt(3, item.getQuantity());
            pstmt.setInt(4, item.getReorderLevel());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Get All Inventory Items
    public List<Inventory> getAllInventoryItems() {
        List<Inventory> inventoryList = new ArrayList<>();
        String sql = "SELECT * FROM Inventory";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Inventory item = new Inventory(
                        rs.getInt("InventoryID"),
                        rs.getString("ItemName"),
                        rs.getInt("Quantity"),
                        rs.getInt("ReorderLevel"));
                inventoryList.add(item);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return inventoryList;
    }

    // Update Inventory Item
    public void updateInventoryItem(Inventory item) {
        String sql = "UPDATE Inventory SET ItemName = ?, Quantity = ?, ReorderLevel = ? WHERE InventoryID = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, item.getItemName());
            pstmt.setInt(2, item.getQuantity());
            pstmt.setInt(3, item.getReorderLevel());
            pstmt.setInt(4, item.getInventoryId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Delete Inventory Item
    public void deleteInventoryItem(int inventoryId) {
        String sql = "DELETE FROM Inventory WHERE InventoryID = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, inventoryId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
