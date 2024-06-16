package org.nimo.aquarium.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.nimo.aquarium.domain.user.User;
import org.nimo.aquarium.domain.user.UserRepository;
import org.nimo.aquarium.web.dto.auth.SigninDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.Date;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    //private final CartService cartService;
    @Transactional
    public User signup(User user) {
        // 비밀번호가 null인지 확인
        if (user.getPassword() == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }

        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);

        User userEntity = userRepository.save(user);

        return userEntity;
    }

    public String signin(SigninDto signinDto) {
        try {
            User user = userRepository.findByUsername(signinDto.getUsername())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

            if (!bCryptPasswordEncoder.matches(signinDto.getPassword(), user.getPassword())) {
                throw new IllegalArgumentException("Invalid username or password");
            }

            return Jwts.builder()
                    .setSubject(user.getUsername())
                    .setExpiration(new Date(System.currentTimeMillis() + 864_000_000)) // 10 days
                    .signWith(key)
                    .compact();
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred during signin", e);
        }
    }
}
