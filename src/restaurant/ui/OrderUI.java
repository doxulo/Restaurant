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


public class OrderUI {

    private TableView<MenuItem> menuItemsTable;
    private TableView<UIOrderItem> orderItemsTable; 
    private ChoiceBox<String> categoryFilter; 

    private List<UIOrderItem> currentOrderItems = new ArrayList<>();
    
    public VBox createOrderUI() {
        menuItemsTable = new TableView<>();
        orderItemsTable = new TableView<>();


        setupMenuItemsTable();
        setupOrderItemsTable();

        categoryFilter = new ChoiceBox<>();
        categoryFilter.getItems().addAll("Appetizers", "Entrées", "Side Dishes", "Desserts", "Beverages");
        
        categoryFilter.setOnAction(e -> updateMenuItems(categoryFilter.getValue()));

        TextField quantityInput = new TextField();
        quantityInput.setPromptText("Quantity");

        Button addToOrderButton = new Button("Add to order");
        addToOrderButton.setOnAction(e -> addItemToOrder(quantityInput.getText()));

        Button clearOrderButton = new Button("Clear order");
        clearOrderButton.setOnAction(e -> clearOrder());

        

        HBox tablesLayout = new HBox(10);
        tablesLayout.getChildren().addAll(menuItemsTable, orderItemsTable);
        HBox.setHgrow(menuItemsTable, Priority.ALWAYS);
        HBox.setHgrow(orderItemsTable, Priority.ALWAYS);

        tablesLayout.setPadding(new Insets(10));

        HBox controlsLayout = new HBox(10);
        controlsLayout.getChildren().addAll(categoryFilter, quantityInput, addToOrderButton, clearOrderButton);

        controlsLayout.setPadding(new Insets(0, 10, 0, 10));

        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(tablesLayout, controlsLayout);
        VBox.setMargin(controlsLayout, new Insets(10, 0, 0, 0)); 

        return mainLayout;
    }

    private void clearOrder() {
        currentOrderItems = new ArrayList<>();
        refreshOrderItemsTable();
    }

    private void updateMenuItems(String category) {
        
        MenuItemDAO menuItemDAO = new MenuItemDAO();
        List<MenuItem> menuItems = menuItemDAO.getMenuItemByCategory(category); 

        
        ObservableList<MenuItem> observableList = FXCollections.observableArrayList(menuItems);

        
        menuItemsTable.setItems(observableList);
    }

    private void addItemToOrder(String i) {
        MenuItem selectedItem = menuItemsTable.getSelectionModel().getSelectedItem();
        int quantity = Integer.parseInt(i); 

        
        UIOrderItem existingItem = currentOrderItems.stream()
                .filter(uiOrderItem -> uiOrderItem.getMenuItem().equals(selectedItem))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            
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

        
        TableColumn<MenuItem, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<MenuItem, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<MenuItem, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<MenuItem, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        

        menuItemsTable.getColumns().addAll(nameColumn, descriptionColumn, priceColumn, categoryColumn);
        

        refreshMenuItemsTable();
    }

    private void refreshMenuItemsTable() {
        
        MenuItemDAO menuItemDAO = new MenuItemDAO();
        List<MenuItem> menuItems = menuItemDAO.getAllMenuItems(); 

        
        ObservableList<MenuItem> observableList = FXCollections.observableArrayList(menuItems);

        
        menuItemsTable.setItems(observableList);
    }

    private void setupOrderItemsTable() {
        orderItemsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        TableColumn<UIOrderItem, String> itemNameColumn = new TableColumn<>("Item Name");
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemName"));

        
        TableColumn<UIOrderItem, Double> itemPriceColumn = new TableColumn<>("Price");
        itemPriceColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getMenuItem().getPrice()));

        
        TableColumn<UIOrderItem, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());

        orderItemsTable.getColumns().addAll(itemNameColumn, itemPriceColumn, quantityColumn);
    }


    
}
