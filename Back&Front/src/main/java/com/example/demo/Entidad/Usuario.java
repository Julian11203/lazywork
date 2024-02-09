package com.example.demo.Entidad;

import javax.persistence.*;


@Entity
public class Usuario {

    @Id
    private Long documento;
    @Column
    private  String nombre;

    @Column(unique = true)
    private String auth_id;

    @Column
    private String role;

    public Usuario() {
    }

    public Usuario(Long documento, String nombre, String auth_id, String role) {
        this.documento = documento;
        this.nombre = nombre;
        this.auth_id = auth_id;
        this.role = role;
    }

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAuth_id() {
        return auth_id;
    }

    public void setAuth_id(String auth_id) {
        this.auth_id = auth_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
