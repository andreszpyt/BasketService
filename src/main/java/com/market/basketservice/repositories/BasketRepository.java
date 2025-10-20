package com.market.basketservice.repositories;

import com.market.basketservice.entities.Basket;
import com.market.basketservice.entities.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasketRepository extends MongoRepository<Basket,String> {

    Optional<Basket> findBasketByClientAndStatus(Long clientId, Status status);

}
