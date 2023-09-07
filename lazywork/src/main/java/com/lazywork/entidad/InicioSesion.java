package com.lazywork.entidad;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "inicio_sesion")
public class InicioSesion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InicioID")
    private Long inicioID;

    @ManyToOne
    @JoinColumn(name = "usuario_id") // Esto puede variar dependiendo de tu modelo de datos
    private UsuarioSistema usuario;

    // Agrega el campo usuarioid con su respectiva columna y relación con el usuario
    @Column(name = "usuarioid", nullable = false)
    private Long usuarioid;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraInicio = new Date();

    // Getters y Setters

    public Long getInicioID() {
        return inicioID;
    }

    public void setInicioID(Long inicioID) {
        this.inicioID = inicioID;
    }

    public UsuarioSistema getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioSistema usuario) {
        this.usuario = usuario;
        // Asigna el ID del usuario al campo 'usuarioid'
        this.usuarioid = usuario != null ? usuario.getUsuarioID() : null;
    }

    public Long getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(Long usuarioid) {
        this.usuarioid = usuarioid;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    @Override
    public String toString() {
        return "InicioSesion{" +
                "inicioID=" + inicioID +
                ", usuario=" + usuario +
                ", fechaHoraInicio=" + fechaHoraInicio +
                '}';
    }
}
