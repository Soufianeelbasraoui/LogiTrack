package org.example.logitrack.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.logitrack.enums.StatutCommande;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandeRequestDTO {
    @NotNull
    private Long clientId;
    private StatutCommande statut;
}
