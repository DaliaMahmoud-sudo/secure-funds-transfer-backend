package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
//enforce null handling returns Optional.empty()
 Optional<User> findByUsername(String username);

}
