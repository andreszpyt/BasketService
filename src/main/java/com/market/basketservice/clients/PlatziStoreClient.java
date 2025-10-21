package com.market.basketservice.clients;

import com.market.basketservice.controllers.response.ProductResponse;
import com.market.basketservice.exceptions.CostumErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "PlatziStoreClient", url = "${basket.client.platzi}", configuration = {CostumErrorDecoder.class})
public interface PlatziStoreClient {

    @GetMapping("/products")
    List<ProductResponse> getProducts();

    @GetMapping("/products/{id}")
    ProductResponse getProduct(@PathVariable Long id);

    @GetMapping("/products")
    List<ProductResponse> getProductsByIds(@RequestParam("ids") List<Long> productIds);
}
