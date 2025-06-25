package com.inventario.cl.application.ports.input;

import com.inventario.cl.application.ports.input.dto.CreateProductCommand;
import com.inventario.cl.application.ports.input.dto.ProductDto;

import java.util.List;

// Este es un Puerto de Entrada (Driven Port)
public interface ProductServicePort {

    // Usa DTOs específicos para la entrada/salida de la aplicación si es necesario
    ProductDto createProduct(CreateProductCommand command);
    ProductDto getBySku(String sku);
    List<ProductDto> listAll();
}