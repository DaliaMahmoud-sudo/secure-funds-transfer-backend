package com.example.demo.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initUsers(UserRepository userRepository,
                                PasswordEncoder passwordEncoder,
                                AccountRepository accountRepository) {

        return args -> {

            // Create user if not exists
            User user = userRepository.findByUsername("ghida")
                    .orElseGet(() -> {

                        User newUser = new User();
                        newUser.setUsername("ghida");
                        newUser.setPassword(passwordEncoder.encode("123456"));

                        userRepository.save(newUser);

                        System.out.println("User created!");

                        return newUser;
                    });

            // Create account if not exists
            if (accountRepository.findByUser(user) == null) {

                Account account = new Account();
                account.setUser(user);
                account.setAccountNumber("ACC" + (1000 + accountRepository.count() + 1));
                account.setBalance(2000.0);

                accountRepository.save(account);

                System.out.println("Account created!");
            }

        };
    }
}