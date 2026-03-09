package com.example.demo.controller;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

 private final AuthService authService;

 @PostMapping("/login")
 public LoginResponse login(@RequestBody LoginRequest request){
    System.out.println(request.getUsername());
  return authService.login(request);

 }
 @PostMapping("/register")
public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

    try {

        authService.register(request);

        return ResponseEntity.ok(
                Map.of("message", "User registered successfully")
        );

    } catch (RuntimeException e) {

        return ResponseEntity
                .badRequest()
                .body(Map.of("message", e.getMessage()));
    }
}
}
