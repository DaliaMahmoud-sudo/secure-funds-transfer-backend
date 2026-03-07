package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TransferRequest;
import com.example.demo.service.TransferService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/transfers")
@RequiredArgsConstructor
public class TransferController {

 private final TransferService transferService;

 @PostMapping
 public String transfer(@RequestBody TransferRequest request,
                        Authentication authentication){

  String username = authentication.getName();

  transferService.transfer(
          username,
          request.getRecipientAccountNumber(),
          request.getAmount()
  );

  return "Transfer successful";
 }
}
