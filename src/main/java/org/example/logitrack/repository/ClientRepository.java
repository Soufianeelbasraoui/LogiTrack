package org.example.logitrack.repository;

import org.example.logitrack.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client ,Long> {
}
