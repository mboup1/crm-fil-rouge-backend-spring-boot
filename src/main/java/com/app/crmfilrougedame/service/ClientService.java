package com.app.crmfilrougedame.service;

import com.app.crmfilrougedame.model.Client;
import com.app.crmfilrougedame.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Client createClient(Client client) {
        // Add any business logic/validation if needed
        return clientRepository.save(client);
    }

    public Optional<Client> updateClient(Long id, Client updatedClient) {
        Optional<Client> existingClientOptional = clientRepository.findById(id);

        if (existingClientOptional.isPresent()) {
            Client existingClient = existingClientOptional.get();
            // Update fields based on your requirements
            existingClient.setCompanyName(updatedClient.getCompanyName());
            existingClient.setFirstName(updatedClient.getFirstName());
            // ... update other fields

            return Optional.of(clientRepository.save(existingClient));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteClient(Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
