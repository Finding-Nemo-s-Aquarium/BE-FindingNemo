package org.nimo.aquarium.domain.cartitem;

import jakarta.persistence.*;
import lombok.*;
import org.nimo.aquarium.domain.cart.Cart;
import org.nimo.aquarium.domain.item.Item;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;

    public static CartItem createCartItem(Cart cart, Item item, int amount){
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setItem(item);
        cartItem.setCount(amount);

        return cartItem;
    }

    public void addCount(int count) { this.count += count; }
}
