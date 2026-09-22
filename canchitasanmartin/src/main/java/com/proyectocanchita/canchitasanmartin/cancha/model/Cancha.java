package com.proyectocanchita.canchitasanmartin.cancha.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter 
@Setter 
@Table(name="canchas") 
public class Cancha {
    @Column(name="id")
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (name="nombre",nullable = false,length = 100)
    private String nombre;
    @Column(name="capacidad",nullable = false)
    private Integer capacidad;
    @Column(name="precio_hora_dia",nullable = false)
    private BigDecimal precioHoraDia;
    @Column(name="precio_hora_noche",nullable = false)    
    private BigDecimal precioHoraNoche;
}
