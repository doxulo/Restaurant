package restaurant.model;

public class Employee {
    private int employeeId;
    private String name;
    private String role;
    private String contactInfo;
    private String hireDate;

    // Constructor
    public Employee(int employeeId, String name, String role, String contactInfo, String hireDate) {
        this.employeeId = employeeId;
        this.name = name;
        this.role = role;
        this.contactInfo = contactInfo;
        this.hireDate = hireDate;
    }

    // Getters
    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getHireDate() {
        return hireDate;
    }

    // Setters
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    // toString method for easy printing of Employee details
    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", hireDate='" + hireDate + '\'' +
                '}';
    }
}
