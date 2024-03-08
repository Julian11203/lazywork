package com.example.demo.Entidad;

import javax.persistence.*;


@Entity
@Table(name="usuarios")
public class Usuario {
    @Id
    private String correoElectronico;
    @Column
    private  String nombreCompleto;
    @Column
    private String rolActual;
    @Column
    private Boolean roleUser;
    @Column
    private Boolean roleAdmin;
    @Column
    private String token;
    public Usuario() {
    }

    public Usuario(String correoElectronico, String nombreCompleto, String rolActual, Boolean roleUser, Boolean roleAdmin, String token) {
        this.correoElectronico = correoElectronico;
        this.nombreCompleto = nombreCompleto;
        this.rolActual = rolActual;
        this.roleUser = roleUser;
        this.roleAdmin = roleAdmin;
        this.token = token;
    }

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

    public String getRolActual() {
        return rolActual;
    }

    public void setRolActual(String rolActual) {
        this.rolActual = rolActual;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
