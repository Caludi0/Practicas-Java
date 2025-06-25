package com.inventario.cl.application.ports.output;

import com.inventario.cl.domain.model.Product;

import java.util.List;
import java.util.Optional;

// Este es un Puerto de Salida (Driving Port)
public interface ProductPersistencePort {

    Product save(Product product);
    Optional<Product> findBySku(String sku);
    List<Product> findAll();
}