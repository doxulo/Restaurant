package restaurant.service;

import restaurant.model.Order;
import restaurant.dao.OrderDAO;


public class OrderService {
    private final OrderDAO orderDAO;

    public OrderService() {
        this.orderDAO = new OrderDAO();
        
    }

    public void createNewOrder(Order order) {
        
        
        orderDAO.insertOrder(order);
    }

    public void updateOrder(Order order) {
        
        orderDAO.updateOrder(order);
    }

    
}
