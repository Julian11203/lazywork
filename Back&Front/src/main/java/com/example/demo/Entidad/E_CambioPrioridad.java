package com.example.demo.Entidad;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "E_CambioPrioridad")
public class E_CambioPrioridad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CambioPrioridadID")
    private Long cambioPrioridadID;

    @ManyToOne
    @JoinColumn(name = "IncidenciaID", nullable = false)
    private E_Incidencia incidencia;

    @ManyToOne
    @JoinColumn(name = "PrioridadID", nullable = false)
    private E_PrioridadIncidencia prioridad;

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

    public E_Incidencia getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(E_Incidencia incidencia) {
        this.incidencia = incidencia;
    }

    public E_PrioridadIncidencia getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(E_PrioridadIncidencia prioridad) {
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
        return "E_CambioPrioridad{" +
                "cambioPrioridadID=" + cambioPrioridadID +
                ", incidencia=" + incidencia +
                ", prioridad=" + prioridad +
                ", descripcion='" + descripcion + '\'' +
                ", fechaCambio=" + fechaCambio +
                '}';
    }
}