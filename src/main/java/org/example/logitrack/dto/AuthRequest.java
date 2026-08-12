package org.example.logitrack.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
    @Email(message = "le email est obligatoire")
    private String email;
    @Size(min = 4,message = "Le mot de passe est obligatoire")
    private String password;
}
