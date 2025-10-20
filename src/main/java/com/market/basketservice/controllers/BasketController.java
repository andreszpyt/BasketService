package com.market.basketservice.controllers;

import com.market.basketservice.controllers.request.BasketRequest;
import com.market.basketservice.entities.Basket;
import com.market.basketservice.services.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;


    @GetMapping
    public ResponseEntity<List<Basket>> getAllBaskets() {
        return ResponseEntity.ok(basketService.getAllBaskets());
    }

    @GetMapping("{id}")
    public ResponseEntity<Basket> getBasketById(@PathVariable String id) {
        return ResponseEntity.ok(basketService.getBasketById(id));
    }

    @PostMapping
    public ResponseEntity<Basket> createBasket(@RequestBody BasketRequest basketRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(basketService.createBasket(basketRequest));
    }

    @PutMapping("{id}")
    public ResponseEntity<Basket> updateBasket(@PathVariable String id, @RequestBody BasketRequest basketRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(basketService.updateBasket(id, basketRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBasket(@PathVariable String id) {
        basketService.deleteBasket(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
