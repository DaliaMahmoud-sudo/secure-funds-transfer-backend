package com.example.demo.repository;

import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties.Apiversion.Use;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Account;
import com.example.demo.entity.User;

import jakarta.persistence.LockModeType;

public interface AccountRepository extends JpaRepository<Account,Long> {

 //Custom Methods
//  @Lock(LockModeType.PESSIMISTIC_WRITE)
// @Query("SELECT a FROM Account a WHERE a.user = :user")
// Account findByUserForUpdate(@Param("user") User user);
 Account findByUser(User user);

 Account findByAccountNumber(String accountNumber);

}
