package org.nimo.aquarium.controller;

import lombok.RequiredArgsConstructor;
import org.nimo.aquarium.config.auth.PrincipalDetails;
import org.nimo.aquarium.domain.cart.Cart;
import org.nimo.aquarium.domain.cartitem.CartItem;
import org.nimo.aquarium.domain.item.Item;
import org.nimo.aquarium.domain.user.User;
import org.nimo.aquarium.service.*;
import org.nimo.aquarium.web.dto.auth.CartSummary;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserPageController {

    private final UserPageService userPageService;
    private final CartService cartService;
    private final ItemService itemService;

/*    // 유저 페이지 접속
    @GetMapping("/user/{id}")
    public String userPage(@PathVariable("id") Integer id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        // 로그인이 되어있는 유저의 id와 유저 페이지에 접속하는 id가 같아야 함
        if (principalDetails.getUser().getId() == id) {

            model.addAttribute("user", userPageService.findUser(id));

            return "/user/userPage";
        } else {
            return "redirect:/main";
        }
    }

    // 회원 정보 수정
    @GetMapping("/user/modify/{id}")
    public String userModify(@PathVariable("id") Integer id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        // 로그인이 되어있는 유저의 id와 수정페이지에 접속하는 id가 같아야 함
        if (principalDetails.getUser().getId() == id) {

            model.addAttribute("user", userPageService.findUser(id));

            return "/userModify";
        } else {
            return "redirect:/main";
        }

    }

    // 수정 실행
    @PostMapping("/user/update/{id}")
    public String userUpdate(@PathVariable("id") Integer id, User user) {

        userPageService.userModify(user);

        return "redirect:/user/{id}";
    }

    // 장바구니 페이지 접속
    @GetMapping("/user/cart/{id}")
    public String userCartPage(@PathVariable("id") Integer id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        // 로그인이 되어있는 유저의 id와 장바구니에 접속하는 id가 같아야 함
        if (principalDetails.getUser().getId() == id) {

            User user = userPageService.findUser(id);
            // 로그인 되어 있는 유저에 해당하는 장바구니 가져오기
            Cart userCart = user.getCart();

            // 장바구니에 들어있는 아이템 모두 가져오기
            List<CartItem> cartItemList = cartService.allUserCartView(userCart);

            // 장바구니에 들어있는 상품들의 총 가격
            int totalPrice = 0;
            for (CartItem cartitem : cartItemList) {
                totalPrice += cartitem.getCount() * cartitem.getItem().getPrice();
            }

            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("totalCount", userCart.getCount());
            model.addAttribute("cartItems", cartItemList);
            model.addAttribute("user", userPageService.findUser(id));

            return "/user/userCart";
        }
        // 로그인 id와 장바구니 접속 id가 같지 않는 경우
        else {
            return "redirect:/main";
        }
    }*/

    // 장바구니에 물건 넣기
    @PostMapping("/user/cart/{id}/{itemId}")
    public ResponseEntity<Map<String, Object>> addCartItem(@PathVariable("id") Integer id, @PathVariable("itemId") Integer itemId,
                                                           @RequestParam(value = "amount", defaultValue = "0") Integer amount) {

        User user = userPageService.findUser(id);
        Item item = itemService.itemView(itemId);

        cartService.addCart(user, item, amount);

        // 아이템 상세 정보를 포함한 응답 반환
        Map<String, Object> response = new HashMap<>();
        response.put("itemId", item.getId());
        response.put("name", item.getName());
        response.put("amount", amount);
        response.put("price", item.getPrice());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/cart/{id}/summary")
    public ResponseEntity<CartSummary> getCartSummary(@PathVariable("id") int id) {
        CartSummary cartSummary = cartService.getCartSummary(id);
        return ResponseEntity.ok(cartSummary);
    }

    /*// 장바구니에서 물건 삭제
    // 삭제하고 남은 상품의 총 개수
    @GetMapping("/user/cart/{id}/{cartItemId}/delete")
    public String deleteCartItem(@PathVariable("id") Integer id, @PathVariable("cartItemId") Integer itemId, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        // 로그인 유저 id와 장바구니 유저의 id가 같아야 함
        if (principalDetails.getUser().getId() == id) {
            // itemId로 장바구니 상품 찾기
            CartItem cartItem = cartService.findCartItemById(itemId);

            // 해당 유저의 카트 찾기
            Cart userCart = cartService.findUserCart(id);

            // 장바구니 전체 수량 감소
            userCart.setCount(userCart.getCount()-cartItem.getCount());

            // 장바구니 물건 삭제
            cartService.cartItemDelete(itemId);

            // 해당 유저의 장바구니 상품들
            List<CartItem> cartItemList = cartService.allUserCartView(userCart);

            // 총 가격 += 수량 * 가격
            int totalPrice = 0;
            for (CartItem cartitem : cartItemList) {
                totalPrice += cartitem.getCount() * cartitem.getItem().getPrice();
            }

            // 총 개수 += 수량
            //int totalCount = 0;
            //for (CartItem cartitem : cartItemList) {
            //    totalCount += cartitem.getCount();
            //}


            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("totalCount", userCart.getCount());
            model.addAttribute("cartItems", cartItemList);
            model.addAttribute("user", userPageService.findUser(id));

            return "/user/userCart";
        }
        // 로그인 id와 장바구니 삭제하려는 유저의 id가 같지 않는 경우
        else {
            return "redirect:/main";
        }
    }*/

}