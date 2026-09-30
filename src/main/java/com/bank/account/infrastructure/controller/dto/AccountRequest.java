package com.bank.account.infrastructure.controller.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class AccountRequest {

    @NotBlank(message = "El número de cuenta es obligatorio")
    @Size(min = 10, max = 20, message = "El número de cuenta debe tener entre 10 y 20 caracteres")
    private String accountNumber;

    @NotBlank(message = "El ID del cliente es obligatorio")
    @Size(min = 1, max = 50, message = "El ID del cliente debe tener entre 1 y 50 caracteres")
    private String customerId;

    @NotNull(message = "El saldo inicial es obligatorio")
    @DecimalMin(value = "0.0", message = "El saldo inicial no puede ser negativo")
    private BigDecimal initialBalance;

    @NotNull(message = "El estado de la cuenta es obligatorio")
    private String status;

    public AccountRequest() {
    }

    public AccountRequest(String accountNumber, String customerId, BigDecimal initialBalance, String status) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.initialBalance = initialBalance;
        this.status = status;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}