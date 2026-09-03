package com.proyectocanchita.canchitasanmartin.cancha.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name="canchas")
public class Cancha {
    @Column(name="id")
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name="capacidad")
    private Integer capacidad;
    @Column(name="precio_hora_dia")
    private BigDecimal precio_hora_dia
}
