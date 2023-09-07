package com.lazywork.entidad;

import jakarta.persistence.*;

@Entity
@Table
public class Usuario {

    @Id
    /*
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     */
    private Long usuarioID;

    @Column(nullable = false, length = 50)
    private String codigoUnico;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellido;

    @Column(nullable = false, length = 50)
    private String documento;

    @Column(nullable = false, length = 50)
    private String nivelSoporte;

    @Column(nullable = false, length = 50)
    private Integer tiempoUsoSistemaMinutos;



    public Long getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Long usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
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

    public String getNivelSoporte() {
        return nivelSoporte;
    }

    public void setNivelSoporte(String nivelSoporte) {
        this.nivelSoporte = nivelSoporte;
    }

    public Integer getTiempoUsoSistemaMinutos() {
        return tiempoUsoSistemaMinutos;
    }

    public void setTiempoUsoSistemaMinutos(Integer tiempoUsoSistemaMinutos) {
        this.tiempoUsoSistemaMinutos = tiempoUsoSistemaMinutos;
    }
}