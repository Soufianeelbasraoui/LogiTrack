package org.example.logitrack.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.logitrack.dto.CommandeRequestDTO;
import org.example.logitrack.dto.CommandeResponseDTO;
import org.example.logitrack.dto.LigneCommandeRequestDTO;
import org.example.logitrack.dto.LigneCommandeResponseDTO;
import org.example.logitrack.enums.StatutCommande;
import org.example.logitrack.service.CommandeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/commandes")
@RequiredArgsConstructor
public class CommandeController {

    private final CommandeService commandeService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','AGENT','MANAGER')")
    public ResponseEntity<Page<CommandeResponseDTO>> getAllCommande(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(commandeService.findAll(pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','AGENT','MANAGER')")
    public ResponseEntity<CommandeResponseDTO> getCommandeByID(@PathVariable Long id) {
        return ResponseEntity.ok(commandeService.findCommandeById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<CommandeResponseDTO> saveCommande(@Valid @RequestBody CommandeRequestDTO commande) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commandeService.saveCommande(commande));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<CommandeResponseDTO> updateCommande(
            @PathVariable Long id,
            @Valid @RequestBody CommandeRequestDTO dto) {
        return ResponseEntity.ok(commandeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<Void> deleteCommande(@PathVariable Long id) {
        commandeService.deleteCommande(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN','AGENT','MANAGER')")
    public ResponseEntity<CommandeResponseDTO> updateStatus(
            @PathVariable Long id,
            @RequestBody StatutCommande statut) {
        return ResponseEntity.ok(commandeService.updateStatus(id, statut));
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<Page<CommandeResponseDTO>> searchByStatut(
            @RequestParam StatutCommande statut,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(commandeService.findByStatut(statut, pageable));
    }
    @GetMapping("/count")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<Long> countCommandes() {
        return ResponseEntity.ok(commandeService.countCommandes());
    }

    @PostMapping("/{orderId}/products")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<LigneCommandeResponseDTO> addProductToOrder(@PathVariable Long orderId, @RequestBody LigneCommandeRequestDTO dto) {
        return ResponseEntity.ok(commandeService.addProductToOrder(orderId, dto));
    }
}
