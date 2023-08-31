package com.hevlar.intro.graphql.controller;

import com.hevlar.intro.graphql.MongoDBTestContainerConfig;
import com.hevlar.intro.graphql.model.Product;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureWebTestClient
@AutoConfigureHttpGraphQlTester
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
@ContextConfiguration(classes = MongoDBTestContainerConfig.class)
class ProductControllerIntegrationTest {

    @Autowired
    HttpGraphQlTester httpGraphQlTester;

    @Test
    @Order(1)
    void createProduct() {
        Product product = this.httpGraphQlTester
                .document("""
                            mutation {
                                createProduct(product: {
                                    name: "Pilot Pen",
                                    description: "Gel-based ball point pen",
                                    price: 1.50,
                                    category: "Stationery"
                                }){
                                    id
                                    name
                                    description
                                    price
                                    category
                                }
                            }
                        """)
                .execute()
                .errors()
                .verify()
                .path("createProduct")
                .entity(Product.class)
                .get();
        assertThat(product.getId()).isNotNull();
        assertThat(product.getName()).isEqualTo("Pilot Pen");
        assertThat(product.getDescription()).isEqualTo("Gel-based ball point pen");
        assertThat(product.getPrice()).isEqualTo(BigDecimal.valueOf(1.50));
        assertThat(product.getCategory()).isEqualTo("Stationery");
    }
}
