package org.example.logitrack.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.logitrack.dto.ProduitRequestDTO;
import org.example.logitrack.dto.ProduitResponseDTO;
import org.example.logitrack.service.ProduitService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProduitController {

    private final ProduitService produitService;

    @GetMapping
    public ResponseEntity<Page<ProduitResponseDTO>> getAllProduit(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(produitService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProduitResponseDTO> getProduitById(@PathVariable Long id) {
        return ResponseEntity.ok(produitService.findProduitById(id));
    }

    @PostMapping
    public ResponseEntity<ProduitResponseDTO> saveProduit(@Valid @RequestBody ProduitRequestDTO produit) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produitService.saveProduit(produit));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProduitResponseDTO> updateProduit(
            @PathVariable Long id,
            @Valid @RequestBody ProduitRequestDTO dto) {
        return ResponseEntity.ok(produitService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletPorduitById(@PathVariable Long id) {
        produitService.deletProduit(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProduitResponseDTO>> searchProduit(
            @RequestParam(required = false) String categorie,
            @RequestParam(required = false) Double prix,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        if (categorie != null) {
            return ResponseEntity.ok(produitService.findByCategorie(categorie, pageable));
        }
        if (prix != null) {
            return ResponseEntity.ok(produitService.findByPrixLessThan(prix, pageable));
        }
        return ResponseEntity.ok(produitService.findAll(pageable));
    }
}
