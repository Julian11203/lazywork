package com.lazywork.entidad;

import jakarta.persistence.*;

import java.sql.Timestamp;
@Entity
@Table
public class Tiene {
     @Id
     @Column(name = "nregistro", length = 20, nullable = false)
     private String nRegistro;
     @Column(length = 80, nullable = false)
     private String descripcion;
     @Column(nullable = false)
     private Timestamp fechaRegistro;
     @ManyToOne
     @JoinColumn(name = "noincidencia",referencedColumnName = "noIncidencia",nullable = false)
     private Incidencia incidencia;
     @ManyToOne
     @JoinColumn(name = "idprioridad",referencedColumnName = "idPrioridad",nullable = false)
     private Prioridad prioridad;

    public Tiene(String nRegistro, Incidencia incidencia, Prioridad prioridad, String descripcion, Timestamp fechaRegistro) {
        this.nRegistro = nRegistro;
        this.incidencia = incidencia;
        this.prioridad = prioridad;
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
    }

    public Tiene() {
    }

    public String getnRegistro() {
        return nRegistro;
    }

    public void setnRegistro(String nRegistro) {
        this.nRegistro = nRegistro;
    }

    public Incidencia getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(Incidencia incidencia) {
        this.incidencia = incidencia;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
