package org.nimo.aquarium.controller;

import lombok.RequiredArgsConstructor;
import org.nimo.aquarium.domain.user.User;
import org.nimo.aquarium.service.AuthService;
import org.nimo.aquarium.web.dto.auth.SigninDto;
import org.nimo.aquarium.web.dto.auth.SignupDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    /*@GetMapping("/signin")
    public String SigninForm() {
        return "signin";
    }*/

    /*@GetMapping("/signup")
    public String SignupForm() {
        return "signup";
    }*/

    /*@PostMapping("/signup")
    public String signup(SignupDto signupDto) {
        // User에 signupDto 넣음
        User user = signupDto.toEntity();

        User userEntity = authService.signup(user);
        System.out.println(userEntity);

        return "signin";
    }*/

    /*@PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninDto signinDto) {
        try {
            String token = authService.signin(signinDto);
            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + token).body("Signin successful");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            // 로그에 예외 메시지 기록
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during signin: " + e.getMessage());
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        if (user.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password cannot be null");
        }
        try {
            User userEntity = authService.signup(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(userEntity);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during signup");
        }
    }*/

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupDto signupDto) {
        if (signupDto.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password cannot be null");
        }
        try {
            User user = signupDto.toEntity();
            User userEntity = authService.signup(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(userEntity);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            // 로그에 예외 메시지 기록
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during signup: " + e.getMessage());
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninDto signinDto) {
        try {
            String token = authService.signin(signinDto);
            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + token).body("Signin successful");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            // 로그에 예외 메시지 기록
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during signin: " + e.getMessage());
        }
    }
}
