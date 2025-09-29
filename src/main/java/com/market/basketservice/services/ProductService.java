package com.market.basketservice.services;

import com.market.basketservice.clients.PlatziStoreClient;
import com.market.basketservice.controllers.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final PlatziStoreClient platziStoreClient;

    @Cacheable(value = "Products")
    public List<ProductResponse> getAllProducts() {
        return platziStoreClient.getProducts();
    }

    @Cacheable(value = "Product", key = "productId")
    public ProductResponse getProductById(Long productId) {
        return platziStoreClient.getProduct(productId);
    }
}
