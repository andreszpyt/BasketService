package com.market.basketservice.controllers.request;

import com.market.basketservice.entities.PaymentMethod;

import java.io.Serializable;

public record PaymentRequest(PaymentMethod paymentMethod) implements Serializable {
}
