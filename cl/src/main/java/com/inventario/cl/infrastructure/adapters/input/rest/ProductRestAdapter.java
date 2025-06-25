package com.inventario.cl.infrastructure.adapters.input.rest;

import com.inventario.cl.application.ports.input.ProductServicePort;
import com.inventario.cl.application.ports.input.dto.ProductDto;
import com.inventario.cl.domain.exception.ProductNotFoundException;
import com.inventario.cl.infrastructure.adapters.input.rest.dto.CreateProductRequest;
import com.inventario.cl.infrastructure.adapters.input.rest.dto.ProductResponse;
import com.inventario.cl.infrastructure.adapters.input.rest.mapper.ProductRestMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController // Indica que es un controlador REST de Spring
@RequestMapping("/api/products") // Path base para los endpoints
public class ProductRestAdapter {

    // Depende del puerto de entrada (interfaz), NO de la implementación concreta del servicio
    private final ProductServicePort service;
    private final ProductRestMapper mapper;

    // Inyección por constructor
    public ProductRestAdapter(ProductServicePort service, ProductRestMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping // Mapea a solicitudes POST /api/products
    public ResponseEntity<ProductResponse> create(@RequestBody CreateProductRequest request) {
        // Mapea la solicitud REST al comando de la capa de aplicación
        var command = mapper.toCommand(request);
        // Llama al servicio de la capa de aplicación a través de su puerto
        ProductDto productDto = service.createProduct(command);
        // Mapea el DTO de la capa de aplicación a la respuesta REST
        var response = ProductResponse.fromDto(productDto);

        // Devuelve una respuesta HTTP 201 Created con la URI del recurso creado
        return ResponseEntity
                .created(URI.create("/api/products/" + response.sku())) // Construye la URI del nuevo recurso
                .body(response);
    }

    @GetMapping("/{sku}") // Mapea a solicitudes GET /api/products/{sku}
    public ProductResponse getBySku(@PathVariable String sku) {
        // Llama al servicio a través de su puerto
        ProductDto productDto = service.getBySku(sku);
        // Mapea el DTO a la respuesta REST
        return ProductResponse.fromDto(productDto);
    }

    @GetMapping // Mapea a solicitudes GET /api/products
    public List<ProductResponse> listAll() {
        // Llama al servicio
        List<ProductDto> productDtos = service.listAll();
        // Mapea la lista de DTOs a una lista de respuestas REST
        return productDtos.stream()
                .map(ProductResponse::fromDto)
                .collect(Collectors.toList());
    }

    // Manejo de excepciones específicas para la API REST
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // Devuelve 404 Not Found
    public String handleProductNotFoundException(ProductNotFoundException ex) {
        return ex.getMessage(); // Devuelve el mensaje de la excepción
    }

    // Puedes añadir más @ExceptionHandler para otros tipos de excepciones (ej. Validaciones)
}