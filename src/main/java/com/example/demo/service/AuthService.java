package com.example.demo.service;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

 private final UserRepository userRepo;
 private final PasswordEncoder passwordEncoder;
 private final JwtUtil jwtUtil;
 
    private final AccountRepository accountRepository;

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



  

    public void register(RegisterRequest request) {

        // Check if username exists
        if (userRepo.findByUsername(request.getUsername()).isPresent()) {throw new RuntimeException("Username already exists");}
        

        // Create user
        User user = new User();
        user.setUsername(request.getUsername());

        // Encrypt password
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepo.save(user);

        // Create account automatically
        Account account = new Account();
        account.setUser(user);

        account.setAccountNumber("ACC" + (1000 + accountRepository.count() + 1));

        account.setBalance(0.0);

        accountRepository.save(account);

    
    }
}
