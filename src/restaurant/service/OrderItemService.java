package restaurant.service;

import restaurant.model.OrderItem;
import restaurant.dao.OrderItemDAO;
import java.util.List;

public class OrderItemService {
    private OrderItemDAO orderItemDAO;

    public OrderItemService() {
        this.orderItemDAO = new OrderItemDAO();
    }

    public void addOrderItem(OrderItem orderItem) {
        // Additional logic before adding an order item can be implemented here
        // e.g., check if the item is available, update inventory, etc.
        orderItemDAO.insertOrderItem(orderItem);
    }

    public void updateOrderItem(OrderItem orderItem) {
        // Additional logic for updating an order item's details
        orderItemDAO.updateOrderItem(orderItem);
    }

    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        // Logic to retrieve all items for a specific order
        return orderItemDAO.getOrderItemsByOrderId(orderId);
    }

    public void deleteOrderItem(int orderItemId) {
        // Logic before deleting an order item, if necessary
        // e.g., update inventory, etc.
        orderItemDAO.deleteOrderItem(orderItemId);
    }

    // TODO: Add methods to handle quantity changes, pricing calculations, etc.
}
