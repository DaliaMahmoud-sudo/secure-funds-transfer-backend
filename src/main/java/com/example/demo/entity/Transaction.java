package com.example.demo.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String senderAccountNumber;
    private String recipientAccountNumber;

    private BigDecimal amount;
    private String type; // "IN" or "OUT"


    private LocalDateTime transactionDate;

    //Many transactions → one user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Transaction() {
    }

    public String getSenderAccountNumber() {
        return this.senderAccountNumber;
    }

    public void setSenderAccountNumber(String senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    public String getRecipientAccountNumber() {
        return this.recipientAccountNumber;
    }

    public void setRecipientAccountNumber(String recipientAccountNumber) {
        this.recipientAccountNumber = recipientAccountNumber;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return this.transactionDate;
    }

    //runs before saving
    @PrePersist
    public void setTransactionDate() {
    this.transactionDate = LocalDateTime.now();
}



    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }



  

    public Transaction(String sender, String recipient,BigDecimal amount, String type, User user) {
        this.senderAccountNumber = sender;
        this.recipientAccountNumber = recipient;
        this.amount = amount;
        this.type = type;
        this.user = user;
        this.transactionDate = LocalDateTime.now();
    }

    // getters and setters
}
