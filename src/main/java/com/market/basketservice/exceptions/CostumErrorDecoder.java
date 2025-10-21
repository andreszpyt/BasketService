package com.market.basketservice.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.coyote.BadRequestException;

public class CostumErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response){
        return switch (response.status()) {
            case 404 -> new DataNotFoundException("Product not found");
            case 400 -> new BadRequestException("Bad request received from external service");
            default -> new Exception("Exception while processing request");
        };
    }

}
