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
        
        
        orderItemDAO.insertOrderItem(orderItem);
    }

    public void updateOrderItem(OrderItem orderItem) {
        
        orderItemDAO.updateOrderItem(orderItem);
    }

    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        
        return orderItemDAO.getOrderItemsByOrderId(orderId);
    }

    public void deleteOrderItem(int orderItemId) {
        
        
        orderItemDAO.deleteOrderItem(orderItemId);
    }

    
}
