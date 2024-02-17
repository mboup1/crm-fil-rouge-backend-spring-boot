package com.app.crmfilrougedame.service;

import com.app.crmfilrougedame.model.Client;
import com.app.crmfilrougedame.model.Order;
import com.app.crmfilrougedame.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    ClientService clientService;

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order createOrder(Order order) {
        Long clientId = order.getIdClient();
        Optional<Client> clientOptional = clientService.getClientById(clientId);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            order.setClient(client);
            return orderRepository.save(order);
        } else {
            // Gérer le cas où le client n'est pas trouvé
            throw new RuntimeException("Pas de client avec  l'id: " + clientId);
        }
    }


    public Optional<Order> updateOrder(Long id, Order updatedOrder) {
        Optional<Order> existingOrderOptional = orderRepository.findById(id);

        if (existingOrderOptional.isPresent()) {
            Order existingOrder = existingOrderOptional.get();

            existingOrder.setTypePresta(updatedOrder.getTypePresta());
            existingOrder.setDesignation(updatedOrder.getDesignation());
            existingOrder.setDesignation(updatedOrder.getDesignation());
            existingOrder.setNbDays(updatedOrder.getNbDays());
            existingOrder.setUnitPrice(updatedOrder.getUnitPrice());
            existingOrder.setState(updatedOrder.getState());

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
