# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/bank/account/application/service/AccountService.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/account/infrastructure/config/DatabaseConfig.java` — `com.zaxxer.hikari`: El import com.zaxxer.hikari.HikariConfig pertenece a com.zaxxer.hikari, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/account/infrastructure/controller/AccountController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/account/infrastructure/repository/entity/AccountEntity.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/bank/account/infrastructure/controller/AccountControllerTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/account/infrastructure/adapter/AccountJpaRepository.java` — `JpaTransactionOperations.saveAll`: Se invoca `saveAll` sobre `JpaTransactionOperations`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/AccountJpaRepository.java` — `AccountEntity.setAccountId`: Se invoca `setAccountId` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/AccountJpaRepository.java` — `AccountEntity.setAmount`: Se invoca `setAmount` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/AccountJpaRepository.java` — `AccountEntity.setType`: Se invoca `setType` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/AccountJpaRepository.java` — `AccountEntity.setDescription`: Se invoca `setDescription` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/adapter/AccountJpaRepository.java` — `AccountEntity.setReference`: Se invoca `setReference` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/application/service/AccountService.java` — `Account.isEmpty`: Se invoca `isEmpty` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/application/service/AccountService.java` — `AccountRepository.findAll`: Se invoca `findAll` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/controller/AccountController.java` — `AccountRequest.customerId`: Se invoca `customerId` sobre `AccountRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/controller/AccountController.java` — `AccountRequest.initialBalance`: Se invoca `initialBalance` sobre `AccountRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/controller/AccountController.java` — `AccountRequest.accountType`: Se invoca `accountType` sobre `AccountRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/controller/AccountController.java` — `AccountService.findById`: Se invoca `findById` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/controller/AccountController.java` — `AccountService.findByAccountNumber`: Se invoca `findByAccountNumber` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/controller/AccountController.java` — `AccountService.findByCustomerId`: Se invoca `findByCustomerId` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/controller/AccountController.java` — `AccountService.findAll`: Se invoca `findAll` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/controller/AccountController.java` — `AccountService.updateAccount`: Se invoca `updateAccount` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/account/infrastructure/controller/AccountController.java` — `AccountService.existsById`: Se invoca `existsById` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/account/application/service/AccountServiceTest.java` — `Account.isPresent`: Se invoca `isPresent` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/account/application/service/AccountServiceTest.java` — `Account.get`: Se invoca `get` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/account/application/service/AccountServiceTest.java` — `AccountService.getAccountByNumber`: Se invoca `getAccountByNumber` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/account/infrastructure/controller/AccountControllerTest.java` — `AccountService.getAccountByNumber`: Se invoca `getAccountByNumber` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Senior

### Brecha de conocimiento
Aplica al menos dos patrones GRASP (Patrones de Software para la Asignación de Responsabilidades Generales) en el diseño y desarrollo de un sistema. Entre ellos: Experto en Información, Creador, Controlador, Alta Cohesión y Bajo Acoplamiento, Polimorfismo, Fabricación Pura, Indirección y Variaciones Protegidas.

### Misión / candidato
Candidato Senior con experiencia en desarrollo backend en Java

### Reto
- Tema: aplicación de patrones GRASP en el desarrollo de sistemas
- Seniority: senior-l2
- Tipo: mixed
- Título: Aplicación de Patrones GRASP en un Sistema de Gestión de Cuentas Bancarias
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Definición de Responsabilidades — objetivo: Identificar y asignar responsabilidades a los componentes del sistema siguiendo el patrón 'Experto en Información'. — entregable (NO resolver): Documento que describe las responsabilidades asignadas a cada componente del sistema.
- Fase 2: Implementación del Patrón 'Creador' — objetivo: Implementar el patrón 'Creador' para la creación de cuentas bancarias. — entregable (NO resolver): Componente que implementa el patrón 'Creador' para la creación de cuentas bancarias.
- Fase 3: Integración y Pruebas — objetivo: Integrar los componentes implementados y realizar pruebas para garantizar la consistencia y el rendimiento del sistema. — entregable (NO resolver): Sistema integrado y probado que aplica los patrones GRASP 'Experto en Información' y 'Creador'.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.bank.account</groupId>
    <artifactId>account-service</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>account-service</name>
    <description>Servicio de gestión de cuentas bancarias con aplicación de patrones GRASP</description>

    <properties>
        <java.version>21</java.version>
        <resilience4j.version>2.2.0</resilience4j.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- Resilience4j para patrones de resiliencia -->
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot3</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>

        <!-- Bases de datos -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>2.2.224</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.7.3</version>
            <scope>runtime</scope>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.30</version>
            <scope>provided</scope>
        </dependency>

        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.testcontainers</groupId>
            <artifactId>postgresql</artifactId>
            <version>1.19.8</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>21</source>
                    <target>21</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/bank/account/AccountApplication.java ===
package com.bank.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

import java.time.Duration;

@SpringBootApplication
@EnableAsync
public class AccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .slidingWindowSize(2)
                .build();
        return CircuitBreakerRegistry.of(circuitBreakerConfig);
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
server:
  port: 8080
  servlet:
    context-path: /api/v1
  tomcat:
    threads:
      max: 200
    connection-timeout: 20000

spring:
  application:
    name: account-service
  profiles:
    active: dev
  datasource:
    url: jdbc:postgresql://localhost:5432/account_db
    username: account_user
    password: secure_password
    driver-class-name: org.postgresql.Driver
    hikari:
      maximum-pool-size: 20
      minimum-idle: 5
      idle-timeout: 30000
      connection-timeout: 20000
      max-lifetime: 600000
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true
        jdbc:
          batch_size: 50
          batch_versioned_data: true
        order_inserts: true
        order_updates: true
  h2:
    console:
      enabled: true
      path: /h2-console
  actuator:
    endpoints:
      web:
        exposure:
          include: health,metrics,info,circuitbreakers
    endpoint:
      health:
        show-details: always

resilience4j:
  circuitbreaker:
    instances:
      accountService:
        registerHealthIndicator: true
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 5s
        failureRateThreshold: 50
        eventConsumerBufferSize: 10
      transactionService:
        registerHealthIndicator: true
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 5s
        failureRateThreshold: 50
        eventConsumerBufferSize: 10

logging:
  level:
    root: INFO
    com.bank.account: DEBUG
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} - %msg%n"
    file: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"
  file:
    name: logs/account-service.log

// === ARCHIVO: src/main/java/com/bank/account/domain/model/Account.java ===
package com.bank.account.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public final class Account {
    private final UUID id;
    private final String accountNumber;
    private final String customerId;
    private BigDecimal balance;
    private final AccountStatus status;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private final List<Transaction> transactions;

    public Account(UUID id, String accountNumber, String customerId, BigDecimal initialBalance, AccountStatus status) {
        this.id = Objects.requireNonNull(id, "El ID de la cuenta no puede ser nulo");
        this.accountNumber = Objects.requireNonNull(accountNumber, "El número de cuenta no puede ser nulo");
        this.customerId = Objects.requireNonNull(customerId, "El ID del cliente no puede ser nulo");
        this.balance = Objects.requireNonNull(initialBalance, "El saldo inicial no puede ser nulo");
        this.status = Objects.requireNonNull(status, "El estado de la cuenta no puede ser nulo");
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
        this.transactions = new ArrayList<>();

        if (initialBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
        }
    }

    public UUID getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public void deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser positivo");
        }
        if (this.status != AccountStatus.ACTIVE) {
            throw new IllegalStateException("No se puede depositar en una cuenta no activa");
        }
        this.balance = this.balance.add(amount);
        this.updatedAt = LocalDateTime.now();
    }

    public void withdraw(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser positivo");
        }
        if (this.status != AccountStatus.ACTIVE) {
            throw new IllegalStateException("No se puede retirar de una cuenta no activa");
        }
        if (this.balance.compareTo(amount) < 0) {
            throw new InsufficientFundsException("Saldo insuficiente para realizar el retiro");
        }
        this.balance = this.balance.subtract(amount);
        this.updatedAt = LocalDateTime.now();
    }

    public void addTransaction(Transaction transaction) {
        Objects.requireNonNull(transaction, "La transacción no puede ser nula");
        if (!this.id.equals(transaction.getAccountId())) {
            throw new IllegalArgumentException("La transacción no pertenece a esta cuenta");
        }
        this.transactions.add(transaction);
    }

    public void close() {
        if (this.status == AccountStatus.CLOSED) {
            throw new IllegalStateException("La cuenta ya está cerrada");
        }
        if (this.balance.compareTo(BigDecimal.ZERO) != 0) {
            throw new IllegalStateException("No se puede cerrar una cuenta con saldo diferente de cero");
        }
        this.status = AccountStatus.CLOSED;
        this.updatedAt = LocalDateTime.now();
    }

    public enum AccountStatus {
        ACTIVE, BLOCKED, CLOSED
    }

    public static class InsufficientFundsException extends RuntimeException {
        public InsufficientFundsException(String message) {
            super(message);
        }
    }
}

// === ARCHIVO: src/main/java/com/bank/account/domain/model/Transaction.java ===
package com.bank.account.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public final class Transaction {
    private final UUID id;
    private final UUID accountId;
    private final BigDecimal amount;
    private final TransactionType type;
    private final String description;
    private final LocalDateTime createdAt;
    private final String reference;

    public Transaction(UUID id, UUID accountId, BigDecimal amount, TransactionType type, String description, String reference) {
        this.id = Objects.requireNonNull(id, "El ID de la transacción no puede ser nulo");
        this.accountId = Objects.requireNonNull(accountId, "El ID de la cuenta no puede ser nulo");
        this.amount = Objects.requireNonNull(amount, "El monto no puede ser nulo");
        this.type = Objects.requireNonNull(type, "El tipo de transacción no puede ser nulo");
        this.description = description;
        this.reference = Objects.requireNonNull(reference, "La referencia no puede ser nula");
        this.createdAt = LocalDateTime.now();

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }
    }

    public UUID getId() {
        return id;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getReference() {
        return reference;
    }

    public enum TransactionType {
        DEPOSIT, WITHDRAWAL, TRANSFER_IN, TRANSFER_OUT
    }
}

// === ARCHIVO: src/main/java/com/bank/account/domain/port/AccountRepository.java ===
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

// === ARCHIVO: src/main/java/com/bank/account/domain/port/TransactionRepository.java ===
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

// === ARCHIVO: src/main/java/com/bank/account/infrastructure/adapter/AccountJpaRepository.java ===
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

// === ARCHIVO: src/main/java/com/bank/account/infrastructure/adapter/TransactionJpaRepository.java ===
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

// === ARCHIVO: src/main/java/com/bank/account/infrastructure/controller/dto/AccountRequest.java ===
package com.bank.account.infrastructure.controller.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class AccountRequest {

    @NotBlank(message = "El número de cuenta es obligatorio")
    @Size(min = 10, max = 20, message = "El número de cuenta debe tener entre 10 y 20 caracteres")
    private String accountNumber;

    @NotBlank(message = "El ID del cliente es obligatorio")
    @Size(min = 1, max = 50, message = "El ID del cliente debe tener entre 1 y 50 caracteres")
    private String customerId;

    @NotNull(message = "El saldo inicial es obligatorio")
    @DecimalMin(value = "0.0", message = "El saldo inicial no puede ser negativo")
    private BigDecimal initialBalance;

    @NotNull(message = "El estado de la cuenta es obligatorio")
    private String status;

    public AccountRequest() {
    }

    public AccountRequest(String accountNumber, String customerId, BigDecimal initialBalance, String status) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.initialBalance = initialBalance;
        this.status = status;
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

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

// === ARCHIVO: src/main/java/com/bank/account/infrastructure/controller/dto/AccountResponse.java ===
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

// === ARCHIVO: src/main/java/com/bank/account/application/service/AccountService.java ===
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

// === ARCHIVO: src/main/java/com/bank/account/infrastructure/config/DatabaseConfig.java ===
package com.bank.account.infrastructure.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.bank.account.infrastructure.adapter")
public class DatabaseConfig {

    @Value("${spring.datasource.url:jdbc:postgresql://localhost:5432/bank_account}")
    private String jdbcUrl;

    @Value("${spring.datasource.username:postgres}")
    private String username;

    @Value("${spring.datasource.password:postgres}")
    private String password;

    @Value("${spring.datasource.hikari.maximum-pool-size:50}")
    private int maximumPoolSize;

    @Value("${spring.datasource.hikari.minimum-idle:20}")
    private int minimumIdle;

    @Value("${spring.datasource.hikari.connection-timeout:30000}")
    private long connectionTimeout;

    @Value("${spring.datasource.hikari.idle-timeout:600000}")
    private long idleTimeout;

    @Value("${spring.datasource.hikari.max-lifetime:1800000}")
    private long maxLifetime;

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size:50}")
    private int batchSize;

    @Value("${spring.jpa.properties.hibernate.order_inserts:true}")
    private boolean orderInserts;

    @Value("${spring.jpa.properties.hibernate.order_updates:true}")
    private boolean orderUpdates;

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_versioned_data:true}")
    private boolean batchVersionedData;

    @Bean
    @Primary
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(maximumPoolSize);
        config.setMinimumIdle(minimumIdle);
        config.setConnectionTimeout(connectionTimeout);
        config.setIdleTimeout(idleTimeout);
        config.setMaxLifetime(maxLifetime);
        config.setAutoCommit(true);
        config.setConnectionTestQuery("SELECT 1");
        config.setPoolName("BankAccountHikariPool");
        
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");
        config.addDataSourceProperty("useLocalSessionState", "true");
        config.addDataSourceProperty("rewriteBatchedStatements", "true");
        config.addDataSourceProperty("cacheResultSetMetadata", "true");
        config.addDataSourceProperty("cacheServerConfiguration", "true");
        config.addDataSourceProperty("elideSetAutoCommits", "true");
        config.addDataSourceProperty("maintainTimeStats", "false");

        return new HikariDataSource(config);
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.bank.account.domain.model", "com.bank.account.infrastructure.repository.entity");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(false);
        em.setJpaVendorAdapter(vendorAdapter);

        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty("hibernate.jdbc.batch_size", String.valueOf(batchSize));
        hibernateProperties.setProperty("hibernate.order_inserts", String.valueOf(orderInserts));
        hibernateProperties.setProperty("hibernate.order_updates", String.valueOf(orderUpdates));
        hibernateProperties.setProperty("hibernate.jdbc.batch_versioned_data", String.valueOf(batchVersionedData));
        hibernateProperties.setProperty("hibernate.generate_statistics", "false");
        hibernateProperties.setProperty("hibernate.jdbc.wrap_result_sets", "true");
        hibernateProperties.setProperty("hibernate.jdbc.fetch_size", "100");
        hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", "false");
        hibernateProperties.setProperty("hibernate.cache.use_query_cache", "false");
        hibernateProperties.setProperty("hibernate.connection.provider_disables_autocommit", "true");

        em.setJpaProperties(hibernateProperties);
        return em;
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
        transactionManager.setDefaultTimeout(30);
        transactionManager.setNestedTransactionAllowed(true);
        return transactionManager;
    }
}

// === ARCHIVO: src/main/java/com/bank/account/infrastructure/controller/AccountController.java ===
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

// === ARCHIVO: src/main/java/com/bank/account/infrastructure/repository/entity/AccountEntity.java ===
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

// === ARCHIVO: src/main/java/com/bank/account/infrastructure/repository/entity/TransactionEntity.java ===
package com.bank.account.infrastructure.repository.entity;

import com.bank.account.domain.model.Transaction;
import com.bank.account.domain.model.Transaction.TransactionType;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions", indexes = {
    @Index(name = "idx_transaction_account_id", columnList = "account_id"),
    @Index(name = "idx_transaction_created_at", columnList = "created_at"),
    @Index(name = "idx_transaction_reference", columnList = "reference", unique = true)
})
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "account_id", nullable = false)
    private UUID accountId;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TransactionType type;

    @Column(length = 255)
    private String description;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false, unique = true, length = 50)
    private String reference;

    public TransactionEntity() {
    }

    public TransactionEntity(UUID accountId, BigDecimal amount, TransactionType type, 
                             String description, String reference) {
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.reference = reference;
        this.createdAt = LocalDateTime.now();
    }

    public static TransactionEntity fromDomain(Transaction transaction) {
        TransactionEntity entity = new TransactionEntity();
        entity.id = transaction.getId();
        entity.accountId = transaction.getAccountId();
        entity.amount = transaction.getAmount();
        entity.type = transaction.getType();
        entity.description = transaction.getDescription();
        entity.createdAt = transaction.getCreatedAt();
        entity.reference = transaction.getReference();
        return entity;
    }

    public Transaction toDomain() {
        return new Transaction(
            this.id,
            this.accountId,
            this.amount,
            this.type,
            this.description,
            this.reference
        );
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}

// === ARCHIVO: src/test/java/com/bank/account/application/service/AccountServiceTest.java ===
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

// === ARCHIVO: src/test/java/com/bank/account/infrastructure/controller/AccountControllerTest.java ===
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
```
