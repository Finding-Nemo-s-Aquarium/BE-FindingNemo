package org.nimo.aquarium.service;

import lombok.RequiredArgsConstructor;
import org.nimo.aquarium.domain.user.User;
import org.nimo.aquarium.domain.user.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CartService cartService;
    private final OrderService orderService;
    private final SaleService saleService;

    @Transactional
    public User signup(User user) {

        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole(user.getRole());

        User userEntity = userRepository.save(user);

        if (Objects.equals(userEntity.getRole(), "ROLE_SELLER")) {
            saleService.createSale(user);
        } else if (Objects.equals(user.getRole(), "ROLE_USER")){
            cartService.createCart(user);
            orderService.createOrder(user);
        }

        return userEntity;
    }
}
