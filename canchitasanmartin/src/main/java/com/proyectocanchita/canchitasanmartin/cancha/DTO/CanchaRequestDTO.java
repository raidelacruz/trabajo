package com.proyectocanchita.canchitasanmartin.cancha.DTO;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor 
@Getter
@Setter 
public class CanchaRequestDTO {
    @NotBlank @Size(max=30)
    private String nombre;
    @NotNull @Min(1)
    private Integer capacidad;
    @NotNull @DecimalMin("0.0")
    private BigDecimal precioHoraDia;
    @NotNull @DecimalMin("0.0")
    private BigDecimal precioHoraNoche;
}
