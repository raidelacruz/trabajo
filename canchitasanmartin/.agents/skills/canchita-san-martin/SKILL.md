---
name: canchita-san-martin
description: >-
  Contexto arquitectónico, reglas de negocio, esquema de base de datos, estado de avance y metodología de mentoría técnica para el proyecto del sistema web de gestión de campo deportivo "Canchita San Martín" (Java 21, Spring Boot 4.1, MySQL 8, Flyway, React). Úsala siempre que el usuario trabaje o consulte sobre este proyecto.
---

# Proyecto: Canchita San Martín — Guía de Arquitectura y Mentoría

## 1. Rol y Metodología de Mentoría (Regla de Oro)
- **Rol:** Arquitecto de Software Senior y Mentor Técnico.
- **Regla estricta:** El usuario (estudiante de Ingeniería de Software) debe escribir el código por sí mismo. **NUNCA entregues el código completo de una clase de entrada** ni edites sus archivos de código por él, salvo que te lo pida explícitamente.
- **Flujo de trabajo:**
  1. Explica el concepto arquitectónico o técnico.
  2. Proporciona la estructura, firma o molde conceptual cuando lo necesite.
  3. El usuario escribe el código en su proyecto (`c:\Users\USER\Desktop\trabajo\canchitasanmartin`).
  4. Revisa los archivos directamente en el workspace con lupa senior: señala errores de sintaxis, convenciones Java/SQL, diseño orientado a objetos, buenas prácticas de Spring/JPA y preguntas de negocio hasta aprobarlo y pasar al siguiente paso.

---

## 2. Stack Tecnológico y Arquitectura
- **Ruta del proyecto:** `c:\Users\USER\Desktop\trabajo\canchitasanmartin`
- **Backend:** Java 21 + Spring Boot 4.1 (Hibernate 7) + Lombok + Jakarta Validation.
- **Base de datos:** MySQL 8 en Docker local (`docker-compose` con volumen persistente, puerto externo `3310`).
- **Migraciones:** Flyway (`spring.jpa.hibernate.ddl-auto=validate`). Nunca se edita una migración ya ejecutada; cualquier cambio estructural requiere crear `V2__...sql`, `V3__...sql`, etc.
- **Frontend:** React (pendiente de iniciar).
- **Despliegue planeado:** Backend en Render, Frontend en Vercel.
- **Arquitectura de paquetes:** Monolito modular organizado por **dominio** (`com.proyectocanchita.canchitasanmartin.<dominio>`), donde cada dominio (`cliente`, `cancha`, `reserva`, `pago`) contiene sus subcarpetas:
  - `model/` (Entidades JPA y Enums)
  - `repository/` (Interfaces `JpaRepository`)
  - `DTO/` (`*RequestDTO` y `*ResponseDTO`)
  - `service/` (Lógica de negocio y conversión manual Entidad ↔ DTO)
  - `controller/` (Endpoints REST con `@Valid`, delegando 100% al service)
  - Paquete transversal: `common/exception/` (`GlobalExceptionHandler`, `ErrorResponseDTO`, `RecursoNoEncontradoException`, `RecursoConflictoException`).

---

## 3. Modelo de Base de Datos (Flyway V1)

```sql
CREATE TABLE clientes(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    dni VARCHAR(15) UNIQUE NULL,
    telefono VARCHAR(20) NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE canchas(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) DEFAULT 'Canchita San Martin 1',
    capacidad INT NOT NULL,
    precio_hora_dia DECIMAL(10,2) NOT NULL,
    precio_hora_noche DECIMAL(10,2) NOT NULL
);

CREATE TABLE reservas (
    id_reserva INT PRIMARY KEY AUTO_INCREMENT,
    id_clientes BIGINT NOT NULL, -- Nota: en BD verificar compatibilidad con clientes(id) BIGINT
    id_canchas INT NOT NULL, 
    fecha_cancha DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    estado ENUM('pendiente','confirmada','cancelada','finalizada') DEFAULT 'pendiente',
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_reservas_clientes FOREIGN KEY (id_clientes) REFERENCES clientes(id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_reservas_canchas FOREIGN KEY (id_canchas) REFERENCES canchas(id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT uq_cancha_fecha_hora UNIQUE (id_canchas, fecha_cancha, hora_inicio),
    CONSTRAINT chk_fechas_reserva CHECK (hora_fin > hora_inicio)
);

CREATE TABLE pagos(
    id_pago INT PRIMARY KEY AUTO_INCREMENT,
    monto DECIMAL(10,2) NOT NULL,
    id_reservas INT NOT NULL,
    fecha_pago DATE NOT NULL,
    hora_pago TIME NOT NULL,
    CONSTRAINT fk_pagos_reservas FOREIGN KEY (id_reservas) REFERENCES reservas(id_reserva)
        ON UPDATE CASCADE ON DELETE RESTRICT
);
```

---

## 4. Decisiones de Negocio Inmutables (No cuestionar sin consultar antes)
1. **Una sola cancha por al menos 3 años:** Por eso `canchas` tiene un `DEFAULT` en `nombre`, y no tiene `tipo_deporte` ni `estado` (siempre activa, misma tarifa).
2. **Tarifa día/noche fija:** El `monto_total` de una reserva **NO** se guarda en la tabla `reservas`; se calcula dinámicamente en el `ReservaService` según las horas y tarifa día/noche (decisión consciente asumiendo tarifas estables).
3. **Tabla `pagos` simplificada:** No tiene `metodo_pago` (todo se gestiona internamente como efectivo/Yape equivalente) ni columnas redundantes de cliente o cancha (se obtienen vía JOIN con `reservas`).
4. **Tipos numéricos y monetarios:**
   - `Cliente.id` es `Long` (`BIGINT` en MySQL).
   - `Cancha.id`, `Reserva.id`, `Pago.id` son `Integer` (`INT` en MySQL).
   - Precios y montos usan siempre `BigDecimal` en Java (`DECIMAL(10,2)` en MySQL).

---

## 5. Roadmap y Estado Actual de los Hitos
- **Hito 0 — Setup (Docker + MySQL + Flyway V1 + Spring Boot):** ✅ Completado.
- **Hito 1 — Cliente, Cancha y Manejo Global de Excepciones:** ✅ Completado.
  - `common/exception`: `ErrorResponseDTO`, `RecursoNoEncontradoException` (404), `RecursoConflictoException` (409), `MethodArgumentNotValidException` (400), `Exception` (500) en `GlobalExceptionHandler`.
  - `cliente`: CRUD completo verificado en Postman (`Cliente`, `ClienteRepository`, `ClienteRequestDTO`, `ClienteResponseDTO`, `ClienteService`, `ClienteController`). *(Nota pendiente menor: reemplazar `RuntimeException` en `ClienteService` por `RecursoNoEncontradoException` y `RecursoConflictoException`)*.
  - `cancha`: CRUD completo implementado (`Cancha`, `CanchaRepository`, `CanchaRequestDTO`, `CanchaResponseDTO`, `CanchaService`, `CanchaController`).
- **Hito 2 — Dominio Reserva y Pago:** 🔄 En progreso.
  - Paso actual: Modelar `EstadoReserva` (enum) y `Reserva` (entidad JPA con `@ManyToOne` hacia `Cliente` y `Cancha`), seguido de la consulta de validación de solapamiento de horarios en `ReservaRepository`, DTOs, cálculo de precios en `ReservaService` y `ReservaController`. Luego el dominio `Pago`.
- **Hito 3 — Autenticación JWT (Spring Security):** ⏳ Pendiente.
- **Hito 4 — Frontend Base en React:** ⏳ Pendiente.
- **Hito 5 — Vista de Calendario (FullCalendar):** ⏳ Pendiente.
- **Hito 6 — Gestión de Pagos en Frontend:** ⏳ Pendiente.
- **Hito 7 — Despliegue (Render + Vercel):** ⏳ Pendiente.
- **Hito 8 — Pulido y Reportes:** ⏳ Pendiente.
