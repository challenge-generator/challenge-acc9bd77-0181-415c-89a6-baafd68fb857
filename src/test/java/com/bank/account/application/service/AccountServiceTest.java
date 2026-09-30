package com.bank.account.application.service;


import com.bank.account.domain.model.InsufficientFundsException;
import com.bank.account.domain.model.Account;
import com.bank.account.domain.model.Account.AccountStatus;
import com.bank.account.domain.model.Transaction;
import com.bank.account.domain.model.Transaction.TransactionType;
import com.bank.account.domain.port.AccountRepository;
import com.bank.account.domain.port.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias de AccountService - Patrones GRASP")
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private AccountService accountService;

    private Account testAccount;
    private UUID accountId;
    private String customerId;

    @BeforeEach
    void setUp() {
        accountId = UUID.randomUUID();
        customerId = "CUST-001";
        testAccount = new Account(
            accountId,
            "ACC-12345",
            customerId,
            new BigDecimal("1000.00"),
            AccountStatus.ACTIVE
        );
    }

    @Test
    @DisplayName("Crear cuenta - Patrón Creador: AccountService crea la cuenta y las transacciones iniciales")
    void createAccount_ShouldCreateAccountWithInitialTransaction() {
        String accountNumber = "ACC-54321";
        BigDecimal initialBalance = new BigDecimal("5000.00");

        when(accountRepository.existsByAccountNumber(accountNumber)).thenReturn(false);
        when(accountRepository.save(any(Account.class))).thenAnswer(invocation -> {
            Account saved = invocation.getArgument(0);
            return saved;
        });
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> {
            Transaction saved = invocation.getArgument(0);
            return saved;
        });

        Account result = accountService.createAccount(customerId, accountNumber, initialBalance);

        assertNotNull(result);
        assertEquals(accountNumber, result.getAccountNumber());
        assertEquals(customerId, result.getCustomerId());
        assertEquals(initialBalance, result.getBalance());
        assertEquals(AccountStatus.ACTIVE, result.getStatus());

        ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
        verify(accountRepository).save(accountCaptor.capture());
        Account savedAccount = accountCaptor.getValue();
        assertEquals(1, savedAccount.getTransactions().size());

        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    @DisplayName("Crear cuenta - Fallo cuando el número de cuenta ya existe")
    void createAccount_ShouldThrowException_WhenAccountNumberExists() {
        String existingAccountNumber = "ACC-99999";
        when(accountRepository.existsByAccountNumber(existingAccountNumber)).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> {
            accountService.createAccount(customerId, existingAccountNumber, new BigDecimal("1000.00"));
        });

        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    @DisplayName("Consultar cuenta por ID - Patrón Experto en Información: AccountService tiene la información completa")
    void getAccountById_ShouldReturnAccountWithTransactions() {
        Transaction tx1 = new Transaction(
            UUID.randomUUID(),
            accountId,
            new BigDecimal("500.00"),
            TransactionType.DEPOSIT,
            "Depósito inicial",
            "REF-001"
        );
        Transaction tx2 = new Transaction(
            UUID.randomUUID(),
            accountId,
            new BigDecimal("200.00"),
            TransactionType.WITHDRAWAL,
            "Retiro cajero",
            "REF-002"
        );
        testAccount.addTransaction(tx1);
        testAccount.addTransaction(tx2);

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));

        Optional<Account> result = accountService.getAccountById(accountId);

        assertTrue(result.isPresent());
        assertEquals(accountId, result.get().getId());
        assertEquals(2, result.get().getTransactions().size());
        verify(accountRepository).findById(accountId);
    }

    @Test
    @DisplayName("Consultar cuenta por número - Patrón Experto en Información")
    void getAccountByNumber_ShouldReturnAccount_WhenExists() {
        when(accountRepository.findByAccountNumber(testAccount.getAccountNumber()))
            .thenReturn(Optional.of(testAccount));

        Optional<Account> result = accountService.getAccountByNumber(testAccount.getAccountNumber());

        assertTrue(result.isPresent());
        assertEquals(testAccount.getAccountNumber(), result.get().getAccountNumber());
    }

    @Test
    @DisplayName("Depositar - Patrón Experto en Información: AccountService coordina depósito y transacción")
    void deposit_ShouldUpdateBalanceAndCreateTransaction() {
        BigDecimal depositAmount = new BigDecimal("1500.00");
        BigDecimal initialBalance = testAccount.getBalance();

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));
        when(accountRepository.save(any(Account.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Account result = accountService.deposit(accountId, depositAmount, "Depósito nomina");

        assertEquals(initialBalance.add(depositAmount), result.getBalance());
        assertEquals(1, result.getTransactions().size());
        assertEquals(TransactionType.DEPOSIT, result.getTransactions().get(0).getType());

        verify(accountRepository).save(any(Account.class));
        verify(transactionRepository).save(any(Transaction.class));
    }

    @Test
    @DisplayName("Depositar - Fallo cuando la cuenta no existe")
    void deposit_ShouldThrowException_WhenAccountNotFound() {
        UUID nonExistentId = UUID.randomUUID();
        when(accountRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            accountService.deposit(nonExistentId, new BigDecimal("100.00"), "test");
        });
    }

    @Test
    @DisplayName("Retirar - Patrón Experto en Información: verifica fondos suficientes")
    void withdraw_ShouldUpdateBalanceAndCreateTransaction_WhenSufficientFunds() {
        BigDecimal withdrawAmount = new BigDecimal("300.00");
        BigDecimal initialBalance = testAccount.getBalance();

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));
        when(accountRepository.save(any(Account.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Account result = accountService.withdraw(accountId, withdrawAmount, "Retiro cajero");

        assertEquals(initialBalance.subtract(withdrawAmount), result.getBalance());
        assertEquals(1, result.getTransactions().size());
        assertEquals(TransactionType.WITHDRAWAL, result.getTransactions().get(0).getType());

        verify(accountRepository).save(any(Account.class));
        verify(transactionRepository).save(any(Transaction.class));
    }

    @Test
    @DisplayName("Retirar - Fallo por fondos insuficientes")
    void withdraw_ShouldThrowException_WhenInsufficientFunds() {
        BigDecimal largeWithdrawal = new BigDecimal("5000.00");
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));

        assertThrows(Account.InsufficientFundsException.class, () -> {
            accountService.withdraw(accountId, largeWithdrawal, "test");
        });

        verify(accountRepository, never()).save(any(Account.class));
        verify(transactionRepository, never()).save(any(Transaction.class));
    }

    @Test
    @DisplayName("Cerrar cuenta - Cambia estado a CLOSED")
    void closeAccount_ShouldChangeStatusToClosed() {
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));
        when(accountRepository.save(any(Account.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Account result = accountService.closeAccount(accountId);

        assertEquals(AccountStatus.CLOSED, result.getStatus());
        verify(accountRepository).save(testAccount);
    }

    @Test
    @DisplayName("Cerrar cuenta - Fallo cuando la cuenta no existe")
    void closeAccount_ShouldThrowException_WhenAccountNotFound() {
        UUID nonExistentId = UUID.randomUUID();
        when(accountRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            accountService.closeAccount(nonExistentId);
        });
    }
}