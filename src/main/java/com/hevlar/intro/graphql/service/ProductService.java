package com.hevlar.intro.graphql.service;

import com.hevlar.intro.graphql.model.Product;
import com.hevlar.intro.graphql.repository.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Mono<Product> createProduct(Product product){
        return productRepository.save(product);
    }

    public Flux<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
