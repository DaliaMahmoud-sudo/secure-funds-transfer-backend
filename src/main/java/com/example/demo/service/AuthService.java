package com.example.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

 private final UserRepository userRepo;
 private final PasswordEncoder passwordEncoder;
 private final JwtUtil jwtUtil;

 public LoginResponse login(LoginRequest request){

  User user = userRepo.findByUsername(request.getUsername())
          .orElseThrow(() -> new RuntimeException("User not found"));
System.out.println("Entered password: " + request.getPassword());
System.out.println("DB password: " + user.getPassword());
System.out.println(passwordEncoder.matches(request.getPassword(), user.getPassword()));
  if(!passwordEncoder.matches(request.getPassword(), user.getPassword()))
      throw new RuntimeException("Invalid credentials");

  String token = jwtUtil.generateToken(user.getUsername());

  return new LoginResponse(token);
 }
}
