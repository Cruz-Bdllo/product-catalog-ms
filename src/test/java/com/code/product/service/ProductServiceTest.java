package com.code.product.service;

import com.code.product.dtos.ProductResponse;
import com.code.product.model.Product;
import com.code.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Testcontainers
class ProductServiceTest {
    /*
        ~ STEPS FOR CREATE A CONTAINER TEST
        1- Define MongoDbContainer property with @Container tag
        2- Create method to generate initial value with @DynamicPropertySource,
           this method have DynamicPropertyRegistry param and with this add getReplicaSetUrl from application.properties
    */

//    @Container
//    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.0.10");

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

//    @DynamicPropertySource
//    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
//        //mongoDBContainer.start();
//        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
//    }

    @Test
    void shouldGetAllProductsFromMongoDB() {
        Mockito.when(productRepository.findAll()).thenReturn(dummyResponse());
        List<ProductResponse> response = productService.getAllProducts();
        assertEquals(response.size(), 1);
        assertEquals(response.get(0).getName(), "laptop");
    }

    @Test
    void shouldEmptyListWhenNotHaveRecords() {
        Mockito.when(productRepository.findAll()).thenReturn(Collections.emptyList());
        assertEquals(productService.getAllProducts().size(), 0);
    }

    private List<Product> dummyResponse() {
        return Arrays.asList(
                new Product("1", "laptop", "Mi laptop", BigDecimal.valueOf(1245))
        );
    }
}