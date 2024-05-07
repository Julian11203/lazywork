package com.example.demo.persistence.entity.security;

import javax.persistence.*;


@Entity
@Table(name="usuarios")
public class User {
    @Id
    private String correoElectronico;
    @Column
    private String nombreCompleto;
    @Column
    private String rolActual;
    @Column
    private Boolean roleUser;
    @Column
    private Boolean roleAdmin;

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getRolActual() {
        return rolActual;
    }

    public void setRolActual(String rolActual) {
        this.rolActual = rolActual;
    }

    public Boolean getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(Boolean roleUser) {
        this.roleUser = roleUser;
    }

    public Boolean getRoleAdmin() {
        return roleAdmin;
    }

    public void setRoleAdmin(Boolean roleAdmin) {
        this.roleAdmin = roleAdmin;
    }
}
