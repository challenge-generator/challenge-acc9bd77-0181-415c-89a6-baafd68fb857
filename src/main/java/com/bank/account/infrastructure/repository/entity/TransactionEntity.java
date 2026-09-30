package com.bank.account.infrastructure.repository.entity;

import com.bank.account.domain.model.Transaction;
import com.bank.account.domain.model.Transaction.TransactionType;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions", indexes = {
    @Index(name = "idx_transaction_account_id", columnList = "account_id"),
    @Index(name = "idx_transaction_created_at", columnList = "created_at"),
    @Index(name = "idx_transaction_reference", columnList = "reference", unique = true)
})
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "account_id", nullable = false)
    private UUID accountId;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TransactionType type;

    @Column(length = 255)
    private String description;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false, unique = true, length = 50)
    private String reference;

    public TransactionEntity() {
    }

    public TransactionEntity(UUID accountId, BigDecimal amount, TransactionType type, 
                             String description, String reference) {
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.reference = reference;
        this.createdAt = LocalDateTime.now();
    }

    public static TransactionEntity fromDomain(Transaction transaction) {
        TransactionEntity entity = new TransactionEntity();
        entity.id = transaction.getId();
        entity.accountId = transaction.getAccountId();
        entity.amount = transaction.getAmount();
        entity.type = transaction.getType();
        entity.description = transaction.getDescription();
        entity.createdAt = transaction.getCreatedAt();
        entity.reference = transaction.getReference();
        return entity;
    }

    public Transaction toDomain() {
        return new Transaction(
            this.id,
            this.accountId,
            this.amount,
            this.type,
            this.description,
            this.reference
        );
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}