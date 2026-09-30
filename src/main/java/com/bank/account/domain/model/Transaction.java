package com.bank.account.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public final class Transaction {
    private final UUID id;
    private final UUID accountId;
    private final BigDecimal amount;
    private final TransactionType type;
    private final String description;
    private final LocalDateTime createdAt;
    private final String reference;

    public Transaction(UUID id, UUID accountId, BigDecimal amount, TransactionType type, String description, String reference) {
        this.id = Objects.requireNonNull(id, "El ID de la transacción no puede ser nulo");
        this.accountId = Objects.requireNonNull(accountId, "El ID de la cuenta no puede ser nulo");
        this.amount = Objects.requireNonNull(amount, "El monto no puede ser nulo");
        this.type = Objects.requireNonNull(type, "El tipo de transacción no puede ser nulo");
        this.description = description;
        this.reference = Objects.requireNonNull(reference, "La referencia no puede ser nula");
        this.createdAt = LocalDateTime.now();

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }
    }

    public UUID getId() {
        return id;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getReference() {
        return reference;
    }

    public enum TransactionType {
        DEPOSIT, WITHDRAWAL, TRANSFER_IN, TRANSFER_OUT
    }
}