package restaurant.service;

import restaurant.model.Employee;
import restaurant.dao.EmployeeDAO;
import java.util.List;

public class EmployeeService {
    private final EmployeeDAO employeeDAO;

    public EmployeeService() {
        this.employeeDAO = new EmployeeDAO();
    }

    public void addEmployee(Employee employee) {
        // Additional logic before adding an employee can be implemented here
        employeeDAO.insertEmployee(employee);
    }

    public void updateEmployee(Employee employee) {
        // Additional logic for updating an employee's details
        employeeDAO.updateEmployee(employee);
    }

    public List<Employee> getAllEmployees() {
        // Logic to retrieve all employees
        return employeeDAO.getAllEmployees();
    }

    public Employee getEmployeeById(int employeeId) {
        // Logic to retrieve a single employee by ID
        // This might involve calling a method in EmployeeDAO that needs to be implemented
        return employeeDAO.getEmployeeById(employeeId);
    }

    public void deleteEmployee(int employeeId) {
        // Logic before deleting an employee, if necessary
        employeeDAO.deleteEmployee(employeeId);
    }

    // Additional methods as required for your business logic
}
