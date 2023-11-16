package restaurant.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import restaurant.dao.EmployeeDAO;
import restaurant.dao.MenuItemDAO;
import restaurant.model.Employee;
import restaurant.model.MenuItem;

import java.util.List;


public class EmployeeManagementUI extends Notification {

    private TableView<Employee> table;

    public VBox createEmployeeManagementUI() {
        table = new TableView<>();
        setupTable();

        
        TextField nameInput = new TextField();
        nameInput.setPromptText("Name");
        TextField roleInput = new TextField();
        roleInput.setPromptText("Role");
        TextField contactInfoInput = new TextField();
        contactInfoInput.setPromptText("Contact");
        TextField hireDateInput = new TextField();
        hireDateInput.setPromptText("Date");
        

        
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addEmployee(nameInput.getText(), roleInput.getText(), contactInfoInput.getText(), hireDateInput.getText()));
        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> updateEmployee(nameInput.getText(), roleInput.getText(), contactInfoInput.getText(), hireDateInput.getText()));
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteSelectedEmployee());

        
        HBox inputLayout = new HBox(10);
        inputLayout.getChildren().addAll(nameInput, roleInput, contactInfoInput, hireDateInput, addButton, updateButton, deleteButton);

        
        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(table, inputLayout);

        return mainLayout;
    }

    private void deleteSelectedEmployee() {
        Employee selectedEmployee = table.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            
            EmployeeDAO employeeDAO = new EmployeeDAO();
            employeeDAO.deleteEmployee(selectedEmployee.getEmployeeId()); 

            
            refreshEmployeeTable();
        } else {
            
        }
    }

    private void setupTable() {
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        
        TableColumn<Employee, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Employee, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        TableColumn<Employee, String> contactColumn = new TableColumn<>("Contact Info");
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contactInfo"));
        TableColumn<Employee, String> hireDateColumn = new TableColumn<>("Hire Date");
        hireDateColumn.setCellValueFactory(new PropertyValueFactory<>("hireDate"));
        

        table.getColumns().addAll(nameColumn, roleColumn, contactColumn, hireDateColumn);
        
        refreshEmployeeTable();
    }

    private void addEmployee(String name, String role, String contactInfo, String hireDate) {
        
        
        if (name == null || name.trim().isEmpty()) {
            showAlert("Invalid Input", "Name cannot be empty.");
            return;
        }

        if (role == null || role.trim().isEmpty()) {
            showAlert("Invalid Input", "Role cannot be empty.");
        }

        
        Employee employee = new Employee(0, name, role, contactInfo, hireDate); 

        
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.insertEmployee(employee);

        
        showAlert("Success", "Employee added successfully.");

        
        refreshEmployeeTable();
    }

    private void updateEmployee(String name, String role, String contactInfo, String hireDate) {
        
        if (name == null || name.trim().isEmpty()) {
            showAlert("Invalid Input", "Name cannot be empty.");
            return;
        }

        if (role == null || role.trim().isEmpty()) {
            showAlert("Invalid Input", "Role cannot be empty.");
        }

        Employee selectedEmployee = table.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            
            selectedEmployee.setName(name);
            selectedEmployee.setRole(role);
            selectedEmployee.setContactInfo(contactInfo);
            selectedEmployee.setHireDate(hireDate);
            

            
            EmployeeDAO employeeDAO = new EmployeeDAO();
            employeeDAO.updateEmployee(selectedEmployee);

            
            refreshEmployeeTable();
        } else {
            
        }
    }

    private void refreshEmployeeTable() {
        
        EmployeeDAO employeeDAO = new EmployeeDAO();
        List<Employee> employees = employeeDAO.getAllEmployees(); 

        
        ObservableList<Employee> observableList = FXCollections.observableArrayList(employees);

        
        table.setItems(observableList);
    }

    
}
