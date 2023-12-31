package com.hevlar.intro.graphql.controller;

import com.hevlar.intro.graphql.model.Product;
import com.hevlar.intro.graphql.service.ProductService;
import graphql.GraphQLError;
import graphql.schema.DataFetchingEnvironment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
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

    @QueryMapping
    public Flux<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @QueryMapping
    public Mono<Product> getProduct(@Argument String id){
        return productService.getProduct(id);
    }

    @MutationMapping
    public Mono<Product> updateProduct(@Argument String id, @Argument Product updatedProduct){
        return productService.updateProduct(id, updatedProduct);
    }

    @MutationMapping
    public Mono<Boolean> deleteProduct(@Argument String id){
        return productService.deleteProduct(id);
    }

    @GraphQlExceptionHandler
    public GraphQLError handle(@NonNull Throwable ex, @NonNull DataFetchingEnvironment environment){
        return GraphQLError
                .newError()
                .errorType(ErrorType.BAD_REQUEST)
                .message(ex.getMessage())
                .path(environment.getExecutionStepInfo().getPath())
                .location(environment.getField().getSourceLocation())
                .build();
    }
}
