package org.example.logitrack.repository;

import org.example.logitrack.enums.StatutCommande;
import org.example.logitrack.model.Commande;
import org.example.logitrack.model.Produit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {

    Page<Commande> findAll(Pageable pageable);

    Page<Commande> findByClientNomContainingIgnoreCase( String nom,Pageable pageable);
    Page<Commande> findByStatut(StatutCommande statut, Pageable pageable);

    @Query("SELECT COUNT(c) FROM Commande c")
    Long countAllCommandes();

    @Query("SELECT l.produit FROM LigneCommande l GROUP BY l.produit ORDER BY COUNT(l) DESC")
    List<Produit> findTopProduit(Pageable pageable);

    @Query("select count(c) from Commande c where c.statut='EN_ATTENTE'")
    Long countEnAttente();

    @Query("select count(c) from Commande c where c.statut='LIVREE'")
    Long countLIVREE();

    @Query("select count(c) from Commande c where c.statut='EXPEDIEE'")
    Long countEXPEDIEE();
    @Query("SELECT c FROM Commande c ORDER BY c.dateCommande DESC")
    List<Commande> findRecentCommandes(Pageable pageable);

}
