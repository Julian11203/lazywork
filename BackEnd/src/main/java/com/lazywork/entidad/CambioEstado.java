package com.lazywork.entidad;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "CambiosEstado")
public class CambioEstado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CambioEstadoID")
    private Long cambioEstadoID;

    @ManyToOne

    @JoinColumn(name = "IncidenciaID", nullable = false)
    private Incidencia incidencia;

    @ManyToOne
    @JoinColumn(name = "EstadoID", nullable = false)
    private EstadoIncidencia estado;

    @Column(name = "Descripcion", nullable = false)
    private String descripcion;

    @Column(name = "FechaRegistro", nullable = false)
    private Date fechaRegistro;

    // Getters y Setters
}