package com.inventario.cl.infrastructure.adapters.input.rest.mapper;

import com.inventario.cl.application.ports.input.dto.CreateProductCommand;
import com.inventario.cl.infrastructure.adapters.input.rest.dto.CreateProductRequest;
import org.springframework.stereotype.Component;

@Component // Puede ser un componente Spring
public class ProductRestMapper {

    // Mapea de Request REST a Comando de la capa de aplicación
    public CreateProductCommand toCommand(CreateProductRequest request) {
        if (request == null) {
            return null;
        }
        return new CreateProductCommand(request.sku(), request.name(), request.quantity());
    }

    // No necesitamos un mapper para ProductDto -> ProductResponse si usamos el método de fábrica en ProductResponse
}