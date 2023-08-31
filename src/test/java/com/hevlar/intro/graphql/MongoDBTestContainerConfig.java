package com.hevlar.intro.graphql;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MongoDBContainer;

@TestConfiguration(proxyBeanMethods = false)
public class MongoDBTestContainerConfig {
    @Bean
    @ServiceConnection
    MongoDBContainer mongoDBContainer(){
        return new MongoDBContainer("mongo:latest");
    }
}

