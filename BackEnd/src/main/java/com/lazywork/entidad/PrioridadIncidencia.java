package com.lazywork.entidad;


import jakarta.persistence.*;

@Entity
@Table(name = "PrioridadesIncidencia")
public class PrioridadIncidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PrioridadID")
    private Long prioridadID;

    @Column(name = "TipoPrioridad", nullable = false)
    private String tipoPrioridad;

    // Getters y Setters

    public Long getPrioridadID() {
        return prioridadID;
    }

    public void setPrioridadID(Long prioridadID) {
        this.prioridadID = prioridadID;
    }

    public String getTipoPrioridad() {
        return tipoPrioridad;
    }

    public void setTipoPrioridad(String tipoPrioridad) {
        this.tipoPrioridad = tipoPrioridad;
    }

    @Override
    public String toString() {
        return "PrioridadIncidencia{" +
                "prioridadID=" + prioridadID +
                ", tipoPrioridad='" + tipoPrioridad + '\'' +
                '}';
    }

    public void setId(Long id) {
    }
}