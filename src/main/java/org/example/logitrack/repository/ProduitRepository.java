package org.example.logitrack.repository;

import org.example.logitrack.dto.ProduitResponseDTO;
import org.example.logitrack.model.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

    Page<Produit> findAll(Pageable pageable);

    Page<Produit> findByCategorieContainingIgnoreCase(String categorie, Pageable pageable);

    Page<Produit> findByPrixLessThan(Double prix, Pageable pageable);

    Page<Produit> findProduitByPrix(double prix, Pageable pageable);

    Page<Produit> findByQuantiteStockLessThanEqual(int quantiteStock, Pageable pageable);
    @Query("SELECT DISTINCT p.categorie FROM Produit p")
    List<String> findDistinctCategories();

}
