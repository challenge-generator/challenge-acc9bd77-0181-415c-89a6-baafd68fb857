package com.bank.account.infrastructure.controller;


import com.bank.account.domain.model.Account;
import com.bank.account.application.service.AccountService;
import com.bank.account.infrastructure.controller.dto.AccountRequest;
import com.bank.account.infrastructure.controller.dto.AccountResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountRequest request) {
        logger.info("Received request to create account for customer: {}", request.customerId());
        try {
            var account = accountService.createAccount(
                request.customerId(),
                request.initialBalance(),
                request.accountType()
            );
            logger.info("Account created successfully with ID: {}", account.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(AccountResponse.fromDomain(account));
        } catch (IllegalArgumentException e) {
            logger.warn("Failed to create account: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error creating account", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create account");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable UUID id) {
        logger.debug("Fetching account by ID: {}", id);
        return accountService.findById(id)
            .map(account -> {
                logger.debug("Account found: {}", account.getAccountNumber());
                return ResponseEntity.ok(AccountResponse.fromDomain(account));
            })
            .orElseGet(() -> {
                logger.warn("Account not found: {}", id);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
            });
    }

    @GetMapping("/number/{accountNumber}")
    public ResponseEntity<AccountResponse> getAccountByNumber(@PathVariable String accountNumber) {
        logger.debug("Fetching account by number: {}", accountNumber);
        return accountService.findByAccountNumber(accountNumber)
            .map(account -> ResponseEntity.ok(AccountResponse.fromDomain(account)))
            .orElseGet(() -> {
                logger.warn("Account not found with number: {}", accountNumber);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
            });
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllAccounts(
            @RequestParam(required = false) String customerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        logger.debug("Fetching accounts - customerId: {}, page: {}, size: {}", customerId, page, size);
        
        List<AccountResponse> accounts;
        if (customerId != null && !customerId.isEmpty()) {
            accounts = accountService.findByCustomerId(customerId)
                .stream()
                .map(AccountResponse::fromDomain)
                .collect(Collectors.toList());
        } else {
            accounts = accountService.findAll()
                .stream()
                .map(AccountResponse::fromDomain)
                .collect(Collectors.toList());
        }
        
        logger.info("Retrieved {} accounts", accounts.size());
        return ResponseEntity.ok(accounts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponse> updateAccount(
            @PathVariable UUID id,
            @Valid @RequestBody AccountRequest request) {
        logger.info("Updating account: {}", id);
        try {
            var updated = accountService.updateAccount(id, request.initialBalance(), request.accountType());
            logger.info("Account updated successfully: {}", id);
            return ResponseEntity.ok(AccountResponse.fromDomain(updated));
        } catch (IllegalArgumentException e) {
            logger.warn("Failed to update account {}: {}", id, e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            logger.error("Error updating account {}", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update account");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable UUID id) {
        logger.info("Deleting account: {}", id);
        if (accountService.existsById(id)) {
            accountService.deleteAccount(id);
            logger.info("Account deleted successfully: {}", id);
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("Attempted to delete non-existent account: {}", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        }
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<AccountResponse> deposit(
            @PathVariable UUID id,
            @RequestParam BigDecimal amount) {
        logger.info("Deposit request for account {}: amount {}", id, amount);
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Amount must be positive");
        }
        try {
            var account = accountService.deposit(id, amount);
            logger.info("Deposit successful for account {}", id);
            return ResponseEntity.ok(AccountResponse.fromDomain(account));
        } catch (IllegalArgumentException e) {
            logger.warn("Deposit failed for account {}: {}", id, e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            logger.error("Error processing deposit for account {}", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Deposit failed");
        }
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<AccountResponse> withdraw(
            @PathVariable UUID id,
            @RequestParam BigDecimal amount) {
        logger.info("Withdraw request for account {}: amount {}", id, amount);
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Amount must be positive");
        }
        try {
            var account = accountService.withdraw(id, amount);
            logger.info("Withdraw successful for account {}", id);
            return ResponseEntity.ok(AccountResponse.fromDomain(account));
        } catch (IllegalArgumentException e) {
            logger.warn("Withdraw failed for account {}: {}", id, e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            logger.error("Error processing withdraw for account {}", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Withdraw failed");
        }
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable UUID id) {
        logger.debug("Fetching balance for account: {}", id);
        return accountService.findById(id)
            .map(account -> ResponseEntity.ok(account.getBalance()))
            .orElseGet(() -> {
                logger.warn("Account not found for balance check: {}", id);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
            });
    }
}