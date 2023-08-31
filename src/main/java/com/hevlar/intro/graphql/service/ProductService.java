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

    public Mono<Product> getProduct(String id){
        return productRepository.findById(id);
    }

    public Mono<Product> updateProduct(String id, Product updatedProduct){
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Product not found")))
                .flatMap(product -> {
                    product.setName(updatedProduct.getName());
                    product.setDescription(updatedProduct.getDescription());
                    product.setPrice(updatedProduct.getPrice());
                    product.setCategory(updatedProduct.getCategory());
                    return productRepository.save(product);
                });
    }
}
