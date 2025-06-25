package com.inventario.cl.domain.exception;

// Hereda de RuntimeException para no forzar 'throws' en los métodos de puerto/servicio
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String sku) {
        super("Producto con SKU '" + sku + "' no encontrado");
    }
}