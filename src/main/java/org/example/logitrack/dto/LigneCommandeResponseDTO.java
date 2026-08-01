package org.example.logitrack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LigneCommandeResponseDTO {
    private Long id;
    private Long produitId;
    private String nomProduit;
    private Integer quantite;
    private double prix;
}
