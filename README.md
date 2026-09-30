# Aplicación de Patrones GRASP en un Sistema de Gestión de Cuentas Bancarias

El sistema de gestión de cuentas bancarias debe permitir la creación, modificación y eliminación de cuentas, así como la consulta de saldos. Los patrones GRASP que se deben aplicar son 'Experto en Información' y 'Creador'. El sistema debe manejar un volumen de 1 500 transacciones por segundo en hora pico y garantizar la consistencia de los datos en caso de fallos temporales.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | aplicación de patrones GRASP en el desarrollo de sistemas |
| **Nivel** | senior-l2 |
| **Tipo** | mixed |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Definición de Responsabilidades

**Objetivo:** Identificar y asignar responsabilidades a los componentes del sistema siguiendo el patrón 'Experto en Información'.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Identificar los componentes del sistema que deben ser expertos en información.
- Asignar responsabilidades a cada componente de acuerdo con el patrón 'Experto en Información'.

**Entregable:** Documento que describe las responsabilidades asignadas a cada componente del sistema.

<details>
<summary>Pistas de conocimiento</summary>

- Revisa la documentación del dominio para identificar las responsabilidades.
- Considera la cohesión y el acoplamiento al asignar responsabilidades.

</details>

### Fase 2: Implementación del Patrón 'Creador'

**Objetivo:** Implementar el patrón 'Creador' para la creación de cuentas bancarias.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Identificar el componente que será el 'Creador' de cuentas bancarias.
- Implementar la lógica de creación de cuentas siguiendo el patrón 'Creador'.

**Entregable:** Componente que implementa el patrón 'Creador' para la creación de cuentas bancarias.

<details>
<summary>Pistas de conocimiento</summary>

- Revisa la documentación del patrón 'Creador' para entender su propósito y aplicación.
- Considera la cohesión y el acoplamiento al implementar el patrón.

</details>

### Fase 3: Integración y Pruebas

**Objetivo:** Integrar los componentes implementados y realizar pruebas para garantizar la consistencia y el rendimiento del sistema.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Integrar los componentes implementados en la fase anterior.
- Realizar pruebas para garantizar la consistencia y el rendimiento del sistema.

**Entregable:** Sistema integrado y probado que aplica los patrones GRASP 'Experto en Información' y 'Creador'.

<details>
<summary>Pistas de conocimiento</summary>

- Revisa la documentación de pruebas para entender los tipos de pruebas a realizar.
- Considera el rendimiento y la consistencia al integrar y probar el sistema.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es el patrón 'Experto en Información' y cómo se aplica en este sistema?
- **paraQueSirve**: ¿Para qué sirve el patrón 'Creador' en este sistema y cómo mejora la creación de cuentas bancarias?
- **comoSeUsa**: ¿Cómo se usa el patrón 'Experto en Información' para asignar responsabilidades en este sistema?
- **erroresComunes**: ¿Cuáles son los errores comunes al aplicar los patrones GRASP en este sistema y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones implica la aplicación de los patrones GRASP en este sistema y cómo se justifican?

## Criterios de Evaluacion

- Identificar y asignar responsabilidades siguiendo el patrón 'Experto en Información'.
- Implementar el patrón 'Creador' para la creación de cuentas bancarias.
- Integrar y probar el sistema para garantizar la consistencia y el rendimiento.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
