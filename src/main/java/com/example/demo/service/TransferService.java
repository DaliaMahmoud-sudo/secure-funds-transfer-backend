package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferService {

 private final UserRepository userRepo;
 private final AccountRepository accountRepo;

 @Transactional
 public void transfer(String username,String recipientAccount,Double amount){

  User senderUser = userRepo.findByUsername(username).orElseThrow();

  Account sender = accountRepo.findByUser(senderUser);

  Account receiver = accountRepo.findByAccountNumber(recipientAccount);

  if(receiver == null)
   throw new RuntimeException("Recipient not found");

  if(sender.getBalance() < amount)
   throw new RuntimeException("Insufficient funds");

  sender.setBalance(sender.getBalance() - amount);
  receiver.setBalance(receiver.getBalance() + amount);

  accountRepo.save(sender);
  accountRepo.save(receiver);
 }
}