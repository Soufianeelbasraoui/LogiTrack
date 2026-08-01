package org.example.logitrack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.logitrack.enums.StatutCommande;


import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandeResponseDTO {
    private Long id;
    private LocalDate dateCommande;
    private StatutCommande statut;
    private Long clientId;
    private String nomClient;
}
