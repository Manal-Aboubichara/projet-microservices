package com.example.microservice_produit.Repository;

import com.example.microservice_produit.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
}
