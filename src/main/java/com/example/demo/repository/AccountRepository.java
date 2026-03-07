package com.example.demo.repository;

import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties.Apiversion.Use;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Account;
import com.example.demo.entity.User;

public interface AccountRepository extends JpaRepository<Account,Long> {

 Account findByUser(User user);

 Account findByAccountNumber(String accountNumber);

}
