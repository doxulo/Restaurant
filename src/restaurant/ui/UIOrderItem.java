package restaurant.ui;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import restaurant.model.MenuItem;

public class UIOrderItem {
    private MenuItem menuItem;
    private IntegerProperty  quantity;

    public UIOrderItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = new SimpleIntegerProperty(quantity);;
    }

    
    public MenuItem getMenuItem() {
        return menuItem;
    }

    public String getMenuItemName() {
        return menuItem.getName();
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public final int getQuantity() {
        return quantity.get();
    }

    public final void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }
}
