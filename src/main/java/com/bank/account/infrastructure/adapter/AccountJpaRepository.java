package com.bank.account.infrastructure.adapter;

import com.bank.account.domain.model.Account;
import com.bank.account.domain.model.AccountStatus;
import com.bank.account.domain.model.Transaction;
import com.bank.account.domain.port.AccountRepository;
import com.bank.account.infrastructure.repository.entity.AccountEntity;
import com.bank.account.infrastructure.repository.entity.TransactionEntity;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Adaptador de infraestructura que implementa el puerto AccountRepository
 * usando Spring Data JPA para la persistencia.
 */
@Repository
public class AccountJpaRepository implements AccountRepository {

    private final JpaAccountOperations accountOperations;
    private final JpaTransactionOperations transactionOperations;

    public AccountJpaRepository(JpaAccountOperations accountOperations, 
                                 JpaTransactionOperations transactionOperations) {
        this.accountOperations = accountOperations;
        this.transactionOperations = transactionOperations;
    }

    @Override
    public Account save(Account account) {
        AccountEntity entity = toEntity(account);
        AccountEntity savedEntity = accountOperations.save(entity);
        
        if (account.getTransactions() != null && !account.getTransactions().isEmpty()) {
            List<TransactionEntity> transactionEntities = account.getTransactions().stream()
                .map(this::toTransactionEntity)
                .collect(Collectors.toList());
            transactionOperations.saveAll(transactionEntities);
        }
        
        return toDomain(savedEntity);
    }

    @Override
    public Optional<Account> findById(UUID id) {
        return accountOperations.findById(id)
            .map(this::toDomain);
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return accountOperations.findByAccountNumber(accountNumber)
            .map(this::toDomain);
    }

    @Override
    public void deleteById(UUID id) {
        accountOperations.deleteById(id);
    }

    @Override
    public boolean existsByAccountNumber(String accountNumber) {
        return accountOperations.existsByAccountNumber(accountNumber);
    }

    private AccountEntity toEntity(Account account) {
        AccountEntity entity = new AccountEntity();
        entity.setId(account.getId());
        entity.setAccountNumber(account.getAccountNumber());
        entity.setCustomerId(account.getCustomerId());
        entity.setBalance(account.getBalance());
        entity.setStatus(account.getStatus().name());
        entity.setCreatedAt(account.getCreatedAt());
        entity.setUpdatedAt(account.getUpdatedAt());
        return entity;
    }

    private Account toDomain(AccountEntity entity) {
        List<Transaction> transactions = new ArrayList<>();
        
        Account account = new Account(
            entity.getId(),
            entity.getAccountNumber(),
            entity.getCustomerId(),
            entity.getBalance(),
            AccountStatus.valueOf(entity.getStatus())
        );
        
        return account;
    }

    private TransactionEntity toTransactionEntity(Transaction transaction) {
        TransactionEntity entity = new TransactionEntity();
        entity.setId(transaction.getId());
        entity.setAccountId(transaction.getAccountId());
        entity.setAmount(transaction.getAmount());
        entity.setType(transaction.getType().name());
        entity.setDescription(transaction.getDescription());
        entity.setCreatedAt(transaction.getCreatedAt());
        entity.setReference(transaction.getReference());
        return entity;
    }

    public interface JpaAccountOperations {
        AccountEntity save(AccountEntity entity);
        Optional<AccountEntity> findById(UUID id);
        Optional<AccountEntity> findByAccountNumber(String accountNumber);
        void deleteById(UUID id);
        boolean existsByAccountNumber(String accountNumber);
    }

    public interface JpaTransactionOperations {
        List<TransactionEntity> saveAll(List<TransactionEntity> entities);
        List<TransactionEntity> findByAccountId(UUID accountId);
    }
}