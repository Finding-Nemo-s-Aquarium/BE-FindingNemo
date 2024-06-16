package org.nimo.aquarium.domain.user;

import jakarta.persistence.*;
import lombok.*;
import org.nimo.aquarium.domain.cart.Cart;

@Builder
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 만들어줌
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 만들어줌
@Getter
@Setter
@Entity // DB에 테이블 자동 생성
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true) // 닉네임 중복 안됨
    private String username;

    private String password;
    private String name;
    private String email;
    private String address;
    private String phone;

    @OneToOne(mappedBy = "user")
    private Cart cart;
}
