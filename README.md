# Microservices Spring Cloud - Gestion des Commandes et Produits

## Équipe de Développement
- ABOU-BICHARA Manal
- KHELFAOUI Laila

## Description du Projet
Ce projet implémente une architecture microservices pour la gestion des commandes et des produits. Il utilise Spring Cloud pour la configuration centralisée, la découverte de services et le routage API.

## Architecture
Le projet est composé de 5 services principaux :
1. **Eureka Server** (Port: 8761) - Service de découverte
2. **Config Server** (Port: 8888) - Configuration centralisée
3. **API Gateway** (Port: 8090) - Point d'entrée unique
4. **Microservice Produits** (Port: 8081) - Gestion des produits
5. **Microservice Commandes** (Port: 8080) - Gestion des commandes

## Prérequis
- Java 17
- Maven
- Git
- GitHub Account

## Configuration
1. Cloner le repository de configuration :
```bash
git clone https://github.com/Manal-Aboubichara/projet-microservices.git
```

2. Configurer les URLs dans application.yml de chaque service

## Installation et Démarrage
1. Démarrer les services dans l'ordre suivant :
```bash
# 1. Eureka Server
cd eureka-server
mvn spring-boot:run

# 2. Config Server
cd ../config-server
mvn spring-boot:run

# 3. API Gateway
cd ../api-gateway
mvn spring-boot:run

# 4. Microservice Produits
cd ../microservice-produit
mvn spring-boot:run

# 5. Microservice Commandes
cd ../microservice-commandes
mvn spring-boot:run
```

## Endpoints API

### Produits
- `GET /api/produits` - Liste tous les produits
- `GET /api/produits/{id}` - Obtient un produit par ID
- `POST /api/produits` - Crée un nouveau produit
- `PUT /api/produits/{id}` - Met à jour un produit
- `DELETE /api/produits/{id}` - Supprime un produit

### Commandes
- `GET /api/commandes` - Liste toutes les commandes
- `GET /api/commandes/recent` - Liste les commandes récentes
- `GET /api/commandes/{id}` - Obtient une commande par ID
- `POST /api/commandes` - Crée une nouvelle commande
- `PUT /api/commandes/{id}` - Met à jour une commande
- `DELETE /api/commandes/{id}` - Supprime une commande

## Fonctionnalités Implémentées
- ✅ Circuit Breaker avec Resilience4j
- ✅ Load Balancing
- ✅ Configuration centralisée
- ✅ Service Discovery
- ✅ API Gateway
- ✅ Health Checks personalisés

## Monitoring et Santé
- Eureka Dashboard: http://localhost:8761
- H2 Console Produits: http://localhost:8081/h2-console
- H2 Console Commandes: http://localhost:8080/h2-console
- Actuator Endpoints: 
  - http://localhost:8080/actuator/health
  - http://localhost:8081/actuator/health

## Dépannage
1. Si un service ne démarre pas, vérifier que le port n'est pas déjà utilisé
2. Vérifier que le Config Server est bien démarré avant les autres services
3. Vérifier les logs pour les erreurs spécifiques

## Contribution
Pour contribuer au projet :
1. Fork le repository
2. Créer une branche pour votre fonctionnalité
3. Commiter vos changements
4. Créer une Pull Request
