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
    private Boolean roleUser;
    @Column
    private Boolean roleAdmin;
    public Usuario() {
    }

    public Usuario(String correoElectronico, String nombreCompleto, Boolean roleUser, Boolean roleAdmin) {
        this.correoElectronico = correoElectronico;
        this.nombreCompleto = nombreCompleto;
        this.roleUser = roleUser;
        this.roleAdmin = roleAdmin;
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
}
