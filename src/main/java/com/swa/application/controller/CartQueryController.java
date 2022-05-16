package com.swa.application.controller;

import com.swa.application.domain.ShoppingCart;
import com.swa.application.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/carts")
public class CartQueryController {

    @Autowired
    private CartService cartService;

    private static final Logger log = LoggerFactory.getLogger(CartQueryController.class);

    @GetMapping("/{cartNumber}")
    public ResponseEntity<?> getCart(@PathVariable String cartNumber){
        log.info("GET request for /carts/" + cartNumber);
        var shoppingCart = cartService.get(cartNumber);
        if(shoppingCart != null) {
            return new ResponseEntity<ShoppingCart>(shoppingCart, HttpStatus.OK);
        }else{
            log.error("COULDN'T GET CARTS");
            return new ResponseEntity<String>("COULDN'T GET CART", HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping()
    public ResponseEntity<?> getAll(){
        log.info("GET request for /carts");
        var shoppingCarts = cartService.getAll();
        if(shoppingCarts !=null){
            return new ResponseEntity<>(shoppingCarts, HttpStatus.OK);
        }else{
            log.error("COULDN'T GET CARTS");
            return new ResponseEntity<>("COULDN'T GET CARTS", HttpStatus.BAD_REQUEST);
        }

    }

}
