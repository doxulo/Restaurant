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


public class OrderUI {

    private TableView<MenuItem> menuItemsTable;
    private TableView<MenuItem> orderItemsTable; 
    private ChoiceBox<String> categoryFilter; 

    public VBox createOrderUI() {
        menuItemsTable = new TableView<>();

        orderItemsTable = new TableView<>();


        setupMenuItemsTable();
        setupOrderItemsTable();

        categoryFilter = new ChoiceBox<>();
        categoryFilter.getItems().addAll(new String[]{"Appetizers", "Entrées", "Side Dishes", "Desserts", "Beverages"});
        
        categoryFilter.setOnAction(e -> updateMenuItems(categoryFilter.getValue()));

        TextField quantityInput = new TextField();
        quantityInput.setPromptText("Quantity");

        Button addOrderButton = new Button("Add Order");
        addOrderButton.setOnAction(e -> addItemToOrder(Integer.parseInt(quantityInput.getText())));

        

        HBox tablesLayout = new HBox(10);
        tablesLayout.getChildren().addAll(menuItemsTable, orderItemsTable);
        HBox.setHgrow(menuItemsTable, Priority.ALWAYS);
        HBox.setHgrow(orderItemsTable, Priority.ALWAYS);

        HBox controlsLayout = new HBox(10);
        controlsLayout.getChildren().addAll(categoryFilter, quantityInput, addOrderButton);

        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(tablesLayout, controlsLayout);
        VBox.setMargin(controlsLayout, new Insets(10, 0, 0, 0)); 

        return mainLayout;
    }

    private void updateMenuItems(String category) {
        
        MenuItemDAO menuItemDAO = new MenuItemDAO();
        List<MenuItem> menuItems = menuItemDAO.getMenuItemByCategory(category); 

        
        ObservableList<MenuItem> observableList = FXCollections.observableArrayList(menuItems);

        
        menuItemsTable.setItems(observableList);
    }

    private void addItemToOrder(int i) {

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
        
    }

    
}
