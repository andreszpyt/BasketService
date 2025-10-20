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

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductService productService;

    public List<Basket> getAllBaskets() {
        return basketRepository.findAll();
    }

    public Basket getBasketById(String id) {
        return basketRepository.findById(id).orElse(null);
    }
    public Basket createBasket(BasketRequest basketRequest) {

        basketRepository.findBasketByClientAndStatus(basketRequest.client(), Status.OPEN)
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

    public Basket updateBasket(String id, BasketRequest basketRequest) {
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

        Basket basket = getBasketById(id);
        basket.setProducts(products);
        basket.calculateTotalPrice();
        return basketRepository.save(basket);
    }

    public void deleteBasket(String id) {
        basketRepository.deleteById(id);
    }

    public List<Product> productsMapper(List<ProductRequest> productRequest){
        List<Long> id = new ArrayList<>();
        productRequest.forEach(product -> {id.add(product.id());
        });
        List<ProductResponse> productsResponse = productService.getAllProductsByIds(id);
        

    }


}