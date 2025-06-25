package com.inventario.cl.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// Configuración para habilitar Spring Data JPA y especificar dónde buscar repositorios
@Configuration
@EnableJpaRepositories(basePackages = "com.cesarlead.inventory.infrastructure.adapters.output.persistence")
public class PersistenceConfig {
    // No necesitas definir Beans aquí si Spring Boot Autoconfiguration ya los crea
    // (DataSource, EntityManagerFactory, TransactionManager), lo cual es el caso
    // con las dependencias de starter-data-jpa y h2.
    // Esta clase solo es necesaria para @EnableJpaRepositories si no está en la clase principal @SpringBootApplication
}