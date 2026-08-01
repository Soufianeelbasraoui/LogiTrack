package org.example.logitrack.service;

import lombok.RequiredArgsConstructor;
import org.example.logitrack.dto.LigneCommandeRequestDTO;
import org.example.logitrack.dto.LigneCommandeResponseDTO;
import org.example.logitrack.exception.ResourceNotFoundException;
import org.example.logitrack.mapper.LigneCommandeMapper;
import org.example.logitrack.model.Commande;
import org.example.logitrack.model.LigneCommande;
import org.example.logitrack.model.Produit;
import org.example.logitrack.repository.CommandeRepository;
import org.example.logitrack.repository.LigneCommandeRepository;
import org.example.logitrack.repository.ProduitRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LigneCommandeService {

    private final LigneCommandeRepository ligneCommandeRepository;
    private final LigneCommandeMapper ligneCommandeMapper;
    private final ProduitRepository produitRepository;
    private final CommandeRepository commandeRepository;

    public List<LigneCommandeResponseDTO> findAll() {
        return ligneCommandeRepository.findAll().stream()
                .map(ligneCommandeMapper::toDto)
                .collect(Collectors.toList());
    }

    public Page<LigneCommandeResponseDTO> findAll(Pageable pageable) {
        return ligneCommandeRepository.findAll(pageable)
                .map(ligneCommandeMapper::toDto);
    }

    public LigneCommandeResponseDTO save(LigneCommandeRequestDTO dto) {
        Produit produit = produitRepository.findById(dto.getProduitId())
                .orElseThrow(() -> new ResourceNotFoundException("Produit", dto.getProduitId()));

        LigneCommande ligne = ligneCommandeMapper.toEntity(dto);
        ligne.setProduit(produit);
        return ligneCommandeMapper.toDto(ligneCommandeRepository.save(ligne));
    }

    public LigneCommandeResponseDTO findById(Long id) {
        LigneCommande ligne = ligneCommandeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LigneCommande", id));
        return ligneCommandeMapper.toDto(ligne);
    }

    public LigneCommandeResponseDTO update(Long id, LigneCommandeRequestDTO dto) {
        LigneCommande ligne = ligneCommandeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LigneCommande", id));

        if (dto.getProduitId() != null) {
            Produit produit = produitRepository.findById(dto.getProduitId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produit", dto.getProduitId()));
            ligne.setProduit(produit);
        }
        ligneCommandeMapper.updateEntityFromDto(dto, ligne);
        return ligneCommandeMapper.toDto(ligneCommandeRepository.save(ligne));
    }

    public void delete(Long id) {
        if (!ligneCommandeRepository.existsById(id)) {
            throw new ResourceNotFoundException("LigneCommande", id);
        }
        ligneCommandeRepository.deleteById(id);
    }
}
