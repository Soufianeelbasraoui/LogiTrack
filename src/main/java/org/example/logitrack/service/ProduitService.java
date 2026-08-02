package org.example.logitrack.service;

import lombok.RequiredArgsConstructor;
import org.example.logitrack.dto.ProduitRequestDTO;
import org.example.logitrack.dto.ProduitResponseDTO;
import org.example.logitrack.exception.ResourceNotFoundException;
import org.example.logitrack.mapper.ProduitMapper;
import org.example.logitrack.model.Produit;
import org.example.logitrack.repository.ProduitRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProduitService {

    private final ProduitRepository produitRepository;
    private final ProduitMapper produitMapper;

    public List<ProduitResponseDTO> getAllProduct() {
        return produitRepository.findAll().stream()
                .map(produitMapper::toDto)
                .collect(Collectors.toList());
    }

    public Page<ProduitResponseDTO> findAll(Pageable pageable) {
        return produitRepository.findAll(pageable)
                .map(produitMapper::toDto);
    }

    public ProduitResponseDTO saveProduit(ProduitRequestDTO dto) {
        Produit produit = produitMapper.toEntity(dto);
        return produitMapper.toDto(produitRepository.save(produit));
    }

    public ProduitResponseDTO findProduitById(Long id) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit", id));
        return produitMapper.toDto(produit);
    }

    public ProduitResponseDTO update(Long id, ProduitRequestDTO dto) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit", id));
        produitMapper.updateEntityFromDto(dto, produit);
        return produitMapper.toDto(produitRepository.save(produit));
    }

    public void deletProduit(Long id) {
        if (!produitRepository.existsById(id)) {
            throw new ResourceNotFoundException("Produit", id);
        }
        produitRepository.deleteById(id);
    }

    public Page<ProduitResponseDTO> findByCategorie(String categorie, Pageable pageable) {
        return produitRepository.findByCategorie(categorie, pageable)
                .map(produitMapper::toDto);
    }

    public Page<ProduitResponseDTO> findByPrixLessThan(Double prix, Pageable pageable) {
        return produitRepository.findByPrixLessThan(prix, pageable)
                .map(produitMapper::toDto);
    }
    public long countProducts() {
        return produitRepository.count();
    }
    public Page<ProduitResponseDTO> findLowStock(Pageable pageable) {
        return produitRepository.findLowStock(pageable)
                .map(produitMapper::toDto);
    }
}
