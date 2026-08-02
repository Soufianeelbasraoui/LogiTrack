package org.example.logitrack.service;

import lombok.RequiredArgsConstructor;
import org.example.logitrack.dto.CommandeRequestDTO;
import org.example.logitrack.dto.CommandeResponseDTO;
import org.example.logitrack.dto.LigneCommandeRequestDTO;
import org.example.logitrack.dto.LigneCommandeResponseDTO;
import org.example.logitrack.enums.StatutCommande;
import org.example.logitrack.exception.ResourceNotFoundException;
import org.example.logitrack.mapper.CommandeMapper;
import org.example.logitrack.model.Client;
import org.example.logitrack.model.Commande;

import org.example.logitrack.model.LigneCommande;
import org.example.logitrack.model.Produit;
import org.example.logitrack.repository.ClientRepository;
import org.example.logitrack.repository.CommandeRepository;
import org.example.logitrack.repository.LigneCommandeRepository;
import org.example.logitrack.repository.ProduitRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
@RequiredArgsConstructor
public class CommandeService {

    private final CommandeRepository commandeRepository;
    private final ClientRepository clientRepository;
    private final CommandeMapper commandeMapper;
    private final LigneCommandeRepository ligneCommandeRepository;
    private final ProduitRepository produitRepository;

    public Page<CommandeResponseDTO> findAll(Pageable pageable) {
        return commandeRepository.findAll(pageable)
                .map(commandeMapper::toDto);
    }

    public CommandeResponseDTO saveCommande(CommandeRequestDTO dto) {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client", dto.getClientId()));

        Commande commande = commandeMapper.toEntity(dto);
        commande.setClient(client);
        if (commande.getDateCommande() == null) {
            commande.setDateCommande(LocalDate.now());
        }
        return commandeMapper.toDto(commandeRepository.save(commande));
    }

    public CommandeResponseDTO findCommandeById(Long id) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commande", id));
        return commandeMapper.toDto(commande);
    }

    public CommandeResponseDTO update(Long id, CommandeRequestDTO dto) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commande", id));

        if (dto.getClientId() != null) {
            Client client = clientRepository.findById(dto.getClientId())
                    .orElseThrow(() -> new ResourceNotFoundException("Client", dto.getClientId()));
            commande.setClient(client);
        }
        commandeMapper.updateEntityFromDto(dto, commande);
        return commandeMapper.toDto(commandeRepository.save(commande));
    }

    public void deleteCommande(Long id) {
        if (!commandeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Commande", id);
        }
        commandeRepository.deleteById(id);
    }

    public CommandeResponseDTO updateStatus(Long id, StatutCommande statut) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commande", id));
        commande.setStatut(statut);
        return commandeMapper.toDto(commandeRepository.save(commande));
    }

    public Page<CommandeResponseDTO> findByStatut(StatutCommande statut, Pageable pageable) {
        return commandeRepository.findByStatut(statut, pageable)
                .map(commandeMapper::toDto);
    }

    public Page<CommandeResponseDTO> findByClientId(Long clientId, Pageable pageable) {
        return commandeRepository.findByClientId(clientId, pageable)
                .map(commandeMapper::toDto);
    }
    public long countCommandes() {
        return commandeRepository.count();
    }
    public LigneCommandeResponseDTO addProductToOrder(Long orderId, LigneCommandeRequestDTO dto) {

        Commande commande = commandeRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Commande", orderId));

        Produit produit = produitRepository.findById(dto.getProduitId()).orElseThrow(() -> new ResourceNotFoundException("Produit", dto.getProduitId()));

        LigneCommande ligneCommande = new LigneCommande();
        ligneCommande.setCommande(commande);
        ligneCommande.setProduit(produit);
        ligneCommande.setQuantite(dto.getQuantite());
        LigneCommande saved = ligneCommandeRepository.save(ligneCommande);
        LigneCommandeResponseDTO response = new LigneCommandeResponseDTO();
        response.setId(saved.getId());
        response.setProduitId(saved.getProduit().getId());
        response.setCommandeId(saved.getCommande().getId());
        response.setQuantite(saved.getQuantite());

        return response;
    }
}