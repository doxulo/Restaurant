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



public class MenuManagementUI extends Notification{

    private static final Logger logger = LoggerFactory.getLogger(MenuManagementUI.class);
    private TableView<MenuItem> table;

    public VBox createMenuManagementUI() {
        table = new TableView<>();
        setupTable();

        
        TextField nameInput = new TextField();
        nameInput.setPromptText("Name");

        TextField descriptionInput = new TextField();
        descriptionInput.setPromptText("Description");

        TextField priceInput = new TextField();
        priceInput.setPromptText("Price");

        ChoiceBox categoryInput = new ChoiceBox();
        categoryInput.getItems().addAll("Appetizers", "Entrées", "Side Dishes", "Desserts", "Beverages");

        
        Text text1 = new Text();
        text1.setText("Category: ");

        
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addMenuItem(nameInput.getText(), descriptionInput.getText(), priceInput.getText(), (String) categoryInput.getValue()));

        Button updateButton = new Button("Update"); 
        updateButton.setOnAction(e -> updateSelectedItem(nameInput.getText(), priceInput.getText()));
        
        Button deleteButton = new Button("Delete"); 
        deleteButton.setOnAction(e -> deleteSelectedItem());

        
        HBox inputLayout = new HBox(10);
        inputLayout.getChildren().addAll(nameInput, descriptionInput, priceInput, text1, categoryInput, addButton, updateButton, deleteButton);

        
        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(table, inputLayout);

        return mainLayout;
    }

    private void updateSelectedItem(String name, String priceStr) {
        
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
            
            selectedItem.setName(name);
            selectedItem.setPrice(Double.parseDouble(priceStr));
            

            
            MenuItemDAO menuItemDAO = new MenuItemDAO();
            menuItemDAO.updateMenuItem(selectedItem);

            
            refreshMenuItemsTable();
        } else {
            
        }
    }


    private void deleteSelectedItem() {
        MenuItem selectedItem = table.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            
            MenuItemDAO menuItemDAO = new MenuItemDAO();
            menuItemDAO.deleteMenuItem(selectedItem.getItemId()); 

            
            refreshMenuItemsTable();
        } else {
            
        }
    }

    private void setupTable() {
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        
        TableColumn<MenuItem, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<MenuItem, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<MenuItem, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<MenuItem, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        

        table.getColumns().addAll(nameColumn, descriptionColumn, priceColumn, categoryColumn);
        

        refreshMenuItemsTable();

    }
    private void addMenuItem(String name, String description, String priceStr, String category) {
        
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
        
        MenuItem menuItem = new MenuItem(0, name, description, price, category);

        
        MenuItemDAO menuItemDAO = new MenuItemDAO();
        menuItemDAO.insertMenuItem(menuItem);

        
        showAlert("Success", "Menu item added successfully.");

        
        refreshMenuItemsTable();
    }

    private void refreshMenuItemsTable() {
        
        MenuItemDAO menuItemDAO = new MenuItemDAO();
        List<MenuItem> menuItems = menuItemDAO.getAllMenuItems(); 

        
        ObservableList<MenuItem> observableList = FXCollections.observableArrayList(menuItems);

        
        table.setItems(observableList);
    }

    
}
