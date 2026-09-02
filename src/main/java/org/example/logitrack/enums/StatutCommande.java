package org.example.logitrack.enums;

public enum StatutCommande {
    EN_ATTENTE("En attente"),
    EXPEDIEE("Expédiée"),
    LIVREE("Livrée");

    private final String libelle;

    StatutCommande(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}
