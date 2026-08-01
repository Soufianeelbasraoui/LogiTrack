package org.example.logitrack.repository;

import org.example.logitrack.model.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

    Page<Produit> findAll(Pageable pageable);

    Page<Produit> findByCategorie(String categorie, Pageable pageable);

    Page<Produit> findByPrixLessThan(Double prix, Pageable pageable);

    @Query("SELECT p FROM Produit p WHERE p.quantiteStock < :seuil")
    Page<Produit> findLowStock(@Param("seuil") int seuil, Pageable pageable);
}
