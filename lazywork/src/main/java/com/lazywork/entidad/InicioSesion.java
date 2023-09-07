package com.lazywork.entidad;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "IniciosSesion")
public class InicioSesion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InicioID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UsuarioID", referencedColumnName = "UsuarioID", nullable = false)
    private Usuario usuario;

    @Column(name = "Tiempodesesion", nullable = false)
    private LocalDateTime tiempodesesion; // Corregido el tipo de dato a LocalDateTime

    @Column(name = "FechaHoraInicio", nullable = false)
    private LocalDateTime fechaHoraInicio;

    @Column(nullable = true)
    private LocalDateTime FechaHoraFin;

    // Constructor por defecto
    public InicioSesion() {
    }

    // Constructor con parámetros
    public InicioSesion(Usuario usuario, LocalDateTime fechaHoraInicio) {
        this.usuario = usuario;
        this.fechaHoraInicio = fechaHoraInicio;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }


    public LocalDateTime getTiempodesesion() {
        return tiempodesesion;
    }

    public void setTiempodesesion(LocalDateTime tiempodesesion) {
        this.tiempodesesion = tiempodesesion;
    }

    public LocalDateTime getFechaHoraFin() {
        return FechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        FechaHoraFin = fechaHoraFin;
    }

    // Puedes agregar otros métodos y personalizar la entidad según tus necesidades

    @Override
    public String toString() {
        return "InicioSesion{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", Tiempodesesion=" + tiempodesesion +
                ", fechaHoraInicio=" + fechaHoraInicio +
                ", FechaHoraFin=" + FechaHoraFin +
                '}';
    }
}
