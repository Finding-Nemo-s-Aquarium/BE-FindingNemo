package org.nimo.aquarium.service;

import lombok.RequiredArgsConstructor;
import org.nimo.aquarium.domain.cart.Cart;
import org.nimo.aquarium.domain.cart.CartRepository;
import org.nimo.aquarium.domain.cartitem.CartItem;
import org.nimo.aquarium.domain.cartitem.CartItemRepository;
import org.nimo.aquarium.domain.item.Item;
import org.nimo.aquarium.domain.item.ItemRepository;
import org.nimo.aquarium.domain.user.User;
import org.nimo.aquarium.domain.user.UserRepository;
import org.nimo.aquarium.web.dto.auth.CartDto;
import org.nimo.aquarium.web.dto.auth.CartItemDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

    /*@Transactional
    public void addItemsToCart(int userId, List<CartItemDto> items) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findById(userId));
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user = userOptional.get();

        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart = cartRepository.save(cart);
        }

        for (CartItemDto itemDto : items) {
            Item item = itemRepository.findByName(itemDto.getName());
            if (item != null) {
                CartItem cartItem = cartItemRepository.findByCartAndItem(cart, item);
                if (cartItem != null) {
                    // 이미 장바구니에 있는 경우 업데이트
                    cartItem.setAmount(cartItem.getAmount() + itemDto.getAmount());
                    cartItemRepository.save(cartItem);
                } else {
                    // 장바구니에 없는 경우 새로 추가
                    cartItem = new CartItem();
                    cartItem.setItem(item);
                    cartItem.setAmount(itemDto.getAmount());
                    cartItem.setCart(cart);
                    cartItemRepository.save(cartItem);
                }
            }
        }
    }

    public CartDto getCartByUserId(int userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            logger.warn("Cart not found for user ID: {}", userId);
            return null;
        }

        List<CartItemDto> items = cart.getItems().stream()
                .map(cartItem -> new CartItemDto(cartItem.getItem().getName(), cartItem.getAmount(), cartItem.getAmount() * cartItem.getItem().getPrice()))
                .collect(Collectors.toList());

        double total = items.stream()
                .mapToDouble(CartItemDto::getPrice)
                .sum();

        logger.info("Cart items: {}", items);
        logger.info("Cart total: {}", total);

        return new CartDto(items, total);
    }*/
    @Transactional
    public void addItemsToCart(int userId, List<CartItemDto> items) {
        logger.info("Adding items to cart for user ID: {}", userId);

        Optional<User> userOptional = Optional.ofNullable(userRepository.findById(userId));
        if (userOptional.isEmpty()) {
            logger.error("User not found: {}", userId);
            throw new RuntimeException("User not found");
        }
        User user = userOptional.get();

        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart = cartRepository.save(cart);
            logger.info("Created new cart for user ID: {}", userId);
        }

        for (CartItemDto itemDto : items) {
            Item item = itemRepository.findByName(itemDto.getName());
            if (item != null) {
                CartItem cartItem = cartItemRepository.findByCartAndItem(cart, item);
                if (cartItem != null) {
                    // 이미 장바구니에 있는 경우 업데이트
                    cartItem.setAmount(cartItem.getAmount() + itemDto.getAmount());
                    cartItemRepository.save(cartItem);
                    logger.info("Updated cart item: {} with amount: {}", cartItem.getId(), cartItem.getAmount());
                } else {
                    // 장바구니에 없는 경우 새로 추가
                    cartItem = new CartItem();
                    cartItem.setItem(item);
                    cartItem.setAmount(itemDto.getAmount());
                    cartItem.setCart(cart);
                    cartItemRepository.save(cartItem);
                    logger.info("Added new cart item: {} with amount: {}", cartItem.getId(), cartItem.getAmount());
                }
            } else {
                logger.error("Item not found: {}", itemDto.getName());
            }
        }
    }

    public CartDto getCartByUserId(int userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            logger.warn("Cart not found for user ID: {}", userId);
            return null;
        }

        List<CartItemDto> items = cart.getItems().stream()
                .map(cartItem -> {
                    Item item = cartItem.getItem();
                    return new CartItemDto(
                            item.getName(),
                            cartItem.getAmount(),
                            item.getPrice(),
                            item.getImgUrl() // imgUrl 필드 추가
                    );
                })
                .collect(Collectors.toList());

        double total = items.stream()
                .mapToDouble(item -> item.getPrice() * item.getAmount())
                .sum();

        logger.info("Cart items: {}", items);
        logger.info("Cart total: {}", total);

        return new CartDto(items, total);
    }
}
