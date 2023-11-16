package restaurant.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.geometry.Insets;
import restaurant.dao.MenuItemDAO;
import restaurant.model.Order;
import restaurant.model.MenuItem;

import java.util.List;
// Other necessary imports

public class OrderUI {

    private TableView<MenuItem> menuItemsTable;
    private TableView<MenuItem> orderItemsTable; // Table to show items in the current order
    private ChoiceBox<String> categoryFilter; // Dropdown to filter items by category

    public VBox createOrderUI() {
        menuItemsTable = new TableView<>();

        orderItemsTable = new TableView<>();


        setupMenuItemsTable();
        setupOrderItemsTable();

        categoryFilter = new ChoiceBox<>();
        categoryFilter.getItems().addAll(new String[]{"Appetizers", "Entrées", "Side Dishes", "Desserts", "Beverages"});
        // Populate categoryFilter with categories and add a listener to filter items
        categoryFilter.setOnAction(e -> updateMenuItems(categoryFilter.getValue()));

        TextField quantityInput = new TextField();
        quantityInput.setPromptText("Quantity");

        Button addOrderButton = new Button("Add Order");
        addOrderButton.setOnAction(e -> addItemToOrder(Integer.parseInt(quantityInput.getText())));

        // Add functionality to addOrderButton

        HBox tablesLayout = new HBox(10);
        tablesLayout.getChildren().addAll(menuItemsTable, orderItemsTable);
        HBox.setHgrow(menuItemsTable, Priority.ALWAYS);
        HBox.setHgrow(orderItemsTable, Priority.ALWAYS);

        HBox controlsLayout = new HBox(10);
        controlsLayout.getChildren().addAll(categoryFilter, quantityInput, addOrderButton);

        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(tablesLayout, controlsLayout);
        VBox.setMargin(controlsLayout, new Insets(10, 0, 0, 0)); // Add margin for better spacing

        return mainLayout;
    }

    private void updateMenuItems(String category) {
        // Fetch the updated list of menu items from the database
        MenuItemDAO menuItemDAO = new MenuItemDAO();
        List<MenuItem> menuItems = menuItemDAO.getMenuItemByCategory(category); // Method to fetch all menu items

        // Convert the list to an observable list for use with TableView
        ObservableList<MenuItem> observableList = FXCollections.observableArrayList(menuItems);

        // Update the TableView
        menuItemsTable.setItems(observableList);
    }

    private void addItemToOrder(int i) {

    }

    private void setupMenuItemsTable() {
        menuItemsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Configure columns for menuItemsTable
        TableColumn<MenuItem, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<MenuItem, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<MenuItem, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<MenuItem, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        // Add other columns as needed

        menuItemsTable.getColumns().addAll(nameColumn, descriptionColumn, priceColumn, categoryColumn);
        // Optionally, add data to the table here if needed

        refreshMenuItemsTable();
    }

    private void refreshMenuItemsTable() {
        // Fetch the updated list of menu items from the database
        MenuItemDAO menuItemDAO = new MenuItemDAO();
        List<MenuItem> menuItems = menuItemDAO.getAllMenuItems(); // Method to fetch all menu items

        // Convert the list to an observable list for use with TableView
        ObservableList<MenuItem> observableList = FXCollections.observableArrayList(menuItems);

        // Update the TableView
        menuItemsTable.setItems(observableList);
    }

    private void setupOrderItemsTable() {
        // Configure columns for orderItemsTable
    }

    // Additional methods for adding items to the order, filtering menu items, etc.
}
