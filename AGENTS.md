# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Aplicación de Patrones GRASP en un Sistema de Gestión de Cuentas Bancarias**.

| | |
|---|---|
| Tema | aplicación de patrones GRASP en el desarrollo de sistemas |
| Nivel | senior-l2 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.5 |
| Patron arquitectonico | hexagonal/clean |
| Tiempo estimado | 8 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web n/a
- org.springframework.boot:spring-boot-starter-data-jpa n/a
- org.springframework.boot:spring-boot-starter-validation n/a
- org.springframework.boot:spring-boot-starter-actuator n/a
- com.h2database:h2 2.2.224
- org.postgresql:postgresql 42.7.3
- org.projectlombok:lombok 1.18.30
- org.springframework.boot:spring-boot-starter-test n/a
- org.testcontainers:postgresql 1.19.8
- io.github.resilience4j:resilience4j-spring-boot3 2.2.0

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Definición de Responsabilidades**: Documento que describe las responsabilidades asignadas a cada componente del sistema.
- **Fase 2 — Implementación del Patrón 'Creador'**: Componente que implementa el patrón 'Creador' para la creación de cuentas bancarias.
- **Fase 3 — Integración y Pruebas**: Sistema integrado y probado que aplica los patrones GRASP 'Experto en Información' y 'Creador'.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Lo que falta y tenes que completar

### 1. Referencias colgando (26)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/bank/account/application/service/AccountService.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/account/infrastructure/config/DatabaseConfig.java` — `com.zaxxer.hikari`
      El import com.zaxxer.hikari.HikariConfig pertenece a com.zaxxer.hikari, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/account/infrastructure/controller/AccountController.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/account/infrastructure/repository/entity/AccountEntity.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/bank/account/infrastructure/controller/AccountControllerTest.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/AccountJpaRepository.java` — `JpaTransactionOperations.saveAll`
      Se invoca `saveAll` sobre `JpaTransactionOperations`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/AccountJpaRepository.java` — `AccountEntity.setAccountId`
      Se invoca `setAccountId` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/AccountJpaRepository.java` — `AccountEntity.setAmount`
      Se invoca `setAmount` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/AccountJpaRepository.java` — `AccountEntity.setType`
      Se invoca `setType` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/AccountJpaRepository.java` — `AccountEntity.setDescription`
      Se invoca `setDescription` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/adapter/AccountJpaRepository.java` — `AccountEntity.setReference`
      Se invoca `setReference` sobre `AccountEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/application/service/AccountService.java` — `Account.isEmpty`
      Se invoca `isEmpty` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/application/service/AccountService.java` — `AccountRepository.findAll`
      Se invoca `findAll` sobre `AccountRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/controller/AccountController.java` — `AccountRequest.customerId`
      Se invoca `customerId` sobre `AccountRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/controller/AccountController.java` — `AccountRequest.initialBalance`
      Se invoca `initialBalance` sobre `AccountRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/controller/AccountController.java` — `AccountRequest.accountType`
      Se invoca `accountType` sobre `AccountRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/controller/AccountController.java` — `AccountService.findById`
      Se invoca `findById` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/controller/AccountController.java` — `AccountService.findByAccountNumber`
      Se invoca `findByAccountNumber` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/controller/AccountController.java` — `AccountService.findByCustomerId`
      Se invoca `findByCustomerId` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/controller/AccountController.java` — `AccountService.findAll`
      Se invoca `findAll` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/controller/AccountController.java` — `AccountService.updateAccount`
      Se invoca `updateAccount` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/account/infrastructure/controller/AccountController.java` — `AccountService.existsById`
      Se invoca `existsById` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/account/application/service/AccountServiceTest.java` — `Account.isPresent`
      Se invoca `isPresent` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/account/application/service/AccountServiceTest.java` — `Account.get`
      Se invoca `get` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/account/application/service/AccountServiceTest.java` — `AccountService.getAccountByNumber`
      Se invoca `getAccountByNumber` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/account/infrastructure/controller/AccountControllerTest.java` — `AccountService.getAccountByNumber`
      Se invoca `getAccountByNumber` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (18)

- `pom.xml`
- `src/main/java/com/bank/account/AccountApplication.java`
- `src/main/resources/application.yml`
- `src/main/java/com/bank/account/domain/model/Account.java`
- `src/main/java/com/bank/account/domain/model/Transaction.java`
- `src/main/java/com/bank/account/domain/port/AccountRepository.java`
- `src/main/java/com/bank/account/domain/port/TransactionRepository.java`
- `src/main/java/com/bank/account/infrastructure/adapter/AccountJpaRepository.java`
- `src/main/java/com/bank/account/infrastructure/adapter/TransactionJpaRepository.java`
- `src/main/java/com/bank/account/infrastructure/controller/dto/AccountRequest.java`
- `src/main/java/com/bank/account/infrastructure/controller/dto/AccountResponse.java`
- `src/main/java/com/bank/account/application/service/AccountService.java`
- `src/main/java/com/bank/account/infrastructure/config/DatabaseConfig.java`
- `src/main/java/com/bank/account/infrastructure/controller/AccountController.java`
- `src/main/java/com/bank/account/infrastructure/repository/entity/AccountEntity.java`
- `src/main/java/com/bank/account/infrastructure/repository/entity/TransactionEntity.java`
- `src/test/java/com/bank/account/application/service/AccountServiceTest.java`
- `src/test/java/com/bank/account/infrastructure/controller/AccountControllerTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/bank/account/domain`
- `src/main/java/com/bank/account/domain/model`
- `src/main/java/com/bank/account/domain/port`
- `src/main/java/com/bank/account/application`
- `src/main/java/com/bank/account/application/service`
- `src/main/java/com/bank/account/infrastructure`
- `src/main/java/com/bank/account/infrastructure/adapter`
- `src/main/java/com/bank/account/infrastructure/config`
- `src/main/java/com/bank/account/infrastructure/controller`
- `src/main/java/com/bank/account/infrastructure/repository`
- `src/main/resources`
- `src/test/java/com/bank/account`

## Verificacion

```bash
mvn clean compile
```

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Desarrollador, Tecnología Java, Senior
- Brecha que el reto ataca: Aplica al menos dos patrones GRASP (Patrones de Software para la Asignación de Responsabilidades Generales) en el diseño y desarrollo de un sistema. Entre ellos: Experto en Información, Creador, Controlador, Alta Cohesión y Bajo Acoplamiento, Polimorfismo, Fabricación Pura, Indirección y Variaciones Protegidas.
- Mision: Candidato Senior con experiencia en desarrollo backend en Java

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
