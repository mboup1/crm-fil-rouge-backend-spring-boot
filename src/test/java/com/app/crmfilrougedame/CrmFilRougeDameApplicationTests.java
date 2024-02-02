package com.app.crmfilrougedame;

import com.app.crmfilrougedame.enums.ClientState;
import com.app.crmfilrougedame.enums.OrderState;
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
		Client client1 = new Client("Sopra", "Fabrice", "Martin", "martin@mail.com", "0656858433", "abc", "xyz", "Nantes", "France", ClientState.ACTIVE);
		clientRepository.save(client1);

		Order order = new Order("Formation10", "Angular init", 3, 1200, OrderState.CANCELED);
		order.setClient(client1);
		orderRepository.save(order);


		//		// Creating and saving client2
//		Client client2 = new Client("M2I Formation", "Julien", "Lamard", "lamard@mail.com", "0611223344", "abc", "xyz", "Paris", "France", true);
//		clientRepository.save(client2);
//
//		// Creating and saving client3 (ATOS)
//		Client client3 = new Client("ATOS", "Alexandre", "Dupont", "alexandre.dupont@atos.net", "0102030405", "123 Boulevard de l’Innovation", "75001", "Paris", "France", true);
//		clientRepository.save(client3);
//
//// Creating and saving client4 (SOPRA STERIA)
//		Client client4 = new Client("SOPRA STERIA", "Isabelle", "Durand", "isabelle.durand@soprasteria.com", "0203040506", "456 Rue du Numérique", "69002", "Lyon", "France", true);
//		clientRepository.save(client4);
	}

}
