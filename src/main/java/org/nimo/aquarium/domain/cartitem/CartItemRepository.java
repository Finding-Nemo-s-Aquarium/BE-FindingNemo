package org.nimo.aquarium.domain.cartitem;

import org.nimo.aquarium.domain.cart.Cart;
import org.nimo.aquarium.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartAndItem(Cart cart, Item item);
}
