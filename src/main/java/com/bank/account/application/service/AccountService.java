package com.bank.account.application.service;

import com.bank.account.domain.model.Account;
import com.bank.account.domain.model.AccountStatus;
import com.bank.account.domain.port.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(String accountNumber, String customerId, BigDecimal initialBalance, AccountStatus status) {
        logger.info("Creando cuenta para cliente: {} con número de cuenta: {}", customerId, accountNumber);
        
        if (accountRepository.existsByAccountNumber(accountNumber)) {
            logger.warn("Ya existe una cuenta con el número: {}", accountNumber);
            throw new IllegalArgumentException("Ya existe una cuenta con el número de cuenta especificado");
        }

        Account newAccount = new Account(
            UUID.randomUUID(),
            accountNumber,
            customerId,
            initialBalance,
            status != null ? status : AccountStatus.ACTIVE
        );

        Account savedAccount = accountRepository.save(newAccount);
        logger.info("Cuenta creada exitosamente con ID: {}", savedAccount.getId());
        return savedAccount;
    }

    public Optional<Account> getAccountById(UUID accountId) {
        logger.debug("Consultando cuenta con ID: {}", accountId);
        Optional<Account> account = accountRepository.findById(accountId);
        
        if (account.isEmpty()) {
            logger.warn("Cuenta no encontrada con ID: {}", accountId);
        }
        
        return account;
    }

    public Optional<Account> getAccountByAccountNumber(String accountNumber) {
        logger.debug("Consultando cuenta con número: {}", accountNumber);
        return accountRepository.findByAccountNumber(accountNumber);
    }

    public List<Account> getAllAccounts() {
        logger.info("Listando todas las cuentas");
        return accountRepository.findAll();
    }

    public Account updateAccountBalance(UUID accountId, BigDecimal newBalance) {
        logger.info("Actualizando balance de cuenta {} a {}", accountId, newBalance);
        
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada: " + accountId));

        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El balance no puede ser negativo");
        }

        return account;
    }

    public void deleteAccount(UUID accountId) {
        logger.info("Eliminando cuenta con ID: {}", accountId);
        
        if (!accountRepository.existsByAccountNumber(accountRepository.findById(accountId)
                .map(Account::getAccountNumber)
                .orElse(""))) {
            throw new IllegalArgumentException("Cuenta no encontrada: " + accountId);
        }
        
        accountRepository.deleteById(accountId);
        logger.info("Cuenta eliminada exitosamente");
    }

    public Account deposit(UUID accountId, BigDecimal amount) {
        logger.info("Depósito de {} a cuenta {}", amount, accountId);
        
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada: " + accountId));

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del depósito debe ser positivo");
        }

        account.deposit(amount);
        return accountRepository.save(account);
    }

    public Account withdraw(UUID accountId, BigDecimal amount) {
        logger.info("Retiro de {} de cuenta {}", amount, accountId);
        
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada: " + accountId));

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del retiro debe ser positivo");
        }

        if (account.getBalance().compareTo(amount) < 0) {
            throw new IllegalStateException("Fondos insuficientes para realizar el retiro");
        }

        account.withdraw(amount);
        return accountRepository.save(account);
    }

    public Account closeAccount(UUID accountId) {
        logger.info("Cerrando cuenta con ID: {}", accountId);
        
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada: " + accountId));

        if (account.getStatus() == AccountStatus.CLOSED) {
            throw new IllegalStateException("La cuenta ya está cerrada");
        }

        if (account.getBalance().compareTo(BigDecimal.ZERO) > 0) {
            throw new IllegalStateException("No se puede cerrar una cuenta con saldo positivo");
        }

        account.close();
        return accountRepository.save(account);
    }

    public boolean existsByAccountNumber(String accountNumber) {
        return accountRepository.existsByAccountNumber(accountNumber);
    }
}