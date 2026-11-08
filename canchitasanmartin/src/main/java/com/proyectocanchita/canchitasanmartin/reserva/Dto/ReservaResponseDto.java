package com.proyectocanchita.canchitasanmartin.reserva.Dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.proyectocanchita.canchitasanmartin.reserva.model.EstadoReserva;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor 
@Getter 
public class ReservaResponseDto {
    private Integer id;
    private Long idCliente;
    private String nombreCompletoCliente;
    private Integer idCancha;
    private String nombreCancha;
    private LocalDate fechaCancha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private EstadoReserva estado;
    private BigDecimal montoTotal;
    private LocalDateTime fechaCreacion;
}
