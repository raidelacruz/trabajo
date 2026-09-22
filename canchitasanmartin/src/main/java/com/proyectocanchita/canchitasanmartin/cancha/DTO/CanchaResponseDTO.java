package com.proyectocanchita.canchitasanmartin.cancha.DTO;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor  
@Getter
@NoArgsConstructor 

public class CanchaResponseDTO {
    private Integer id;
    private String nombre;
    private Integer capacidad;
    private BigDecimal precioHoraDia;
    private BigDecimal precioHoraNoche;    
}
