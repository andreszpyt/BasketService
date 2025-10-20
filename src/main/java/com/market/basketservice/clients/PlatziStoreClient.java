package com.market.basketservice.clients;

import com.market.basketservice.controllers.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PlatziStoreClient", url = "${basket.client.platzi}")
public interface PlatziStoreClient {

    @GetMapping("/products")
    List<ProductResponse> getProducts();

    @GetMapping("/products/{id}")
    ProductResponse getProduct(@PathVariable Long id);

    List<ProductResponse> getProductsByIds(List<Long> productIds);

}
