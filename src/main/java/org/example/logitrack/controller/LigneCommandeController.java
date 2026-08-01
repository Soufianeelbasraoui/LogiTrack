package org.example.logitrack.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.logitrack.dto.LigneCommandeRequestDTO;
import org.example.logitrack.dto.LigneCommandeResponseDTO;
import org.example.logitrack.service.LigneCommandeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lignes-commandes")
@RequiredArgsConstructor
public class LigneCommandeController {

    private final LigneCommandeService ligneCommandeService;

    @GetMapping
    public ResponseEntity<Page<LigneCommandeResponseDTO>> getAllLignesCommandes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(ligneCommandeService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LigneCommandeResponseDTO> getLigneCommandeById(@PathVariable Long id) {
        return ResponseEntity.ok(ligneCommandeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<LigneCommandeResponseDTO> saveLigneCommande(
            @Valid @RequestBody LigneCommandeRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ligneCommandeService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LigneCommandeResponseDTO> updateLigneCommande(
            @PathVariable Long id,
            @Valid @RequestBody LigneCommandeRequestDTO dto) {
        return ResponseEntity.ok(ligneCommandeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLigneCommande(@PathVariable Long id) {
        ligneCommandeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
