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
import restaurant.ui.OrderUI;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        
        BorderPane root = new BorderPane();

        
        MenuBar menuBar = new MenuBar();
        root.setTop(menuBar);

        
        Menu menuManagement = new Menu("Menu Management");

        MenuItem orderManagement = new MenuItem("Order");
        MenuItem menuItemMenuManagement = new MenuItem("Manage Menu Items");
        MenuItem employeeManagement = new MenuItem("Manage Employees");

        menuManagement.getItems().add(orderManagement);
        menuManagement.getItems().add(menuItemMenuManagement);
        menuManagement.getItems().add(employeeManagement);

        orderManagement.setOnAction((e -> showOrderManagement(root)));
        menuItemMenuManagement.setOnAction(e -> showMenuManagement(root));
        employeeManagement.setOnAction(e -> showEmployeeManagement(root));
        

        menuBar.getMenus().addAll(menuManagement); 

        
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Restaurant POS System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showOrderManagement(BorderPane root) {
        
        
        root.setCenter(new OrderUI().createOrderUI());
    }

    private void showEmployeeManagement(BorderPane root) {
        
        
        root.setCenter(new EmployeeManagementUI().createEmployeeManagementUI());
    }

    private void showMenuManagement(BorderPane root) {
        
        
        root.setCenter(new MenuManagementUI().createMenuManagementUI());
    }

    

    public static void main(String[] args) {
        launch(args);
    }
}
