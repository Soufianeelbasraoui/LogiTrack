package org.example.logitrack.service;

import lombok.RequiredArgsConstructor;
import org.example.logitrack.dto.AuthRequest;
import org.example.logitrack.dto.AuthResponse;
import org.example.logitrack.dto.RegisterRequest;
import org.example.logitrack.model.Users;
import org.example.logitrack.repository.UserRepository;
import org.example.logitrack.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        Users user = new Users();
        user.setNom(request.getNom());
        user.setPrenom(request.getPrenom());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        userRepository.save(user);
        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getNom(),
                user.getRole().name()
        );
        return new AuthResponse(token);
    }
    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
                )
        );
        Users user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getNom(),
                user.getRole().name()
        );
        return new AuthResponse(token);
    }
}