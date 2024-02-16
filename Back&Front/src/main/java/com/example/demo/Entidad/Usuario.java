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
    private String rolDeUsuario;
    public Usuario() {
    }

    public Usuario(String correoElectronico, String nombreCompleto, String rolDeUsuario) {
        this.correoElectronico = correoElectronico;
        this.nombreCompleto = nombreCompleto;
        this.rolDeUsuario = rolDeUsuario;
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

    public String getRolDeUsuario() {
        return rolDeUsuario;
    }

    public void setRolDeUsuario(String rolDeUsuario) {
        this.rolDeUsuario = rolDeUsuario;
    }
}
