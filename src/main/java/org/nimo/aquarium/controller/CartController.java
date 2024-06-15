/*
package org.nimo.aquarium.controller;

import org.nimo.aquarium.service.CartService;
import org.nimo.aquarium.web.dto.auth.CartSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/cart")
public class CartController {

    //@Autowired
    private CartService cartService;

    // 장바구니 요약 정보 조회
    @GetMapping("/user/cart/{id}/summary")
    public ResponseEntity<CartSummary> getCartSummary(@PathVariable("userId") int userId, @PathVariable String id) {
        CartSummary cartSummary = cartService.getCartSummary(userId);
        return ResponseEntity.ok(cartSummary);
    }
}
*/
