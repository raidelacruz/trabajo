package com.proyectocanchita.canchitasanmartin.reserva.model;

import org.hibernate.annotations.CollectionIdMutability;

import jakarta.annotation.Generated;
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
    @Table(name = "reservas")
public class Reserva {
    @Column(name="id_reservas")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="id_clientes")
    private Long id_cliente;
    @Column(name="id_canchas")
    private Long id_canchas;

}
