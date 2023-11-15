package restaurant.service;

import restaurant.model.MenuItem;
import restaurant.dao.MenuItemDAO;
import java.util.List;

public class MenuItemService {
    private MenuItemDAO menuItemDAO;

    public MenuItemService() {
        this.menuItemDAO = new MenuItemDAO();
    }

    public void addMenuItem(MenuItem menuItem) {
        // Additional logic before adding a menu item can be implemented here
        menuItemDAO.insertMenuItem(menuItem);
    }

    public void updateMenuItem(MenuItem menuItem) {
        // Additional logic for updating a menu item's details
        menuItemDAO.updateMenuItem(menuItem);
    }

    public List<MenuItem> getAllMenuItems() {
        // Logic to retrieve all menu items
        return menuItemDAO.getAllMenuItems();
    }

    public MenuItem getMenuItemById(int itemId) {
        // Logic to retrieve a single menu item by ID
        // This might involve calling a method in MenuItemDAO that needs to be implemented
        return menuItemDAO.getMenuItemById(itemId);
    }

    public void deleteMenuItem(int itemId) {
        // Logic before deleting a menu item, if necessary
        menuItemDAO.deleteMenuItem(itemId);
    }

    // Additional methods as required for your business logic
}
