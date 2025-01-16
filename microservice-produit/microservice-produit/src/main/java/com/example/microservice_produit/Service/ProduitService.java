package com.example.microservice_produit.Service;

import com.example.microservice_produit.Repository.ProduitRepository;
import com.example.microservice_produit.model.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository repository;

    public List<Produit> getAllProduits() {
        return repository.findAll();
    }

    public Optional<Produit> getProduitById(Long id) {
        return repository.findById(id);
    }

    public Produit saveProduit(Produit produit) {
        return repository.save(produit);
    }

    public Optional<Produit> updateProduit(Long id, Produit updatedProduit) {
        return repository.findById(id)
                .map(produit -> {
                    produit.setNom(updatedProduit.getNom());
                    produit.setDescription(updatedProduit.getDescription());
                    produit.setPrix(updatedProduit.getPrix());
                    produit.setStock(updatedProduit.getStock());
                    return repository.save(produit);
                });
    }

    public void deleteProduit(Long id) {
        repository.deleteById(id);
    }
}
