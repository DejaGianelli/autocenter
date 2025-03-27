package com.drivelab.autocenter.purchase;

import com.drivelab.autocenter.domain.Cnpj;
import com.drivelab.autocenter.domain.purchase.PurchaseCreationCommand;
import com.drivelab.autocenter.domain.supplier.Supplier;
import com.drivelab.autocenter.domain.supplier.SupplierCreationCommand;
import com.drivelab.autocenter.domain.supplier.SupplierCreationUseCase;
import com.drivelab.autocenter.domain.supplier.SupplierName;
import com.drivelab.autocenter.mockbuilders.PurchaseCreationRequestBodyMb;
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

@ActiveProfiles("test")
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PurchaseCreationRestIntTest {

    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>(DockerImageName.parse("mysql:8.0.40"));

    @LocalServerPort
    int port;

    WebTestClient client;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    SupplierCreationUseCase useCase;

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
    void create_purchase_successfully() throws JsonProcessingException {

        Supplier supplier = useCase.newSupplier(SupplierCreationCommand.Builder.builder()
                .cnpj(new Cnpj("02744280000178"))
                .supplierName(new SupplierName("Some Company Limited"))
                .build());

        var requestBody = new PurchaseCreationRequestBodyMb()
                .defaultRequest()
                .setSupplierId(supplier.publicId().toString());
        var json = objectMapper.writeValueAsString(requestBody);

        client.post().uri("/v1/purchases")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(json)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.total").isEqualTo(0.00)
                .jsonPath("$.supplier.id").isEqualTo(requestBody.getSupplierId())
                .jsonPath("$.supplier.name").isEqualTo(supplier.name().toString());
    }
}
