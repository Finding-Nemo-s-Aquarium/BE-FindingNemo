package org.nimo.aquarium.domain.cartitem;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.nimo.aquarium.domain.cart.Cart;
import org.nimo.aquarium.domain.item.Item;

@Entity
@Getter
@Setter
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int amount;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
