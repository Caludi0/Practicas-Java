package com.inventario.cl.infrastructure.adapters.output.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Spring Data JPA generará la implementación en tiempo de ejecución
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    // Spring Data JPA proporciona implementaciones para save(), findById(), findAll() automáticamente
    // Puedes añadir métodos custom si son necesarios (ej. findByNameIgnoringCase)
}