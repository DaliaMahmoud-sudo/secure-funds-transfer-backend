package com.example.demo.service;
import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Account;
import com.example.demo.entity.Transaction;
import com.example.demo.entity.User;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class TransferService {

 private final UserRepository userRepo;
 private final AccountRepository accountRepo;
 private final TransactionRepository transactionRepository;
//ensure ACID
 @Transactional
 public void transfer(String username,String recipientAccount,BigDecimal amount){

  User senderUser = userRepo.findByUsername(username).orElseThrow();

  Account sender = accountRepo.findByUser(senderUser);

  Account receiver = accountRepo.findByAccountNumber(recipientAccount);

  if(receiver == null)
   throw new RuntimeException("Recipient not found");
//check balance
if (sender.getBalance().compareTo(amount) < 0) {
    throw new RuntimeException("Insufficient funds");
}

sender.setBalance(sender.getBalance().subtract(amount));
receiver.setBalance(receiver.getBalance().add(amount));

  accountRepo.save(sender);
  accountRepo.save(receiver);
// OUT transaction for sender
Transaction senderTransaction = new Transaction(
        sender.getAccountNumber(),
        receiver.getAccountNumber(),
        amount,
        "OUT",
        senderUser
);

transactionRepository.save(senderTransaction);


// IN transaction for receiver
Transaction receiverTransaction = new Transaction(
        sender.getAccountNumber(),
        receiver.getAccountNumber(),
        amount,
        "IN",
        receiver.getUser()
);

transactionRepository.save(receiverTransaction);
 
 }
}