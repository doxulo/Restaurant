package restaurant.model;

public class Inventory {
    private int inventoryId;
    private String itemName;
    private int quantity;
    private int reorderLevel;

    // Constructor
    public Inventory(int inventoryId, String itemName, int quantity, int reorderLevel) {
        this.inventoryId = inventoryId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.reorderLevel = reorderLevel;
    }

    // Getters and Setters
    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    // toString method for easy printing of Inventory details
    @Override
    public String toString() {
        return "Inventory{" +
                "inventoryId=" + inventoryId +
                ", itemName='" + itemName + '\'' +
                ", quantity=" + quantity +
                ", reorderLevel=" + reorderLevel +
                '}';
    }
}
