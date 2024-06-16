package org.nimo.aquarium.domain.cart;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.nimo.aquarium.domain.cartitem.CartItem;
import org.nimo.aquarium.domain.user.User;

import java.util.List;

@Entity
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItem> items;
}
