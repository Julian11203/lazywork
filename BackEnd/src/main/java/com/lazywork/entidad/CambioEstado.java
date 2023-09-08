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

    public Long getCambioEstadoID() {
        return cambioEstadoID;
    }

    public Incidencia getIncidencia() {
        return incidencia;
    }

    public EstadoIncidencia getEstado() {
        return estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    @Override
    public String toString() {
        return "CambioEstado{" +
                "cambioEstadoID=" + cambioEstadoID +
                ", incidencia=" + incidencia +
                ", estado=" + estado +
                ", descripcion='" + descripcion + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
    public void setId(Long id){

    }
}