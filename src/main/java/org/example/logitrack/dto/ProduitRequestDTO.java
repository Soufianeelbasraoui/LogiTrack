package org.example.logitrack.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduitRequestDTO {
    @NotBlank
    private String nom;
    @NotBlank
    private String categorie;
    @NotNull
    @Positive
    private Double prix;
    @NotNull
    @Min(0)
    private Integer quantiteStock;
}
