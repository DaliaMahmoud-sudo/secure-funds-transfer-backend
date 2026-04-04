package com.example.demo.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TransferRequest {
@NotBlank(message = "Recipient account is required")
 private String recipientAccountNumber;

  @NotNull
@Positive(message = "Amount must be positive")
 private BigDecimal amount;

}
