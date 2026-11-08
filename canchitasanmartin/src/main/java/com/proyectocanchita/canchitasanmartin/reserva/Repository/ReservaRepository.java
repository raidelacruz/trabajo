package com.proyectocanchita.canchitasanmartin.reserva.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyectocanchita.canchitasanmartin.reserva.model.EstadoReserva;
import com.proyectocanchita.canchitasanmartin.reserva.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    List<Reserva> findByFechaCancha(LocalDate fechaCancha);


    @Query("""
    SELECT COUNT(r) > 0 FROM Reserva r
    WHERE r.cancha.id = :canchaId
      AND r.fechaCancha = :fechaCancha
      AND r.estado <> :estadoCancelado
      AND r.horaInicio < :nuevaHoraFin
      AND r.horaFin > :nuevaHoraInicio
""")
boolean existeSolapamiento(
    @Param("canchaId") Integer canchaId,
    @Param("fechaCancha") LocalDate fechaCancha,
    @Param("nuevaHoraInicio") LocalTime nuevaHoraInicio,
    @Param("nuevaHoraFin") LocalTime nuevaHoraFin,
    @Param("estadoCancelado") EstadoReserva estadoCancelado
);
}
