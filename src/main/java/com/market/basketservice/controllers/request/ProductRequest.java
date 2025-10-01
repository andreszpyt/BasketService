package com.market.basketservice.controllers.request;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductRequest(Long id, int quantity) {
}
