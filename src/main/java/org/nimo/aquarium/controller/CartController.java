package org.nimo.aquarium.controller;

import org.nimo.aquarium.service.CartService;
import org.nimo.aquarium.web.dto.auth.CartDto;
import org.nimo.aquarium.web.dto.auth.CartItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping(value = "/{userId}/items", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addItemsToCart(@PathVariable int userId, @RequestBody List<CartItemDto> items) {
        cartService.addItemsToCart(userId, items); // 서비스 메서드 호출
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CartDto getCart(@PathVariable int userId) {
        logger.info("Fetching cart for user ID: {}", userId);
        CartDto cartDto = cartService.getCartByUserId(userId);
        if (cartDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found");
        }
        logger.info("Cart DTO: {}", cartDto);
        return cartDto;
    }
}