package org.example.logitrack.service;

import org.example.logitrack.model.Commande;
import org.example.logitrack.model.LigneCommande;
import org.example.logitrack.model.Produit;
import org.example.logitrack.repository.CommandeRepository;
import org.example.logitrack.repository.LigneCommandeRepository;
import org.example.logitrack.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeService {
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;

    public List<Commande> findAllCommande(){
        return commandeRepository.findAll();
    }

    public Commande saveCommande(Commande commande) {
        return commandeRepository.save(commande);
    }
    public Commande findCommandeById(Integer id){
        return commandeRepository.findById(id).orElse(null);
    }
    public void deleteCommande(Integer id){
      commandeRepository.deleteById(id);
    }

    public Commande updateStatus(Integer id, String statut) {
        Commande commande = findCommandeById(id);
            commande.setStatut(statut);
            return commandeRepository.save(commande);

    }

}