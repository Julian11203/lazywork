package com.example.demo.Entidad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "IniciosSesion")
@JsonIgnoreProperties({"usuarioback"})

public class InicioSesion{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UsuarioID", referencedColumnName = "UsuarioID", nullable = false)
    private Usuarioback usuarioback;


    @Column(nullable = false)
    private String tiempodesesion;

    @Column(name = "FechaHoraInicio", nullable = false)
    private LocalDateTime fechaHoraInicio;


    @Column(nullable = true)
    private LocalDateTime FechaHoraFin;
    // Constructor por defecto
    public InicioSesion() {
    }

    public InicioSesion(Long id, Usuarioback usuarioback, String tiempodesesion, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {
        this.id = id;
        this.usuarioback = usuarioback;
        this.tiempodesesion = tiempodesesion;
        this.fechaHoraInicio = fechaHoraInicio;
        FechaHoraFin = fechaHoraFin;
    }

    public InicioSesion(Usuarioback usuarioback, String tiempodesesion, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {
        this.usuarioback = usuarioback;
        this.tiempodesesion = tiempodesesion;
        this.fechaHoraInicio = fechaHoraInicio;
        FechaHoraFin = fechaHoraFin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuarioback getUsuarioback() {
        return usuarioback;
    }

    public void setUsuarioback(Usuarioback usuarioback) {
        this.usuarioback = usuarioback;
    }

    public String getTiempodesesion() {
        return tiempodesesion;
    }

    public void setTiempodesesion(String tiempodesesion) {
        this.tiempodesesion = tiempodesesion;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return FechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        FechaHoraFin = fechaHoraFin;
    }
}