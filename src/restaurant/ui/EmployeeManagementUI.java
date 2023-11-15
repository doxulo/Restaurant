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
// Other necessary imports

public class EmployeeManagementUI extends Notification {

    private TableView<Employee> table;

    public VBox createEmployeeManagementUI() {
        table = new TableView<>();
        setupTable();

        // Input fields for employee information
        TextField nameInput = new TextField();
        nameInput.setPromptText("Name");
        TextField roleInput = new TextField();
        roleInput.setPromptText("Role");
        TextField contactInfoInput = new TextField();
        contactInfoInput.setPromptText("Contact");
        TextField hireDateInput = new TextField();
        hireDateInput.setPromptText("Date");
        // Add other fields as needed

        // Buttons for Add, Update, Delete
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addEmployee(nameInput.getText(), roleInput.getText(), contactInfoInput.getText(), hireDateInput.getText()));
        Button updateButton = new Button("Update"); // Implement update functionality
        Button deleteButton = new Button("Delete"); // Implement delete functionality

        // Layout for input fields and buttons
        HBox inputLayout = new HBox(10);
        inputLayout.getChildren().addAll(nameInput, roleInput, contactInfoInput, hireDateInput, addButton, updateButton, deleteButton);

        // Main layout
        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(table, inputLayout);

        return mainLayout;
    }

    private void setupTable() {
        // Define table columns for Employee properties
        TableColumn<Employee, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Employee, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        TableColumn<Employee, String> contactColumn = new TableColumn<>("Contact Info");
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contactInfo"));
        TableColumn<Employee, String> hireDateColumn = new TableColumn<>("Hire Date");
        hireDateColumn.setCellValueFactory(new PropertyValueFactory<>("hireDate"));
        // Add other columns as necessary

        table.getColumns().addAll(nameColumn, roleColumn, contactColumn, hireDateColumn);
        // Optionally, add data to the table here
        refreshEmployeeTable();
    }

    private void addEmployee(String name, String role, String contactInfo, String hireDate) {
        // Implement logic to add an employee
        // Validate inputs
        if (name == null || name.trim().isEmpty()) {
            showAlert("Invalid Input", "Name cannot be empty.");
            return;
        }

        if (role == null || role.trim().isEmpty()) {
            showAlert("Invalid Input", "Role cannot be empty.");
        }

        // Create a new MenuItem object
        Employee employee = new Employee(0, name, role, contactInfo, hireDate); // Assuming other fields like description and category

        // Add the menu item to the database using DAO
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.insertEmployee(employee);

        // Show success message
        showAlert("Success", "Employee added successfully.");

        // Optionally, update your UI here (e.g., refresh the table to show the new item)
        refreshEmployeeTable();
    }

    private void refreshEmployeeTable() {
        // Fetch the updated list of menu items from the database
        EmployeeDAO employeeDAO = new EmployeeDAO();
        List<Employee> employees = employeeDAO.getAllEmployees(); // Method to fetch all menu items

        // Convert the list to an observable list for use with TableView
        ObservableList<Employee> observableList = FXCollections.observableArrayList(employees);

        // Update the TableView
        table.setItems(observableList);
    }

    // Methods for updating and deleting employees
}
