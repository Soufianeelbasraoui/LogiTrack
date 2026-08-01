package org.example.logitrack.repository;

import org.example.logitrack.model.Users;
import org.example.logitrack.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByNom(String nom);
    long countByRole(Role role);
    Page<Users> findByRole(Role role, Pageable pageable);
    @Query("""
            SELECT u FROM Users u
            WHERE LOWER(u.nom) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(u.prenom) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%'))
            """)
    Page<Users> searchUsers(String keyword, Pageable pageable);

}