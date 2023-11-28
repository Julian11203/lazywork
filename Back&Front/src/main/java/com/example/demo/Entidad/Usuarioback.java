package com.example.demo.Entidad;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.*;
@Entity
@Table(name = "Usuariosback")
public class Usuarioback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UsuarioID")
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellido;
    @Column(nullable = false, length = 50)
    private String documento;
    @Column(nullable = false, length = 50)
    private String correo;

    @Column(nullable = false, length = 50)
    private String telefono;

    @Column(nullable = false, length = 50)
    private String tipoderol;


    @NotNull
    private String nivelSoporte;


    public Usuarioback() {
    }

    public Usuarioback(Long id, String nombre, String apellido, String documento, String correo, String telefono, String tipoderol, String nivelSoporte) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.correo = correo;
        this.telefono = telefono;
        this.tipoderol = tipoderol;
        this.nivelSoporte = nivelSoporte;
    }

    public Usuarioback(String nombre, String apellido, String documento, String correo, String telefono, String tipoderol, String nivelSoporte) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.correo = correo;
        this.telefono = telefono;
        this.tipoderol = tipoderol;
        this.nivelSoporte = nivelSoporte;
    }

    public String getNivelSoporte() {
        return nivelSoporte;
    }

    public void setNivelSoporte(String nivelSoporte) {
        this.nivelSoporte = nivelSoporte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long usuarioid) {
        this.id = usuarioid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTipoderol() {
        return tipoderol;
    }

    public void setTipoderol(String tipoderol) {
        this.tipoderol = tipoderol;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Usuarioback{" +
                "id=" + id +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                ", documento='" + documento + '\'' +
                ", tipoderol='" + tipoderol + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }

    public String getRol() {
        return this.tipoderol; // Suponiendo que 'tipoderol' es el campo que almacena el rol en la clase Usuarioback
    }

}
