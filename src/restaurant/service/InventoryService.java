package restaurant.service;

import restaurant.model.Inventory;
import restaurant.dao.InventoryDAO;


public class InventoryService {
    private final InventoryDAO inventoryDAO;

    public InventoryService() {
        this.inventoryDAO = new InventoryDAO();
        
    }

    public void updateInventory(Inventory inventory) {
        
        inventoryDAO.updateInventoryItem(inventory);
    }

    
}
