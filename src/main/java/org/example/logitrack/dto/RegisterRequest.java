package org.example.logitrack.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.logitrack.enums.Role;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Username is required")
    private String nom;
    @NotBlank(message = "prénom est requis")
    private String prenom;
    @Email(message = "invalide email")
    @Email(message = "le email est obligatoire")
    private String email;

    @Size(min = 4,message = "Password must be at least 6 characters")
    private String password;

    private Role role;
}
