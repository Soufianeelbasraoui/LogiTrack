package org.example.logitrack.service;

import lombok.RequiredArgsConstructor;
import org.example.logitrack.dto.ClientRequestDTO;
import org.example.logitrack.dto.ClientResponseDTO;
import org.example.logitrack.exception.ResourceNotFoundException;
import org.example.logitrack.mapper.ClientMapper;
import org.example.logitrack.model.Client;
import org.example.logitrack.repository.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public Page<ClientResponseDTO> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable)
                .map(clientMapper::toDto);
    }

    public ClientResponseDTO saveClient(ClientRequestDTO dto) {
        Client client = clientMapper.toEntity(dto);
        return clientMapper.toDto(clientRepository.save(client));
    }

    public ClientResponseDTO findClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", id));
        return clientMapper.toDto(client);
    }

    public ClientResponseDTO update(Long id, ClientRequestDTO dto) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", id));
        clientMapper.updateEntityFromDto(dto, client);
        return clientMapper.toDto(clientRepository.save(client));
    }

    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client", id);
        }
        clientRepository.deleteById(id);
    }

    public Page<ClientResponseDTO> findByNom(String nom, Pageable pageable) {
        return clientRepository.findByNomContainingIgnoreCase(nom, pageable)
                .map(clientMapper::toDto);
    }
    public long countClients() {
        return clientRepository.count();
    }
}
