package restaurant.dao;

import restaurant.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    // Insert Employee
    public void insertEmployee(Employee employee) {
        String sql = "INSERT INTO Employees (EmployeeID, Name, Role, ContactInfo, HireDate) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(2, employee.getName());
            pstmt.setString(3, employee.getRole());
            pstmt.setString(4, employee.getContactInfo());
            pstmt.setString(5, employee.getHireDate());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Employee getEmployeeById(int employeeId) {
        String sql = "SELECT * FROM Employees WHERE EmployeeID = ?";
        Employee employee = null;

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, employeeId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                employee = new Employee(
                        rs.getInt("EmployeeID"),
                        rs.getString("Name"),
                        rs.getString("Role"),
                        rs.getString("ContactInfo"),
                        rs.getString("HireDate"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employee;
    }

    // Get All Employees
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM Employees";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("EmployeeID"),
                        rs.getString("Name"),
                        rs.getString("Role"),
                        rs.getString("ContactInfo"),
                        rs.getString("HireDate"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employees;
    }

    // Update Employee
    public void updateEmployee(Employee employee) {
        String sql = "UPDATE Employees SET Name = ?, Role = ?, ContactInfo = ?, HireDate = ? WHERE EmployeeID = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getRole());
            pstmt.setString(3, employee.getContactInfo());
            pstmt.setString(4, employee.getHireDate());
            pstmt.setInt(5, employee.getEmployeeId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Delete Employee
    public void deleteEmployee(int employeeId) {
        String sql = "DELETE FROM Employees WHERE EmployeeID = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employeeId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}