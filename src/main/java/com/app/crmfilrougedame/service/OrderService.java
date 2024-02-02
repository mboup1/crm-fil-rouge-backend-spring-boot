package com.app.crmfilrougedame.service;

import com.app.crmfilrougedame.model.Order;
import com.app.crmfilrougedame.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order createOrder(Order order) {
        // Add any business logic/validation if needed
        return orderRepository.save(order);
    }

    public Optional<Order> updateOrder(Long id, Order updatedOrder) {
        Optional<Order> existingOrderOptional = orderRepository.findById(id);

        if (existingOrderOptional.isPresent()) {
            Order existingOrder = existingOrderOptional.get();
            // Update fields based on your requirements
            existingOrder.setTypePresta(updatedOrder.getTypePresta());
            existingOrder.setDesignation(updatedOrder.getDesignation());
            // ... update other fields

            return Optional.of(orderRepository.save(existingOrder));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
