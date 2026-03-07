package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

 private final UserRepository userRepo;
 private final AccountRepository accountRepo;

 @GetMapping
 public Account getAccount(Authentication authentication){

 String username = authentication.getName();

 User user = userRepo.findByUsername(username)
                     .orElseThrow(() -> new RuntimeException("User not found"));

 return accountRepo.findByUser(user);
 }
}
