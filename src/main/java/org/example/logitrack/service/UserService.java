package org.example.logitrack.service;

import org.example.logitrack.exception.ResourceNotFoundException;
import org.example.logitrack.model.Users;
import org.example.logitrack.repository.UserRepository;
import org.example.logitrack.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public Users addUser(Users user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email déjà utilisé.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public Page<Users> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
    public Page<Users> searchUsers(String keyword, Pageable pageable) {
        return userRepository.searchUsers(keyword, pageable);
    }
    public Optional<Users> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Users updateUser(Long id, Users userDetails) {
        Optional<Users> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            Users existingUser = optionalUser.get();
            if (userDetails.getNom() != null) existingUser.setNom(userDetails.getNom());
            if (userDetails.getPrenom() != null) existingUser.setPrenom(userDetails.getPrenom());
            if (userDetails.getEmail() != null) existingUser.setEmail(userDetails.getEmail());
            if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
                existingUser.setPassword(userDetails.getPassword());
            }
            if (userDetails.getRole() != null) existingUser.setRole(userDetails.getRole());

            return userRepository.save(existingUser);
        }
        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public long countAllUsers() {
        return userRepository.count();
    }

    public long countUsersByRole(Role role) {
        return userRepository.countByRole(role);
    }


}
