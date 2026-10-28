package com.proyectocanchita.canchitasanmartin.reserva.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.proyectocanchita.canchitasanmartin.cancha.model.Cancha;
import com.proyectocanchita.canchitasanmartin.cliente.model.Cliente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

    @Entity
    @Getter
    @Setter
    @Table(name = "reservas")
public class Reserva {
    @Column(name="id_reserva")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clientes", nullable = false)
    private Cliente cliente;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_canchas", nullable = false)
    private Cancha cancha;
    @Column(name="fecha_cancha", nullable = false)
    private LocalDate fechaCancha;
    @Column(name="hora_inicio", nullable = false)
    private LocalTime horaInicio;
    @Column(name="hora_fin", nullable = false)
    private LocalTime horaFin;
    @Enumerated(EnumType.STRING) 
    @Column (name="estado")
    private EstadoReserva estado;
    @Column (name="fecha_creacion",insertable=false,updatable=false)
    private LocalDateTime fechaCreacion;
}
