package com.inventario.cl.infrastructure.adapters.input.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

// Carga el contexto completo de Spring Boot para pruebas de integración
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // Inicia el servidor en un puerto aleatorio
@AutoConfigureMockMvc // Configura MockMvc para realizar llamadas HTTP simuladas
class ProductRestAdapterTest {
    @Autowired
    private MockMvc mvc; // Inyecta MockMvc

    @Test
    void createAndGetProduct_shouldSucceed() throws Exception {
        // Paso 1: Crear un producto (POST)
        String createProductRequestBody = "{\"sku\":\"INT-TEST-001\",\"name\":\"Integration Test Product\",\"quantity\":99}";

        mvc.perform(MockMvcRequestBuilders.post("/api/products") // Realiza un POST a /api/products
                        .contentType(MediaType.APPLICATION_JSON) // Indica que el cuerpo es JSON
                        .content(createProductRequestBody)) // Establece el cuerpo de la solicitud
                .andExpect(MockMvcResultMatchers.status().isCreated()) // Espera un código de estado 201 Created
                .andExpect(MockMvcResultMatchers.header().exists("Location")) // Espera que exista el header Location
                .andExpect(MockMvcResultMatchers.jsonPath("$.sku").value("INT-TEST-001")); // Verifica un campo en la respuesta JSON

        // Paso 2: Obtener el producto creado (GET)
        mvc.perform(MockMvcRequestBuilders.get("/api/products/INT-TEST-001")) // Realiza un GET a /api/products/{sku}
                .andExpect(MockMvcResultMatchers.status().isOk()) // Espera un código de estado 200 OK
                .andExpect(MockMvcResultMatchers.jsonPath("$.sku").value("INT-TEST-001")) // Verifica campos en la respuesta JSON
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Integration Test Product"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value(99));

        // Paso 3: Intentar obtener un producto que no existe
        mvc.perform(MockMvcRequestBuilders.get("/api/products/NON-EXISTENT-SKU"))
                .andExpect(MockMvcResultMatchers.status().isNotFound()); // Espera un código de estado 404 Not Found
    }

    @Test
    void listAllProducts_shouldReturnList() throws Exception {
        // Creamos algunos productos primero (la BBDD en memoria se limpia entre tests con Spring Boot Test por defecto)
        String product1 = "{\"sku\":\"LIST-001\",\"name\":\"Item 1\",\"quantity\":10}";
        String product2 = "{\"sku\":\"LIST-002\",\"name\":\"Item 2\",\"quantity\":20}";

        mvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(product1))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(product2))
                .andExpect(MockMvcResultMatchers.status().isCreated());


        // Listar todos los productos (GET)
        mvc.perform(MockMvcRequestBuilders.get("/api/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray()) // Espera una respuesta que sea un array JSON
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2)) // Espera que el array tenga 2 elementos
                // Puedes verificar contenido más específico si es necesario
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].sku").isIn("LIST-001", "LIST-002")) // Verifica que los SKUs esperados estén presentes
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].sku").isIn("LIST-001", "LIST-002"));
    }

}