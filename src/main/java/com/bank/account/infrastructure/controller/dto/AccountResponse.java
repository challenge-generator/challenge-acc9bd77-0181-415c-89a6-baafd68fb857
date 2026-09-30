package com.bank.account.infrastructure.controller.dto;

import com.bank.account.domain.model.Account;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record AccountResponse(
    UUID id,
    String accountNumber,
    String customerId,
    BigDecimal balance,
    String status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public static AccountResponse fromDomain(Account account) {
        return new AccountResponse(
            account.getId(),
            account.getAccountNumber(),
            account.getCustomerId(),
            account.getBalance(),
            account.getStatus().name(),
            account.getCreatedAt(),
            account.getUpdatedAt()
        );
    }

    public static AccountResponse fromDomainWithDefaultDates(Account account) {
        return new AccountResponse(
            account.getId(),
            account.getAccountNumber(),
            account.getCustomerId(),
            account.getBalance(),
            account.getStatus().name(),
            account.getCreatedAt(),
            account.getUpdatedAt() != null ? account.getUpdatedAt() : LocalDateTime.now()
        );
    }
}