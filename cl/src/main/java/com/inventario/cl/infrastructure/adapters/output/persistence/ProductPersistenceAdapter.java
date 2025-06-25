package com.inventario.cl.infrastructure.adapters.output.persistence;

import com.inventario.cl.application.ports.output.ProductPersistencePort;
import com.inventario.cl.domain.model.Product;
import com.inventario.cl.infrastructure.adapters.output.persistence.mapper.ProductPersistenceMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Este componente implementa el puerto de salida
@Component // Componente gestionado por Spring
public class ProductPersistenceAdapter implements ProductPersistencePort {

    // Depende del repositorio Spring Data JPA (Detalle de infraestructura)
    private final ProductRepository repository;
    private  final ProductPersistenceMapper mapper; // Inyectamos el mapper si es un componente

    // Inyección por constructor
    public ProductPersistenceAdapter(ProductRepository repository, ProductPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Product save(Product product) {
        // Mapea del modelo de dominio a la entidad JPA
        ProductEntity entityToSave = mapper.toEntity(Product); // Usamos el mapper inyectado
        // Usa el repositorio JPA para guardar
        ProductEntity savedEntity = repository.save(entityToSave);
        // Mapea de la entidad JPA guardada el modelo de dominio y lo vuelve
        return mapper.toDomain(savedEntity); // Usamos el mapper inyectado
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        // Usa el repositorio JPA
        Optional<ProductEntity> entityOptional = repository.findById(sku);
        // Mapea el resultado (Si existe) de entidad JPA a modelo de dominio
        return  entityOptional.map(mapper::toDomain); // Usamos el mapper inyectado
    }

    @Override
    public List<Product> findAll() {
        // Usa el repositorio JPA
        List<ProductEntity> entities = repository.findAll();
        // Mapea la lista de entidades JPA a una lista de modelos de dominio
        return entities.stream()
                .map(mapper::toDomain) // Usamo el mapper inyectado
                .collect(Collectors.toList());
    }
}
