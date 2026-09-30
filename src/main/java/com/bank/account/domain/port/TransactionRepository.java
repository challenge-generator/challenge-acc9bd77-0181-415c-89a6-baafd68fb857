package com.bank.account.domain.port;

import com.bank.account.domain.model.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Puerto de salida para operaciones de persistencia de transacciones.
 * Definido por el dominio siguiendo el patrón hexagonal.
 */
public interface TransactionRepository {
    
    Transaction save(Transaction transaction);
    
    Optional<Transaction> findById(UUID id);
    
    List<Transaction> findByAccountId(UUID accountId);
    
    void deleteById(UUID id);
    
    boolean existsById(UUID id);
}