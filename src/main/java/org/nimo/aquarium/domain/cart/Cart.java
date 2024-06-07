package org.nimo.aquarium.domain.cart;

import jakarta.persistence.*;
import lombok.*;
import org.nimo.aquarium.domain.cartitem.CartItem;
import org.nimo.aquarium.domain.user.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private int count;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems = new ArrayList<>();

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate createDate;

    @PrePersist
    public void createDate(){ this.createDate = LocalDate.now(); }

    public static Cart createCart(User user){
        Cart cart = new Cart();
        cart.setCount(0);
        cart.setUser(user);

        return cart;
    }
}
