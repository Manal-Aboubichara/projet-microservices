package com.projet.microservice_commandes.service;

import com.projet.microservice_commandes.model.Commande;
import com.projet.microservice_commandes.repository.CommandeRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {
    @Autowired
    private CommandeRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${mes-config-ms.commandes-last}")
    private int commandesLast;

    public List<Commande> getAllCommandes() {
        return repository.findAll();
    }

    public List<Commande> getRecentCommandes() {
        return repository.findByDateAfter(LocalDate.now().minusDays(commandesLast));
    }

    @CircuitBreaker(name = "microservice-produit", fallbackMethod = "getProduitFallback")
    public Optional<Commande> getCommandeById(Long id) {
        Optional<Commande> commande = repository.findById(id);
        if (commande.isPresent() && commande.get().getIdProduit() != null) {
            try {
                ResponseEntity<Object> produit = restTemplate.getForEntity(
                        "http://microservice-produit/api/produits/" + commande.get().getIdProduit(),
                        Object.class
                );
                // Si vous voulez faire quelque chose avec le produit, vous pouvez le faire ici
            } catch (Exception e) {
                // Log l'erreur si nécessaire
            }
        }
        return commande;
    }

    public Optional<Commande> getProduitFallback(Long id, Exception e) {
        return repository.findById(id);
    }

    public Commande saveCommande(Commande commande) {
        return repository.save(commande);
    }

    public Optional<Commande> updateCommande(Long id, Commande updatedCommande) {
        return repository.findById(id)
                .map(commande -> {
                    commande.setDescription(updatedCommande.getDescription());
                    commande.setQuantite(updatedCommande.getQuantite());
                    commande.setDate(updatedCommande.getDate());
                    commande.setMontant(updatedCommande.getMontant());
                    commande.setIdProduit(updatedCommande.getIdProduit()); // N'oubliez pas d'ajouter cette ligne
                    return repository.save(commande);
                });
    }

    public void deleteCommande(Long id) {
        repository.deleteById(id);
    }
}