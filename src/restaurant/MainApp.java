package restaurant;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import restaurant.ui.EmployeeManagementUI;
import restaurant.ui.MenuManagementUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Root layout
        BorderPane root = new BorderPane();

        // Setting up the menu bar
        MenuBar menuBar = new MenuBar();
        root.setTop(menuBar);

        // Menu for Menu Management
        Menu menuManagement = new Menu("Menu Management");

        MenuItem homeMenuManagement = new MenuItem("Home");
        MenuItem menuItemMenuManagement = new MenuItem("Manage Menu Items");
        MenuItem employeeManagement = new MenuItem("Manage Employees");

        menuManagement.getItems().add(homeMenuManagement);
        menuManagement.getItems().add(menuItemMenuManagement);
        menuManagement.getItems().add(employeeManagement);

        homeMenuManagement.setOnAction(e -> showHome(root));
        menuItemMenuManagement.setOnAction(e -> showMenuManagement(root));
        employeeManagement.setOnAction(e -> showEmployeeManagement(root));
        // Add other menus similarly...

        menuBar.getMenus().addAll(menuManagement); // Add more menus as needed

        // Setting up the scene
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Restaurant POS System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showEmployeeManagement(BorderPane root) {
        // Logic to display the menu management UI
        // Replace with your actual UI code
        root.setCenter(new EmployeeManagementUI().createEmployeeManagementUI());
    }

    private void showHome(BorderPane root) {

    }

    private void showMenuManagement(BorderPane root) {
        // Logic to display the menu management UI
        // Replace with your actual UI code
        root.setCenter(new MenuManagementUI().createMenuManagementUI());
    }

    // Similarly, add methods for other sections like showOrderProcessing, showInventoryManagement, etc.

    public static void main(String[] args) {
        launch(args);
    }
}
