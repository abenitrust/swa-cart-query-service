package com.swa.application.integration;

import com.swa.application.domain.ShoppingCart;
import com.swa.application.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    private static final Logger log = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private CartService cartService;

    @KafkaListener(topics = "${event.topics.cart-created}")
    public void cartCreated(ShoppingCart cart) {
        log.info("Received Kafka message on topic: CART-CREATED with payload: " + cart);
        cartService.save(cart);
    }

    @KafkaListener(topics = "${event.topics.cart-updated}")
    public void cartUpdated(ShoppingCart cart)  {
        log.info("Received Kafka message on topic: CHECKOUT-FOR-QUERY with payload: " + cart);
        cartService.update(cart);
    }

    @KafkaListener(topics = "${event.topics.cart-deleted}")
    public void cartDeleted(ShoppingCart cart)  {
        log.info("Received Kafka message on topic: CHECKOUT-FOR-QUERY with payload: " + cart);
        cartService.delete(cart.getShoppingCartNumber());
    }
}
