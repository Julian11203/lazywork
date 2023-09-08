package com.lazywork.entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "Rol")
public class Rol {

    @Id
    /*
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     */
    @Column
    private Long rolID;

    @Column(nullable = false, length = 50)
    private String nombreRol;

    public Long getRolID() {
        return rolID;
    }

    public void setRolID(Long rolID) {
        this.rolID = rolID;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
}