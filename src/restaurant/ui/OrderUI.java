package restaurant.ui;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.geometry.Insets;
import restaurant.dao.MenuItemDAO;
import restaurant.model.Employee;
import restaurant.model.Order;
import restaurant.model.MenuItem;

import java.util.List;
import java.util.ArrayList;
// Other necessary imports

public class OrderUI {

    private TableView<MenuItem> menuItemsTable;
    private TableView<UIOrderItem> orderItemsTable; // Table to show items in the current order
    private ChoiceBox<String> categoryFilter; // Dropdown to filter items by category

    private List<UIOrderItem> currentOrderItems = new ArrayList<>();
    
    public VBox createOrderUI() {
        menuItemsTable = new TableView<>();
        orderItemsTable = new TableView<>();


        setupMenuItemsTable();
        setupOrderItemsTable();

        categoryFilter = new ChoiceBox<>();
        categoryFilter.getItems().addAll("Appetizers", "Entrées", "Side Dishes", "Desserts", "Beverages");
        // Populate categoryFilter with categories and add a listener to filter items
        categoryFilter.setOnAction(e -> updateMenuItems(categoryFilter.getValue()));

        TextField quantityInput = new TextField();
        quantityInput.setPromptText("Quantity");

        Button addToOrderButton = new Button("Add to order");
        addToOrderButton.setOnAction(e -> addItemToOrder(quantityInput.getText()));

        Button clearOrderButton = new Button("Clear order");
        clearOrderButton.setOnAction(e -> clearOrder());

        // Add functionality to addOrderButton

        HBox tablesLayout = new HBox(10);
        tablesLayout.getChildren().addAll(menuItemsTable, orderItemsTable);
        HBox.setHgrow(menuItemsTable, Priority.ALWAYS);
        HBox.setHgrow(orderItemsTable, Priority.ALWAYS);

        HBox controlsLayout = new HBox(10);
        controlsLayout.getChildren().addAll(categoryFilter, quantityInput, addToOrderButton, clearOrderButton);

        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(tablesLayout, controlsLayout);
        VBox.setMargin(controlsLayout, new Insets(10, 0, 0, 0)); // Add margin for better spacing

        mainLayout.setPadding(new Insets(10));
        return mainLayout;
    }

    private void clearOrder() {
        currentOrderItems = new ArrayList<>();
        refreshOrderItemsTable();
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

    private void addItemToOrder(String i) {
        MenuItem selectedItem = menuItemsTable.getSelectionModel().getSelectedItem();
        int quantity = Integer.parseInt(i); // Add error handling

        // Find if the item is already in the order
        UIOrderItem existingItem = currentOrderItems.stream()
                .filter(uiOrderItem -> uiOrderItem.getMenuItem().equals(selectedItem))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            // Update quantity if already in the order
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            // Add new item to the order
            currentOrderItems.add(new UIOrderItem(selectedItem, quantity));
        }

        refreshOrderItemsTable();
    }

    private void refreshOrderItemsTable() {
        ObservableList<UIOrderItem> items = FXCollections.observableArrayList(currentOrderItems);
        orderItemsTable.setItems(items);
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
        orderItemsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // Column for Item Name
        TableColumn<UIOrderItem, String> itemNameColumn = new TableColumn<>("Item Name");
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemName"));

        // Column for Price
        TableColumn<UIOrderItem, Double> itemPriceColumn = new TableColumn<>("Price");
        itemPriceColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getMenuItem().getPrice()));

        // Column for Quantity
        TableColumn<UIOrderItem, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());

        orderItemsTable.getColumns().addAll(itemNameColumn, itemPriceColumn, quantityColumn);
    }


    // Additional methods for adding items to the order, filtering menu items, etc.
}
