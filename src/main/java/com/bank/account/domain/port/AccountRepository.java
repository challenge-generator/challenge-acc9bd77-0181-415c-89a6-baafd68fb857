package com.bank.account.domain.port;

import com.bank.account.domain.model.Account;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
    Account save(Account account);

    Optional<Account> findById(UUID id);

    Optional<Account> findByAccountNumber(String accountNumber);

    void deleteById(UUID id);

    boolean existsByAccountNumber(String accountNumber);
}