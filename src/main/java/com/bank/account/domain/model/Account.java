package com.bank.account.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public final class Account {
    private final UUID id;
    private final String accountNumber;
    private final String customerId;
    private BigDecimal balance;
    private final AccountStatus status;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private final List<Transaction> transactions;

    public Account(UUID id, String accountNumber, String customerId, BigDecimal initialBalance, AccountStatus status) {
        this.id = Objects.requireNonNull(id, "El ID de la cuenta no puede ser nulo");
        this.accountNumber = Objects.requireNonNull(accountNumber, "El número de cuenta no puede ser nulo");
        this.customerId = Objects.requireNonNull(customerId, "El ID del cliente no puede ser nulo");
        this.balance = Objects.requireNonNull(initialBalance, "El saldo inicial no puede ser nulo");
        this.status = Objects.requireNonNull(status, "El estado de la cuenta no puede ser nulo");
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
        this.transactions = new ArrayList<>();

        if (initialBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
        }
    }

    public UUID getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public void deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser positivo");
        }
        if (this.status != AccountStatus.ACTIVE) {
            throw new IllegalStateException("No se puede depositar en una cuenta no activa");
        }
        this.balance = this.balance.add(amount);
        this.updatedAt = LocalDateTime.now();
    }

    public void withdraw(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser positivo");
        }
        if (this.status != AccountStatus.ACTIVE) {
            throw new IllegalStateException("No se puede retirar de una cuenta no activa");
        }
        if (this.balance.compareTo(amount) < 0) {
            throw new InsufficientFundsException("Saldo insuficiente para realizar el retiro");
        }
        this.balance = this.balance.subtract(amount);
        this.updatedAt = LocalDateTime.now();
    }

    public void addTransaction(Transaction transaction) {
        Objects.requireNonNull(transaction, "La transacción no puede ser nula");
        if (!this.id.equals(transaction.getAccountId())) {
            throw new IllegalArgumentException("La transacción no pertenece a esta cuenta");
        }
        this.transactions.add(transaction);
    }

    public void close() {
        if (this.status == AccountStatus.CLOSED) {
            throw new IllegalStateException("La cuenta ya está cerrada");
        }
        if (this.balance.compareTo(BigDecimal.ZERO) != 0) {
            throw new IllegalStateException("No se puede cerrar una cuenta con saldo diferente de cero");
        }
        this.status = AccountStatus.CLOSED;
        this.updatedAt = LocalDateTime.now();
    }

    public enum AccountStatus {
        ACTIVE, BLOCKED, CLOSED
    }

    public static class InsufficientFundsException extends RuntimeException {
        public InsufficientFundsException(String message) {
            super(message);
        }
    }
}