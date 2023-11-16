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
        
        menuItemDAO.insertMenuItem(menuItem);
    }

    public void updateMenuItem(MenuItem menuItem) {
        
        menuItemDAO.updateMenuItem(menuItem);
    }

    public List<MenuItem> getAllMenuItems() {
        
        return menuItemDAO.getAllMenuItems();
    }

    public MenuItem getMenuItemById(int itemId) {
        
        
        return menuItemDAO.getMenuItemById(itemId);
    }

    public void deleteMenuItem(int itemId) {
        
        menuItemDAO.deleteMenuItem(itemId);
    }

    
}
