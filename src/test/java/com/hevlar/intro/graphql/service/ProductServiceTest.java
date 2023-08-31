package com.hevlar.intro.graphql.service;

import com.hevlar.intro.graphql.model.Product;
import com.hevlar.intro.graphql.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @Test
    void createProduct() {
        Product product = new Product(
                "Pilot Pen",
                "Gel-based ball point pen",
                BigDecimal.valueOf(1.50),
                "Stationery");
        given(productRepository.save(any())).willReturn(Mono.just(product));
        StepVerifier.create(productService.createProduct(product))
                .expectNext(product)
                .verifyComplete();
    }

    @Test
    void updateProduct() {
        Product product = new Product(
                "Pilot G1 Pen",
                "Best selling gel-based ball point pen",
                BigDecimal.valueOf(1.80),
                "Stationery");
        given(productRepository.findById("1")).willReturn(Mono.just(product));
        given(productRepository.save(any())).willReturn(Mono.just(product));
        StepVerifier.create(productService.updateProduct("1", product))
                .expectNext(product)
                .verifyComplete();
    }

    @Test
    void updateProduct_throwWhenProductNotFound() {
        Product product = new Product(
                "Pilot G1 Pen",
                "Best selling gel-based ball point pen",
                BigDecimal.valueOf(1.80),
                "Stationery");
        given(productRepository.findById("1")).willReturn(Mono.empty());
        StepVerifier.create(productService.updateProduct("1", product))
                .expectErrorMessage("Product not found")
                .verify();
    }
}
