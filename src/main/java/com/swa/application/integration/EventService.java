package com.swa.application.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.swa.application.domain.ShoppingCart;
import com.swa.application.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    private static final Logger log = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private CartService cartService;

    @KafkaListener(topics = {"CART-CREATED"})
    public void cartCreated(@Payload String cartString) throws JsonProcessingException {
        log.info("Received Kafka message on topic: CART-CREATED with payload: " + cartString);
//        ShoppingCart newCart = objectMapper.readValue(cartString,ShoppingCart.class);
        cartService.save(new ShoppingCart()); //TODO
    }

    @KafkaListener(topics = {"CART-UPDATED"})
    public void cartUpdated(@Payload String cartNumber)  {
        log.info("Received Kafka message on topic: CHECKOUT-FOR-QUERY with payload: " + cartNumber);
        cartService.update(new ShoppingCart()); // TODO
    }

    @KafkaListener(topics = {"CART-DELETED"})
    public void cartDeleted(@Payload String cartNumber)  {
        log.info("Received Kafka message on topic: CHECKOUT-FOR-QUERY with payload: " + cartNumber);
        cartService.delete(cartNumber);
    }
}
