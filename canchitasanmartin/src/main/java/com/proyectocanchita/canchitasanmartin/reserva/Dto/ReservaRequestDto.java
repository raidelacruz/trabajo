package com.proyectocanchita.canchitasanmartin.reserva.Dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor 
@Getter 
@Setter 
public class ReservaRequestDto {
    @NotNull(message = "El id del cliente es obligatorio")
    private Long idCliente;
    @NotNull (message = "El id de la cancha es obligatorio")
    private Integer idCancha;
    @NotNull (message = "La fecha de la cancha no puede estar vacia") @FutureOrPresent(message = "La fecha de la cancha no puede ser anterior a la fecha actual") 
    private LocalDate fechaCancha;
    @NotNull (message = "La hora de inicio no puede estar vacia")
    private LocalTime horaInicio;
    @NotNull (message = "La hora de fin no puede estar vacia") 
    private LocalTime horaFin;
    @NotNull (message = "El monto total no puede estar vacio")  @Positive(message = "El monto total debe ser mayor a cero")
    private BigDecimal montoTotal;
}
