package com.inventario.cl.domain.model;

import java.util.Objects;

// Usamos récord (Java 14+) o clase final para inmutabilidad
public final class Product { // Si no usas récord

    private final String sku; // Stock Keeping Unit - Identificador único
    private final String name;
    private final int quantity;

    public Product(String sku, String name, int quantity) {
        // Validaciones de invariantes del dominio en el constructor
        this.sku = Objects.requireNonNull(sku, "SKU no puede ser null");
        this.name = Objects.requireNonNull(name, "Nombre no puede ser null");
        if (quantity < 0) {
            throw new IllegalArgumentException("Cantidad no puede ser negativa");
        }
        this.quantity = quantity;
    }

    // Método de dominio para actualizar la cantidad (devuelve nueva instancia)
    public Product withQuantity(int newQuantity) {
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Nueva cantidad no puede ser negativa");
        }
        return new Product(this.sku, this.name, newQuantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return quantity == product.quantity &&
                Objects.equals(sku, product.sku) &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, name, quantity);
    }

    @Override
    public String toString() {
        return "Product{" +
                "sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
