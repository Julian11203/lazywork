package com.example.demo.Entidad;

import javax.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "CambiosEstado")
public class E_CambioEstado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CambioEstadoID")
    private Long cambioEstadoID;

    @ManyToOne

    @JoinColumn(name = "IncidenciaID", nullable = false)
    private E_Incidencia incidencia;

    @ManyToOne
    @JoinColumn(name = "EstadoID", nullable = false)
    private E_EstadoIncidencia estado;

    @Column(name = "Descripcion", nullable = false)
    private String descripcion;

    @Column(name = "FechaRegistro", nullable = false)
    private LocalDate fechaRegistro;

    public E_CambioEstado(Long cambioEstadoID, E_Incidencia incidencia, E_EstadoIncidencia estado, String descripcion, LocalDate fechaRegistro) {
        this.cambioEstadoID = cambioEstadoID;
        this.incidencia = incidencia;
        this.estado = estado;
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
    }

    public E_CambioEstado() {
    }
    // Getters y Setters


    public Long getCambioEstadoID() {
        return cambioEstadoID;
    }

    public void setCambioEstadoID(Long cambioEstadoID) {
        this.cambioEstadoID = cambioEstadoID;
    }

    public E_Incidencia getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(E_Incidencia incidencia) {
        this.incidencia = incidencia;
    }

    public E_EstadoIncidencia getEstado() {
        return estado;
    }

    public void setEstado(E_EstadoIncidencia estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "E_CambioEstado{" +
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
