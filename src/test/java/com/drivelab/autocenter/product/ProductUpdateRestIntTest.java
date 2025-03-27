package com.drivelab.autocenter.product;

import com.drivelab.autocenter.domain.product.ProductCreationCommand;
import com.drivelab.autocenter.domain.product.ProductCreationUseCase;
import com.drivelab.autocenter.domain.product.ProductName;
import com.drivelab.autocenter.domain.product.Sku;
import com.drivelab.autocenter.mockbuilders.ProductUpdateRequestBodyMb;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ProductUpdateRestIntTest {

    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>(DockerImageName.parse("mysql:8.0.40"));

    @LocalServerPort
    int port;

    WebTestClient client;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ProductCreationUseCase useCase;

    @BeforeEach
    void setUp() {
        client = WebTestClient.bindToServer().baseUrl("http://localhost:" + port).build();
    }

    @DynamicPropertySource
    static void mysqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }

    @Test
    void update_product_successfully() throws JsonProcessingException {
        
        var product = useCase.newProduct(ProductCreationCommand.Builder.builder()
                .name(new ProductName("Test Product"))
                .sku(new Sku("AGK94585-Yellow"))
                .build());

        var requestBody = new ProductUpdateRequestBodyMb()
                .setName("Test Product Changed")
                .setSku("AGK94585-Blue")
                .defaultRequest();
        var json = objectMapper.writeValueAsString(requestBody);

        client.put().uri("/v1/products/{id}", product.publicId().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(json)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isEqualTo(product.publicId().toString())
                .jsonPath("$.sku").isEqualTo(requestBody.getSku())
                .jsonPath("$.name").isEqualTo(requestBody.getName());
    }
}
