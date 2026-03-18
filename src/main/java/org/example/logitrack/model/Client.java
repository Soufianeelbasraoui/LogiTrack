package org.example.logitrack.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Client")
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String email;
    private String telephone;
    private String ville;
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    private List<Commande> commandes;

    public Client() {
    }
}
