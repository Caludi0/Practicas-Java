package com.inventario.cl.application.ports.input.dto;

// Record para un DTO simple
public record CreateProductCommand(String sku, String name, int quantity) {}