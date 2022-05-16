package com.swa.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swa.application.domain.ShoppingCart;
import com.swa.application.repository.ShoppingCartRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    public static ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ModelMapper modelMapper;

    private static final Logger log = LoggerFactory.getLogger(CartService.class);

    public ShoppingCart get(String cartNumber){
        return shoppingCartRepository.findById(cartNumber).get();
    }

    public List<ShoppingCart> getAll(){
        return shoppingCartRepository.findAll();
    }

    public void update(ShoppingCart cart){
        shoppingCartRepository.save(cart);
    }

    public void save(ShoppingCart cart){
        shoppingCartRepository.save(cart);
    }

    public void delete(String cartNumber) {
        shoppingCartRepository.findById(cartNumber).ifPresent(cart -> shoppingCartRepository.delete(cart));
    }


}
