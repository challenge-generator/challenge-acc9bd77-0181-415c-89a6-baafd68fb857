package com.bank.account.infrastructure.controller;


import com.bank.account.domain.model.InsufficientFundsException;
import com.bank.account.application.service.AccountService;
import com.bank.account.domain.model.Account;
import com.bank.account.domain.model.Account.AccountStatus;
import com.bank.account.infrastructure.controller.dto.AccountRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.web.reactive.function.BodyInserters.*;

@WebFluxTest(AccountController.class)
@DisplayName("Pruebas de integración del controlador de cuentas")
class AccountControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private AccountService accountService;

    private Account testAccount;
    private UUID accountId;

    @BeforeEach
    void setUp() {
        accountId = UUID.randomUUID();
        testAccount = new Account(
            accountId,
            "ACC-12345",
            "CUST-001",
            new BigDecimal("1000.00"),
            AccountStatus.ACTIVE
        );
    }

    @Test
    @DisplayName("POST /api/accounts - Crear cuenta exitosamente")
    void createAccount_ShouldReturn201_WhenSuccessful() {
        AccountRequest request = new AccountRequest(
            "CUST-001",
            "ACC-54321",
            new BigDecimal("5000.00")
        );

        when(accountService.createAccount(anyString(), anyString(), any(BigDecimal.class)))
            .thenReturn(testAccount);

        webTestClient.post()
            .uri("/api/accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .body(fromValue(request))
            .exchange()
            .expectStatus().isCreated()
            .expectBody()
            .jsonPath("$.accountNumber").isEqualTo("ACC-12345")
            .jsonPath("$.customerId").isEqualTo("CUST-001")
            .jsonPath("$.balance").isEqualTo(1000.00);
    }

    @Test
    @DisplayName("POST /api/accounts - Retorna 400 cuando el número de cuenta ya existe")
    void createAccount_ShouldReturn400_WhenAccountNumberExists() {
        AccountRequest request = new AccountRequest(
            "CUST-001",
            "ACC-DUPLICATE",
            new BigDecimal("1000.00")
        );

        when(accountService.createAccount(anyString(), anyString(), any(BigDecimal.class)))
            .thenThrow(new IllegalArgumentException("El número de cuenta ya existe"));

        webTestClient.post()
            .uri("/api/accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .body(fromValue(request))
            .exchange()
            .expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("GET /api/accounts/{id} - Consultar cuenta por ID")
    void getAccountById_ShouldReturnAccount_WhenExists() {
        when(accountService.getAccountById(accountId)).thenReturn(Optional.of(testAccount));

        webTestClient.get()
            .uri("/api/accounts/{id}", accountId)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.id").isEqualTo(accountId.toString())
            .jsonPath("$.accountNumber").isEqualTo("ACC-12345");
    }

    @Test
    @DisplayName("GET /api/accounts/{id} - Retorna 404 cuando no existe")
    void getAccountById_ShouldReturn404_WhenNotFound() {
        UUID nonExistentId = UUID.randomUUID();
        when(accountService.getAccountById(nonExistentId)).thenReturn(Optional.empty());

        webTestClient.get()
            .uri("/api/accounts/{id}", nonExistentId)
            .exchange()
            .expectStatus().isNotFound();
    }

    @Test
    @DisplayName("GET /api/accounts/number/{accountNumber} - Consultar por número de cuenta")
    void getAccountByNumber_ShouldReturnAccount_WhenExists() {
        when(accountService.getAccountByNumber("ACC-12345"))
            .thenReturn(Optional.of(testAccount));

        webTestClient.get()
            .uri("/api/accounts/number/{accountNumber}", "ACC-12345")
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.accountNumber").isEqualTo("ACC-12345");
    }

    @Test
    @DisplayName("POST /api/accounts/{id}/deposit - Realizar depósito")
    void deposit_ShouldReturn200_WhenSuccessful() {
        BigDecimal depositAmount = new BigDecimal("500.00");
        Account updatedAccount = new Account(
            accountId,
            "ACC-12345",
            "CUST-001",
            new BigDecimal("1500.00"),
            AccountStatus.ACTIVE
        );

        when(accountService.deposit(eq(accountId), any(BigDecimal.class), anyString()))
            .thenReturn(updatedAccount);

        webTestClient.post()
            .uri("/api/accounts/{id}/deposit", accountId)
            .contentType(MediaType.APPLICATION_JSON)
            .body(fromValue("{\"amount\": 500.00, \"description\": \"Depósito nomina\"}"))
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.balance").isEqualTo(1500.00);
    }

    @Test
    @DisplayName("POST /api/accounts/{id}/withdraw - Realizar retiro")
    void withdraw_ShouldReturn200_WhenSuccessful() {
        BigDecimal withdrawAmount = new BigDecimal("300.00");
        Account updatedAccount = new Account(
            accountId,
            "ACC-12345",
            "CUST-001",
            new BigDecimal("700.00"),
            AccountStatus.ACTIVE
        );

        when(accountService.withdraw(eq(accountId), any(BigDecimal.class), anyString()))
            .thenReturn(updatedAccount);

        webTestClient.post()
            .uri("/api/accounts/{id}/withdraw", accountId)
            .contentType(MediaType.APPLICATION_JSON)
            .body(fromValue("{\"amount\": 300.00, \"description\": \"Retiro cajero\"}"))
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.balance").isEqualTo(700.00);
    }

    @Test
    @DisplayName("POST /api/accounts/{id}/withdraw - Retorna 400 por fondos insuficientes")
    void withdraw_ShouldReturn400_WhenInsufficientFunds() {
        when(accountService.withdraw(eq(accountId), any(BigDecimal.class), anyString()))
            .thenThrow(new Account.InsufficientFundsException("Fondos insuficientes"));

        webTestClient.post()
            .uri("/api/accounts/{id}/withdraw", accountId)
            .contentType(MediaType.APPLICATION_JSON)
            .body(fromValue("{\"amount\": 5000.00, \"description\": \"test\"}"))
            .exchange()
            .expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("DELETE /api/accounts/{id} - Cerrar cuenta")
    void closeAccount_ShouldReturn200_WhenSuccessful() {
        Account closedAccount = new Account(
            accountId,
            "ACC-12345",
            "CUST-001",
            BigDecimal.ZERO,
            AccountStatus.CLOSED
        );

        when(accountService.closeAccount(accountId)).thenReturn(closedAccount);

        webTestClient.delete()
            .uri("/api/accounts/{id}", accountId)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.status").isEqualTo("CLOSED");
    }

    @Test
    @DisplayName("DELETE /api/accounts/{id} - Retorna 404 cuando no existe")
    void closeAccount_ShouldReturn404_WhenNotFound() {
        UUID nonExistentId = UUID.randomUUID();
        when(accountService.closeAccount(nonExistentId))
            .thenThrow(new IllegalArgumentException("Cuenta no encontrada"));

        webTestClient.delete()
            .uri("/api/accounts/{id}", nonExistentId)
            .exchange()
            .expectStatus().isNotFound();
    }
}