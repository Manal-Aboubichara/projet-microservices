package com.projet.microservice_commandes.controller;

import com.projet.microservice_commandes.model.Commande;
import com.projet.microservice_commandes.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    @Autowired
    private CommandeService service;

    @GetMapping
    public List<Commande> getAllCommandes() {
        return service.getAllCommandes();
    }

    @GetMapping("/recent")
    public List<Commande> getRecentCommandes() {
        return service.getRecentCommandes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable Long id) {
        return service.getCommandeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

  @PostMapping
  public Commande createCommande(@RequestBody Commande commande) {
     return service.saveCommande(commande);
   }

    @PutMapping("/{id}")
    public ResponseEntity<Commande> updateCommande(@PathVariable Long id, @RequestBody Commande updatedCommande) {
        return service.updateCommande(id, updatedCommande)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable Long id) {
        if (service.getCommandeById(id).isPresent()) {
            service.deleteCommande(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}