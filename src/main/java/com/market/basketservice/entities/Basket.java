package com.market.basketservice.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "basket")
public class Basket {
    @Id
    private String id;
    private Long client;
    private BigDecimal totalPrice;
    private List<Product> products;
    private Status status;

    public void calculateTotalPrice() {
        this.totalPrice = products.stream()
                .map(Product -> Product.getPrice().multiply(new BigDecimal(Product.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
