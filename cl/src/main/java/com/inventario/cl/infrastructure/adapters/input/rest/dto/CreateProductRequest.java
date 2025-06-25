package com.inventario.cl.infrastructure.adapters.input.rest.dto;

// DTO para la solicitud de creación de producto
public record CreateProductRequest(String sku, String name, int quantity) {}
