package com.inventario.cl.application.service;

import com.inventario.cl.application.ports.input.ProductServicePort;
import com.inventario.cl.application.ports.input.dto.CreateProductCommand;
import com.inventario.cl.application.ports.input.dto.ProductDto;
import com.inventario.cl.application.ports.output.ProductPersistencePort;
import com.inventario.cl.domain.exception.ProductNotFoundException;
import com.inventario.cl.domain.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// Implementa el puerto de entrada
@Service // Spring lo reconoce como un componente gestionable
public class ProductService implements ProductServicePort {

    // Depende del puerto de salida (abstracción), NO de la implementación concreta
    private final ProductPersistencePort persistence;

    // Inyección de dependencias por constructor (preferible en Spring)
    public ProductService(ProductPersistencePort persistence) {
        this.persistence = persistence;
    }

    @Override
    public ProductDto createProduct(CreateProductCommand cmd) {
        // Mapea el comando de entrada al modelo de dominio
        Product toSave = new Product(cmd.sku(), cmd.name(), cmd.quantity());

        // Usa el puerto de salida para interactuar con la persistencia
        Product saved = persistence.save(toSave);

        // Mapea el modelo de dominio guardado al DTO de salida
        return ProductDto.fromDomain(saved);
    }

    @Override
    public ProductDto getBySku(String sku) {
        // Usa el puerto de salida
        Product product = persistence.findBySku(sku)
                .orElseThrow(() -> new ProductNotFoundException(sku)); // Lógica de negocio: si no existe, lanza excepción de dominio

        // Mapea al DTO de salida
        return ProductDto.fromDomain(product);
    }

    @Override
    public List<ProductDto> listAll() {
        // Usa el puerto de salida
        List<Product> products = persistence.findAll();

        // Mapea la lista de modelos de dominio a una lista de DTOs de salida
        return products.stream()
                .map(ProductDto::fromDomain)
                .collect(Collectors.toList());
    }
}
