package com.market.basketservice.services;

import com.market.basketservice.clients.PlatziStoreClient;
import com.market.basketservice.controllers.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final PlatziStoreClient platziStoreClient;

    @Cacheable(value = "Products")
    public List<ProductResponse> getAllProducts() {
        log.info("getAllProducts");
        return platziStoreClient.getProducts();
    }

    @Cacheable(value = "Product", key = "#productId")
    public ProductResponse getProductById(Long productId) {
        log.info("getProductById : {}", productId);
        return platziStoreClient.getProduct(productId);
    }

    @Cacheable(value = "Products")
    public List<ProductResponse> getAllProductsByIds(List<Long> productIds) {
        log.info("getAllProductsByIds : {}", productIds);
        return platziStoreClient.getProductsByIds(productIds);
    }
}
