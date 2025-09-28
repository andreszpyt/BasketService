package com.market.basketservice.services;

import com.market.basketservice.clients.PlatziStoreClient;
import com.market.basketservice.controllers.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final PlatziStoreClient platziStoreClient;

    public List<ProductResponse> getAllProducts() {
        return platziStoreClient.getProducts();
    }

    public ProductResponse getProductById(Long id) {
        return platziStoreClient.getProduct(id);
    }
}
