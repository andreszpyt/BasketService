package com.market.basketservice.services;

import com.market.basketservice.controllers.request.BasketRequest;
import com.market.basketservice.controllers.request.ProductRequest;
import com.market.basketservice.controllers.response.ProductResponse;
import com.market.basketservice.entities.Basket;
import com.market.basketservice.entities.Product;
import com.market.basketservice.entities.Status;
import com.market.basketservice.repositories.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductService productService;

    public List<Basket> getAllBaskets() {
        return basketRepository.findAll();
    }

    public Basket getBasketById(String id) {
            return basketRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Basket not found"));
    }

    public Basket createBasket(BasketRequest basketRequest) {

        basketRepository.findBasketByClientAndStatus(basketRequest.client(), Status.OPEN)
                .ifPresent(basket -> {throw new IllegalArgumentException("There is already an open basket");
    });

        List<Product> products = productsMapper(basketRequest.products());

        Basket basket = Basket.builder()
                .client(basketRequest.client())
                .status(Status.OPEN)
                .products(products)
                .build();

        basket.calculateTotalPrice();

        return basketRepository.save(basket);
    }

    public Basket updateBasket(String id, BasketRequest basketRequest) {

        Basket basket = getBasketById(id);

        if(basket == null) {
            throw new IllegalArgumentException("Basket not found");
        }

        List<Product> products = productsMapper(basketRequest.products());

        basket.setProducts(products);
        basket.calculateTotalPrice();
        return basketRepository.save(basket);
    }

    public void deleteBasket(String id) {
        basketRepository.deleteById(id);
    }

    public List<Product> productsMapper(List<ProductRequest> productRequest){
        List<Long> ids = productRequest.stream()
                .map(ProductRequest::id)
                .toList();

        List<ProductResponse> productsResponse = productService.getAllProductsByIds(ids);

        Map<Long, ProductResponse> productResponseMap = productsResponse.stream()
                .collect(Collectors.toMap(ProductResponse::id, Function.identity()));

        return productRequest.stream()
                .filter(request -> productResponseMap.containsKey(request.id()))
                .map(request -> {
                     ProductResponse response = productResponseMap.get(request.id());
                     return Product.builder()
                            .id(response.id())
                            .title(response.title())
                            .price(response.price())
                            .quantity(request.quantity()) // Usa a quantidade do request
                            .build();
                })
                .toList();
    }
}