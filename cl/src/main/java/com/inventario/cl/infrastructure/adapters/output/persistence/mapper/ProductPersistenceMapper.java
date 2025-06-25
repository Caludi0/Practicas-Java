package com.inventario.cl.infrastructure.adapters.output.persistence.mapper;

import com.inventario.cl.domain.model.Product;
import com.inventario.cl.infrastructure.adapters.output.persistence.ProductEntity;
import org.springframework.stereotype.Component;

// Usamos metodos static para un mapeo sencillo
@Component // Opcional, si prefieres inyectar mapper. Si no, usa solo métodos estáticos
public class ProductPersistenceMapper {

    // Mapea de Entidad JPA a Modelo de Dominio
    public  static Product toDomain(ProductEntity entity) {
        if (entity == null) {
            return null;
        }
        // Creamos una nueva instancia del modelo de dominio
        return  new Product(entity.getSku(), entity.getName(), entity.getQuantity());
    }

    // Mapea de Modelo de Dominio a entidad JPA
    public  static ProductEntity toEntity(Product domain) {
        if (domain == null) {
            return null;
        }
        ProductEntity entity = new ProductEntity();
        entity.setSku(domain.getSku());
        entity.setName(domain.getName());
        entity.setQuantity(domain.getQuantity);
        // Usamos setters para la entidad JPA mutable
        return entity;
    }

    // Si usas Spring Component, puedes inyectar y usar métodos no estáticos:
    /*
    public Product toDomain(ProductEntity entity) {
        // ... implementación
    }
    */

}
