package restaurant.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import restaurant.dao.MenuItemDAO;
import restaurant.model.MenuItem;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Other necessary imports

public class MenuManagementUI extends Notification{

    private static final Logger logger = LoggerFactory.getLogger(MenuManagementUI.class);
    private TableView<MenuItem> table;

    public VBox createMenuManagementUI() {
        table = new TableView<>();
        setupTable();

        // Input fields
        TextField nameInput = new TextField();
        nameInput.setPromptText("Name");

        TextField descriptionInput = new TextField();
        descriptionInput.setPromptText("Description");

        TextField priceInput = new TextField();
        priceInput.setPromptText("Price");

        ChoiceBox categoryInput = new ChoiceBox();
        categoryInput.getItems().addAll(new String[]{"Appetizers", "Entrées", "Side Dishes", "Desserts", "Beverages"});

        // Add other input fields as needed
        Text text1 = new Text();
        text1.setText("Category: ");

        // Buttons
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addMenuItem(nameInput.getText(), descriptionInput.getText(), priceInput.getText(), (String) categoryInput.getValue()));

        Button updateButton = new Button("Update"); // Add functionality for update
        updateButton.setOnAction(e -> updateSelectedItem(nameInput.getText(), priceInput.getText()));
        
        Button deleteButton = new Button("Delete"); // Add functionality for delete
        deleteButton.setOnAction(e -> deleteSelectedItem());

        // Layout for input fields and buttons
        HBox inputLayout = new HBox(10);
        inputLayout.getChildren().addAll(nameInput, descriptionInput, priceInput, text1, categoryInput, addButton, updateButton, deleteButton);

        // Main layout
        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(table, inputLayout);

        return mainLayout;
    }

    private void updateSelectedItem(String name, String priceStr) {
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

        MenuItem selectedItem = table.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Assuming nameInput and priceInput are the input fields
            selectedItem.setName(name);
            selectedItem.setPrice(Double.parseDouble(priceStr));
            // Update other fields as necessary

            // Save the updated item to the database
            MenuItemDAO menuItemDAO = new MenuItemDAO();
            menuItemDAO.updateMenuItem(selectedItem);

            // Refresh the table
            refreshMenuItemsTable();
        } else {
            // Handle case where no item is selected (e.g., show an alert)
        }
    }


    private void deleteSelectedItem() {
        MenuItem selectedItem = table.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Delete the selected item from the database
            MenuItemDAO menuItemDAO = new MenuItemDAO();
            menuItemDAO.deleteMenuItem(selectedItem.getItemId()); // Assuming MenuItem has an 'getId' method

            // Update the UI
            refreshMenuItemsTable();
        } else {
            // Handle case where no item is selected (e.g., show an alert)
        }
    }

    private void setupTable() {
        // Define table columns
        TableColumn<MenuItem, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<MenuItem, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<MenuItem, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<MenuItem, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        // Add other columns as needed

        table.getColumns().addAll(nameColumn, descriptionColumn, priceColumn, categoryColumn);
        // Optionally, add data to the table here if needed

        refreshMenuItemsTable();

    }
    private void addMenuItem(String name, String description, String priceStr, String category) {
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

        if (category == null || category.trim().isEmpty()) {
            showAlert("Invalid Input", "Category cannot be empty.");
            return;
        }
        // Create a new MenuItem object
        MenuItem menuItem = new MenuItem(0, name, description, price, category);

        // Add the menu item to the database using DAO
        MenuItemDAO menuItemDAO = new MenuItemDAO();
        menuItemDAO.insertMenuItem(menuItem);

        // Show success message
        showAlert("Success", "Menu item added successfully.");

        // Optionally, update your UI here (e.g., refresh the table to show the new item)
        refreshMenuItemsTable();
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
