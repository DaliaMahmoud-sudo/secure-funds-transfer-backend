package com.example.demo.dto;

import lombok.Data;

@Data
public class TransferRequest {

 private String recipientAccountNumber;
 private Double amount;

}
