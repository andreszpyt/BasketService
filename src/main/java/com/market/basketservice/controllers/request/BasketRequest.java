package com.market.basketservice.controllers.request;

import lombok.Builder;

import java.util.List;

@Builder
public record BasketRequest(Long client, List<ProductRequest> products) {
}
