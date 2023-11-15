package restaurant.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.cell.PropertyValueFactory;
import restaurant.dao.MenuItemDAO;
import restaurant.model.MenuItem;

import java.util.List;
// Other necessary imports

public class MenuManagementUI {

    private TableView<MenuItem> table;

    public VBox createMenuManagementUI() {
        table = new TableView<>();
        setupTable();

        // Input fields
        TextField nameInput = new TextField();
        nameInput.setPromptText("Name");
        TextField priceInput = new TextField();
        priceInput.setPromptText("Price");
        // Add other input fields as needed

        // Buttons
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addMenuItem(nameInput.getText(), priceInput.getText()));
        Button updateButton = new Button("Update"); // Add functionality for update
        Button deleteButton = new Button("Delete"); // Add functionality for delete

        // Layout for input fields and buttons
        HBox inputLayout = new HBox(10);
        inputLayout.getChildren().addAll(nameInput, priceInput, addButton, updateButton, deleteButton);

        // Main layout
        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(table, inputLayout);

        return mainLayout;
    }

    private void setupTable() {
        // Define table columns
        TableColumn<MenuItem, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<MenuItem, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        // Add other columns as needed

        table.getColumns().addAll(nameColumn, priceColumn);
        // Optionally, add data to the table here if needed
        refreshMenuItemsTable();

    }
    private void addMenuItem(String name, String priceStr) {
        // Validate inputs
        if (name == null || name.trim().isEmpty()) {
            showAlert("Invalid Input", "Name cannot be empty.");
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceStr);
            if (price < 0) {
                showAlert("Invalid Input", "Price cannot be negative.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Price must be a number.");
            return;
        }

        // Create a new MenuItem object
        MenuItem menuItem = new MenuItem(0, name, "", price, ""); // Assuming other fields like description and category

        // Add the menu item to the database using DAO
        MenuItemDAO menuItemDAO = new MenuItemDAO();
        menuItemDAO.insertMenuItem(menuItem);

        // Show success message
        showAlert("Success", "Menu item added successfully.");

        // Optionally, update your UI here (e.g., refresh the table to show the new item)
        refreshMenuItemsTable();
    }

    private void showAlert(String title, String message) {
        // Implementation to show an alert dialog.
        // This can vary based on whether you're using JavaFX, Swing, etc.
    }

    private void refreshMenuItemsTable() {
        // Fetch the updated list of menu items from the database
        MenuItemDAO menuItemDAO = new MenuItemDAO();
        List<MenuItem> menuItems = menuItemDAO.getAllMenuItems(); // Method to fetch all menu items

        // Convert the list to an observable list for use with TableView
        ObservableList<MenuItem> observableList = FXCollections.observableArrayList(menuItems);

        // Update the TableView
        table.setItems(observableList);
    }

    // ... other methods ...
}
