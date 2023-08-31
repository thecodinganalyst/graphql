package com.hevlar.intro.graphql.repository;

import com.hevlar.intro.graphql.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

@DataMongoTest
@Testcontainers
@ContextConfiguration(classes = {ReactiveMongoTestConfiguration.class})
class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    void save(){
        Product product = new Product(
                "Pilot Pen",
                "Gel-based ball point pen",
                BigDecimal.valueOf(10.00),
                "Stationery");
        StepVerifier.create(productRepository.save(product))
                .expectNext(product)
                .verifyComplete();
    }
}
