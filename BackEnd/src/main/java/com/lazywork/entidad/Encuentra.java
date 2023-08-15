package com.lazywork.entidad;

import jakarta.persistence.*;

import java.sql.Timestamp;
@Entity
@Table
public class Encuentra {
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
    @JoinColumn(name = "idestado",referencedColumnName = "idEstado",nullable = false)
    private Estado estado;

    public Encuentra(String nRegistro, String descripcion, Timestamp fechaRegistro, Incidencia incidencia, Estado estado) {
        this.nRegistro = nRegistro;
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
        this.incidencia = incidencia;
        this.estado = estado;
    }

    public Encuentra() {
    }

    public String getnRegistro() {
        return nRegistro;
    }

    public void setnRegistro(String nRegistro) {
        this.nRegistro = nRegistro;
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

    public Incidencia getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(Incidencia incidencia) {
        this.incidencia = incidencia;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
