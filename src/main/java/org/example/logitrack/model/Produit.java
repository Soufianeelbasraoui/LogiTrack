package org.example.logitrack.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Produit")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String categorie;
    private double prix;
    private int quantiteStock;
    @OneToMany(mappedBy = "produit",cascade = CascadeType.ALL)
    private List<LigneCommande> ligneCommandes;

    public Produit() {
    }
}
