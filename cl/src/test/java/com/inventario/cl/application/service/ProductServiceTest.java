package com.inventario.cl.application.service;

import com.inventario.cl.application.ports.input.dto.CreateProductCommand;
import com.inventario.cl.application.ports.output.ProductPersistencePort;
import com.inventario.cl.domain.exception.ProductNotFoundException;
import com.inventario.cl.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

class ProductServiceTest {
    @Mock // Mockeamos la dependencia de salida (el puerto de persistencia)
    ProductPersistencePort persistence;

    @InjectMocks // La instancia de ProductService donde se inyectarán los mocks
    ProductService service;

    @BeforeEach
        // Se ejecuta antes de cada test
    void setUp() {
        // Inicializa los mocks anotados
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProduct_shouldSaveProductAndReturnDto() {
        // Arrange (Preparar)
        var command = new CreateProductCommand("SKU123", "Laptop", 10);
        var domainProduct = new Product("SKU123", "Laptop", 10); // El objeto dominio esperado
        // Definir el comportamiento del mock: cuando se llame save() con un Product igual a domainProduct, devolver domainProduct
        when(persistence.save(any(Product.class))).thenReturn(domainProduct); // Usamos any() porque la instancia exacta puede variar ligeramente

        // Act (Actuar)
        var resultDto = service.createProduct(command);

        // Assert (Asegurar)
        // Verificamos que el puerto de persistencia fue llamado con el Product esperado
        verify(persistence).save(argThat(p -> p.getSku().equals("SKU123") && p.getName().equals("Laptop") && p.getQuantity() == 10));
        // Verificamos el contenido del DTO retornado
        assertThat(resultDto).isNotNull();
        assertThat(resultDto.getSku()).isEqualTo("SKU123");
        assertThat(resultDto.getName()).isEqualTo("Laptop");
        assertThat(resultDto.getQuantity()).isEqualTo(10);
    }

    @Test
    void getBySku_whenProductExists_shouldReturnProductDto() {
        // Arrange
        String sku = "SKU456";
        var domainProduct = new Product(sku, "Mouse", 5);
        // Mock: cuando se llame findBySku() con "SKU456", devolver Optional.of(domainProduct)
        when(persistence.findBySku(sku)).thenReturn(Optional.of(domainProduct));

        // Act
        var resultDto = service.getBySku(sku);

        // Assert
        // Verificamos que el puerto fue llamado
        verify(persistence).findBySku(sku);
        // Verificamos el DTO retornado
        assertThat(resultDto).isNotNull();
        assertThat(resultDto.getSku()).isEqualTo(sku);
        assertThat(resultDto.getName()).isEqualTo("Mouse");
        assertThat(resultDto.getQuantity()).isEqualTo(5);
    }

    @Test
    void getBySku_whenProductDoesNotExist_shouldThrowNotFoundException() {
        // Arrange
        String sku = "SKU-NON-EXISTENT";
        // Mock: cuando se llame findBySku() con este SKU, devolver Optional.empty()
        when(persistence.findBySku(sku)).thenReturn(Optional.empty());

        // Act & Assert
        // Verificamos que se lanza la excepción esperada
        assertThatThrownBy(() -> service.getBySku(sku))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessageContaining(sku); // Verificamos el mensaje de la excepción

        // Verificamos que el puerto fue llamado
        verify(persistence).findBySku(sku);
        // Verificamos que NO se llamó a save() u otros métodos (opcional pero buena práctica)
        verifyNoMoreInteractions(persistence);
    }

    @Test
    void listAll_shouldReturnListOfProductDtos() {
        // Arrange
        var product1 = new Product("SKU001", "Keyboard", 10);
        var product2 = new Product("SKU002", "Monitor", 5);
        List<Product> domainProducts = List.of(product1, product2);
        // Mock: cuando se llame findAll(), devolver la lista de productos de dominio
        when(persistence.findAll()).thenReturn(domainProducts);

        // Act
        List<com.cesarlead.inventory.application.ports.input.dto.ProductDto> resultDtos = service.listAll();

        // Assert
        // Verificamos que el puerto fue llamado
        verify(persistence).findAll();
        // Verificamos la lista de DTOs retornada
        assertThat(resultDtos)
                .hasSize(2)
                .extracting("sku", "name", "quantity") // Extraemos campos para comparar fácilmente
                .containsExactlyInAnyOrder(
                        tuple("SKU001", "Keyboard", 10),
                        tuple("SKU002", "Monitor", 5)
                );
        verifyNoMoreInteractions(persistence);
    }

}