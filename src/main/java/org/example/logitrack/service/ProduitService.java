package org.example.logitrack.service;

import org.example.logitrack.model.Produit;
import org.example.logitrack.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProduitService {
    @Autowired
    private ProduitRepository produitRepository;

    public List<Produit> getAllProduct(){
        return produitRepository.findAll();
    }

    public void saveProduit(Produit produit){
        produitRepository.save(produit);
    }

    public  Produit findProduitById(int id){
        return produitRepository.findById(id).orElse(null);
    }
    public void deletProduit(int id){
        produitRepository.deleteById(id);
    }
}
