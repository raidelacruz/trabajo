package com.proyectocanchita.canchitasanmartin.cliente.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;

@Entity
@Getter
@Setter
@Table(name = "clientes")

public class Cliente {
     @Column(name="id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
      @Column(name = "nombre",nullable = false,length = 100)
    private String nombre;
      @Column(name = "apellido",nullable = false,length = 100)
    private String apellido;
      @Column(name = "dni",unique = true,length = 15)
    private String dni;
      @Column(name = "telefono",nullable = false,length = 15)
    private String telefono;
    @Column(name = "fecha_registro",insertable = false,updatable = false)
    @Setter(AccessLevel.NONE)
    private LocalDateTime fechaRegistro;
}
