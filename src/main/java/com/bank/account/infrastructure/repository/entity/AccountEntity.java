package com.bank.account.infrastructure.repository.entity;

import com.bank.account.domain.model.Account;
import com.bank.account.domain.model.AccountStatus;
import com.bank.account.domain.model.Transaction;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "accounts", indexes = {
    @Index(name = "idx_account_number", columnList = "account_number", unique = true),
    @Index(name = "idx_customer_id", columnList = "customer_id"),
    @Index(name = "idx_status", columnList = "status"),
    @Index(name = "idx_created_at", columnList = "created_at")
})
public class AccountEntity {

    private static final Logger logger = LoggerFactory.getLogger(AccountEntity.class);

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "account_number", nullable = false, unique = true, length = 20)
    private String accountNumber;

    @Column(name = "customer_id", nullable = false, length = 50)
    private String customerId;

    @Column(name = "balance", nullable = false, precision = 19, scale = 4)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private AccountStatus status;

    @Column(name = "account_type", length = 30)
    private String accountType;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "accountId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<TransactionEntity> transactions = new ArrayList<>();

    public AccountEntity() {
    }

    public static AccountEntity fromDomain(Account account) {
        if (account == null) {
            logger.warn("Attempted to convert null Account to AccountEntity");
            return null;
        }
        
        AccountEntity entity = new AccountEntity();
        entity.setId(account.getId());
        entity.setAccountNumber(account.getAccountNumber());
        entity.setCustomerId(account.getCustomerId());
        entity.setBalance(account.getBalance());
        entity.setStatus(account.getStatus());
        entity.setCreatedAt(account.getCreatedAt());
        entity.setUpdatedAt(account.getUpdatedAt());
        
        if (account.getTransactions() != null && !account.getTransactions().isEmpty()) {
            List<TransactionEntity> transactionEntities = new ArrayList<>();
            for (Transaction transaction : account.getTransactions()) {
                TransactionEntity te = TransactionEntity.fromDomain(transaction);
                if (te != null) {
                    te.setAccountId(account.getId());
                    transactionEntities.add(te);
                }
            }
            entity.setTransactions(transactionEntities);
        }
        
        logger.debug("Converted Account {} to AccountEntity", account.getId());
        return entity;
    }

    public Account toDomain() {
        List<Transaction> transactionList = new ArrayList<>();
        if (transactions != null) {
            for (TransactionEntity te : transactions) {
                Transaction t = te.toDomain();
                if (t != null) {
                    transactionList.add(t);
                }
            }
        }
        
        Account account = new Account(
            this.id,
            this.accountNumber,
            this.customerId,
            this.balance,
            this.status
        );
        
        logger.debug("Converted AccountEntity {} to Account domain", this.id);
        return account;
    }

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
        if (this.updatedAt == null) {
            this.updatedAt = LocalDateTime.now();
        }
        logger.debug("AccountEntity pre-persist triggered for account: {}", this.accountNumber);
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
        logger.debug("AccountEntity pre-update triggered for account: {}", this.accountNumber);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionEntity> transactions) {
        this.transactions = transactions;
    }
}