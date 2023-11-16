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
        
        employeeDAO.insertEmployee(employee);
    }

    public void updateEmployee(Employee employee) {
        
        employeeDAO.updateEmployee(employee);
    }

    public List<Employee> getAllEmployees() {
        
        return employeeDAO.getAllEmployees();
    }

    public Employee getEmployeeById(int employeeId) {
        
        
        return employeeDAO.getEmployeeById(employeeId);
    }

    public void deleteEmployee(int employeeId) {
        
        employeeDAO.deleteEmployee(employeeId);
    }

    
}
