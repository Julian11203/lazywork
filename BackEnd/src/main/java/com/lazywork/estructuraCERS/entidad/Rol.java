package com.lazywork.estructuraCERS.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Roles")
public class Rol {

    @Id
    /*
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     */
    @Column(name = "RolID")
    private Long rolID;

    @Column(name = "NombreRol", nullable = false)
    private String nombreRol;

    // Getters y Setters

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