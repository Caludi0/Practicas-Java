package com.inventario.cl.application.ports.input.dto;

import com.inventario.cl.domain.model.Product;

// DTO de salida que representa un producto
public record ProductDto(String sku, String name, int quantity) {

    // Método de fábrica para mapear del modelo de dominio al DTO
    public static ProductDto fromDomain(Product product) {
        return new ProductDto(product.sku(), product.getName(), product.getQuantity());
    }
}