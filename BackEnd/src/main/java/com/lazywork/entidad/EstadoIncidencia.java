package com.lazywork.entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "EstadosIncidencia")
public class EstadoIncidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EstadoID")
    private Long estadoID;

    @Column(name = "TipoEstado", nullable = false)
    private String tipoEstado;

    // Getters y Setters

    public Long getEstadoID() {
        return estadoID;
    }

    public void setEstadoID(Long estadoID) {
        this.estadoID = estadoID;
    }

    public String getTipoEstado() {
        return tipoEstado;
    }

    public void setTipoEstado(String tipoEstado) {
        this.tipoEstado = tipoEstado;
    }

    @Override
    public String toString() {
        return "EstadoIncidencia{" +
                "estadoID=" + estadoID +
                ", tipoEstado='" + tipoEstado + '\'' +
                '}';
    }
}