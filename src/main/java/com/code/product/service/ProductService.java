package com.code.product.service;

import com.code.product.dtos.ProductRequest;
import com.code.product.dtos.ProductResponse;
import com.code.product.model.Product;
import com.code.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    // Inject bean repository
    private final ProductRepository repository;

    public List<ProductResponse> getAllProducts() {
        List<Product> products = repository.findAll();
        return products.stream().map(this::toProductResponse).collect(Collectors.toList());
    }

    /**
     * Mapper class Product entity to response for user
     * @param product Entity
     * @return Reponse
     */
    private ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
