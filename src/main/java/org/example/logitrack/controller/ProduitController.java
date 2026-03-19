package org.example.logitrack.controller;

import org.example.logitrack.model.Produit;
import org.example.logitrack.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProduitController {
    @Autowired
    private ProduitService produitService;

    @GetMapping
    public List<Produit> getAllProduit(){
        return produitService.getAllProduct();
    }
    @GetMapping("/{id}")
    public Produit getProduitById(@PathVariable int id){
        return produitService.findProduitById(id);
    }
    @PostMapping
    public void saveProduit(@RequestBody Produit produit){
         produitService.saveProduit(produit);
    }
    @DeleteMapping("{id}")
    public void deletPorduitById(@PathVariable int id){
        produitService.deletProduit(id);
    }
}
