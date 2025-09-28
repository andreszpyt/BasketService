package com.market.basketservice.controllers.response;

import java.math.BigDecimal;

public record ProductResponse(Long id, String title, BigDecimal price) {
}
