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
    private String fotoPerfil;
    @Column(unique = true)
    private String auth_id;
    @Column
    private String rolDeUsuario;
    public Usuario() {
    }
    public Usuario(String email, String name, String img, String auth_id, String role) {
        this.correoElectronico = email;
        this.nombreCompleto = name;
        this.fotoPerfil = img;
        this.auth_id = auth_id;
        this.rolDeUsuario = role;
    }
    public String getEmail() {
        return correoElectronico;
    }
    public void setEmail(String email) {
        this.correoElectronico = email;
    }
    public String getName() {
        return nombreCompleto;
    }
    public void setName(String name) {
        this.nombreCompleto = name;
    }
    public String getImg() {
        return fotoPerfil;
    }
    public void setImg(String img) {
        this.fotoPerfil = img;
    }
    public String getAuth_id() {
        return auth_id;
    }
    public void setAuth_id(String auth_id) {
        this.auth_id = auth_id;
    }
    public String getRole() {
        return rolDeUsuario;
    }
    public void setRole(String role) {
        this.rolDeUsuario = role;
    }
}
