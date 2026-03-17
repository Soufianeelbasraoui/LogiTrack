package org.example.logitrack.service;


import org.example.logitrack.model.Client;
import org.example.logitrack.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }
    public Client findClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
