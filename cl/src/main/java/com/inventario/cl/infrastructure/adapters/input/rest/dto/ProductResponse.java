package com.inventario.cl.infrastructure.adapters.input.rest.dto;

import com.inventario.cl.application.ports.input.dto.ProductDto;

// DTO para la respuesta de producto
public record ProductResponse(String sku, String name, int quantity) {

    // Método de fábrica para mapear desde el DTO de la capa de aplicación
    public static ProductResponse fromDto(ProductDto applicationDto) {
        return new ProductResponse(
                applicationDto.sku(),
                applicationDto.name(),
                applicationDto.quantity()
        );
    }
}
