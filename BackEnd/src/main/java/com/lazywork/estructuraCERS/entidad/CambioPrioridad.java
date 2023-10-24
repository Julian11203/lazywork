package com.lazywork.estructuraCERS.entidad;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "CambioPrioridad")
public class CambioPrioridad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CambioPrioridadID")
    private Long cambioPrioridadID;

    @ManyToOne
    @JoinColumn(name = "IncidenciaID", nullable = false)
    private Incidencia incidencia;

    @ManyToOne
    @JoinColumn(name = "PrioridadID", nullable = false)
    private PrioridadIncidencia prioridad;

    @Column(name = "Descripcion", nullable = false)
    private String descripcion;

    @Column(name = "FechaCambio", nullable = false)
    private Date fechaCambio;

    // Getters y Setters


    public Long getCambioPrioridadID() {
        return cambioPrioridadID;
    }

    public void setCambioPrioridadID(Long cambioPrioridadID) {
        this.cambioPrioridadID = cambioPrioridadID;
    }

    public Incidencia getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(Incidencia incidencia) {
        this.incidencia = incidencia;
    }

    public PrioridadIncidencia getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(PrioridadIncidencia prioridad) {
        this.prioridad = prioridad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    @Override
    public String toString() {
        return "CambioPrioridad{" +
                "cambioPrioridadID=" + cambioPrioridadID +
                ", incidencia=" + incidencia +
                ", prioridad=" + prioridad +
                ", descripcion='" + descripcion + '\'' +
                ", fechaCambio=" + fechaCambio +
                '}';
    }
}