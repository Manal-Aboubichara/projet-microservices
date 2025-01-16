package com.projet.microservice_commandes.health;

import com.projet.microservice_commandes.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CommandeHealthIndicator implements HealthIndicator {
    @Autowired
    private CommandeRepository repository;

    @Override
    public Health health() {
        long count = repository.count();
        if (count > 0) {
            return Health.up()
                    .withDetail("count", count)
                    .build();
        }
        return Health.down()
                .withDetail("count", 0)
                .withDetail("message", "Aucune commande trouvée")
                .build();
    }
}
