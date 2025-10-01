package com.market.basketservice.services;

import com.market.basketservice.controllers.request.BasketRequest;
import com.market.basketservice.controllers.response.ProductResponse;
import com.market.basketservice.entities.Basket;
import com.market.basketservice.entities.Product;
import com.market.basketservice.entities.Status;
import com.market.basketservice.repositories.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductService productService;

    public Basket createBasket(BasketRequest basketRequest) {

        basketRepository.findBasketByClientIdAndStatus(basketRequest.client(), Status.OPEN)
                .ifPresent(basket -> {throw new IllegalArgumentException("There is already an open basket");
    });



        List<Product> products = new ArrayList<>();
        basketRequest.products().forEach(product -> {
            ProductResponse productResponse = productService.getProductById(product.id());
            products.add(Product.builder()
                    .id(productResponse.id())
                    .title(productResponse.title())
                    .price(productResponse.price())
                    .quantity(product.quantity())
                    .build());
        });

        Basket basket = Basket.builder()
                .client(basketRequest.client())
                .status(Status.OPEN)
                .products(products)
                .build();

        basket.calculateTotalPrice();

        return basketRepository.save(basket);
    }
}