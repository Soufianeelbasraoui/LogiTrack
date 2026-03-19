package org.example.logitrack.controller;

import org.example.logitrack.model.Commande;

import org.example.logitrack.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {
   @Autowired
   private CommandeService commandeService;

   @GetMapping
    public List<Commande> getAllCommande(){
        return commandeService.findAllCommande();
   }

   @GetMapping("/{id}")
    public Commande getCommandeByID(@PathVariable int id){
       return commandeService.findCommandeById(id);
   }

   @PostMapping
    public Commande saveCommande(@RequestBody Commande commande){
       return  commandeService.saveCommande(commande);
   }
   @DeleteMapping("/{id}")
    public void deleteCommande(@PathVariable int id){
       commandeService.deleteCommande(id);
   }

    @PutMapping("/{id}/status")
    public Commande updateStatus(@PathVariable int id, @RequestBody String statut) {
        return commandeService.updateStatus(id, statut);
    }

    }

