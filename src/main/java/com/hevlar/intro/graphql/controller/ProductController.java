package com.hevlar.intro.graphql.controller;

import com.hevlar.intro.graphql.model.Product;
import com.hevlar.intro.graphql.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
@Slf4j
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @MutationMapping
    public Mono<Product> createProduct(@Argument Product product){
        log.info("createProduct: " + product.toString());
        return productService.createProduct(product);
    }
}