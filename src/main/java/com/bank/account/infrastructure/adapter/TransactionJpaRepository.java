package com.bank.account.infrastructure.adapter;

import com.bank.account.domain.model.Transaction;
import com.bank.account.domain.model.TransactionType;
import com.bank.account.domain.port.TransactionRepository;
import com.bank.account.infrastructure.repository.entity.TransactionEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Adaptador de infraestructura que implementa el puerto TransactionRepository
 * usando Spring Data JPA para la persistencia de transacciones.
 */
@Repository
public class TransactionJpaRepository implements TransactionRepository {

    private final TransactionJpaRepository.JpaTransactionOperations operations;

    public TransactionJpaRepository(TransactionJpaRepository.JpaTransactionOperations operations) {
        this.operations = operations;
    }

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity entity = toEntity(transaction);
        TransactionEntity savedEntity = operations.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<Transaction> findById(UUID id) {
        return operations.findById(id)
            .map(this::toDomain);
    }

    @Override
    public List<Transaction> findByAccountId(UUID accountId) {
        return operations.findByAccountId(accountId).stream()
            .map(this::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        operations.deleteById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return operations.existsById(id);
    }

    private TransactionEntity toEntity(Transaction transaction) {
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

    private Transaction toDomain(TransactionEntity entity) {
        return new Transaction(
            entity.getId(),
            entity.getAccountId(),
            entity.getAmount(),
            TransactionType.valueOf(entity.getType()),
            entity.getDescription(),
            entity.getReference()
        );
    }

    public interface JpaTransactionOperations {
        TransactionEntity save(TransactionEntity entity);
        Optional<TransactionEntity> findById(UUID id);
        List<TransactionEntity> findByAccountId(UUID accountId);
        void deleteById(UUID id);
        boolean existsById(UUID id);
    }
}