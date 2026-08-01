package org.example.logitrack.repository;


import org.example.logitrack.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users ,Long> {

    Optional<Users> findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByNom(String nom);
}
