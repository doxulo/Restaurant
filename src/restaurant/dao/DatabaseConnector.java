package restaurant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    public static Connection connect() {
        Connection conn = null;
        try {
            // dbPath is the relative path to the SQLite database
            String dbPath = "database/Restaurant.db";
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
