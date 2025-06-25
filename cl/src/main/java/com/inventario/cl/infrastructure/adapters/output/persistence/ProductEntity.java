package com.inventario.cl.infrastructure.adapters.output.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // Indica que es una entidad JPA
@Table(name = "products") // Nombre de la tabla en la BBDD
@Getter //Genera getters
@Setter // Genera setters (Necesarios para JPA/Hibernate
@NoArgsConstructor // Constructor sin argumentos (necesario para JPA)
public class ProductEntity {

    @Id // Indica que este campo es la clave primaria
    private  String sku; // Usamos el SKU como clave primaria
    private  String name;
    private  int quantity;

    // JPA necesita un constructor sin argumentos, los setters son para que pueda hidratar el objeto.
    // Esto constasta con el modelo de dominio inmutable, que es intencional.
}
