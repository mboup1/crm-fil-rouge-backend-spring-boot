package com.app.crmfilrougedame;

import com.app.crmfilrougedame.model.Client;
import com.app.crmfilrougedame.model.Order;
import com.app.crmfilrougedame.repository.ClientRepository;
import com.app.crmfilrougedame.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CrmFilRougeDameApplicationTests {

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	OrderRepository orderRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void addClientAndOrder() {
		Client client = new Client("Sopra10", "Fabrice", "Martin", "martin@mail.com", "0656858433", "abc", "xyz", "Nantes", "France", false);
		clientRepository.save(client);

		Order order = new Order("Formation10", "Angular init", 3, 1200, 0);
		order.setClient(client);
		orderRepository.save(order);
	}

}
