package org.example.logitrack.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDTO {
    @NotBlank
    private String nom;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String telephone;
    @NotBlank
    private String ville;
}
