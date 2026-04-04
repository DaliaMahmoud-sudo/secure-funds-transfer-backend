package com.example.demo.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Account {

 @Id
 //auto increment
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private String accountNumber;

@Column(nullable = false)
private BigDecimal balance = BigDecimal.ZERO;

 @OneToOne
 //foreign key in Account table
 @JoinColumn(name="user_id")
 private User user;
}