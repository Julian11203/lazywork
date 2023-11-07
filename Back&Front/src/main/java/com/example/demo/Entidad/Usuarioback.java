package com.example.demo.Entidad;

import javax.persistence.Entity;
import javax.persistence.*;
@Entity
@Table(name = "usuariosback")
public class Usuarioback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioid;
    @Column(nullable = false, length = 50)
    private String apellido;
    @Column(nullable = false, length = 50)
    private String correo;

    @Column(nullable = false, length = 50)
    private String documento;

    @Column(nullable = false, length = 50)
    private String nivel_soporte;
    @Column(nullable = false, length = 50)
    private String nombre;


    @Column(nullable = false, length = 50)
    private String telefono;

    public Usuarioback() {
    }

    public Usuarioback(Long usuarioid, String apellido, String correo, String documento, String nivel_soporte, String nombre, String telefono) {
        this.usuarioid = usuarioid;
        this.apellido = apellido;
        this.correo = correo;
        this.documento = documento;
        this.nivel_soporte = nivel_soporte;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Long getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(Long usuarioid) {
        this.usuarioid = usuarioid;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNivel_soporte() {
        return nivel_soporte;
    }

    public void setNivel_soporte(String nivel_soporte) {
        this.nivel_soporte = nivel_soporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
