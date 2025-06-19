package com.inventario.cl.domain.model;

import java.util.Objects;

// Usamos récord (Java 14+) o clase final para inmutalidad
public final class Product { //Si no usamos record

    private  final String sku; // Stock keeping unit - identificador unico
    private final String name;
    private final int quantiy;

    public  Product(String sku, String name, int quantiy) {
        // Validaciones de invariantes del dominio en el constructor
        this.sku = Objects.requireNonNull(sku, "SKU no puede ser null");
        this.name = Objects.requireNonNull(name, "Nombre no puede ser null");
        if (quantiy < 0) {
            throw new IllegalArgumentException("Cantidad no puede ser menor a 0");
        }
        this.quantiy = quantiy;
    }

    //Metodo de dominio para actualizar la cantidad (devuelve nueva instancia
    public  Product withQuantity(int newQuantity) {
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Nueva cantidad no puede ser menor a 0");
        }
        return  new Product(this.sku, this.name, newQuantity);
    }

    @Override
    public boolean equals(Objects o) {

    }
}
