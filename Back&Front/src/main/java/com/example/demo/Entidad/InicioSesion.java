package com.example.demo.Entidad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "IniciosSesion")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

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
    private String fechaHoraInicio;


    @Column(name = "FechaHoraFin", nullable = false)
    private String fechaHoraFin;


    @Column(name = "Nombreuser", nullable = false)

    private String nombreuser;


    // Constructor por defecto
    public InicioSesion() {
    }

    public InicioSesion(Long id, Usuarioback usuarioback, String tiempodesesion, String fechaHoraInicio, String fechaHoraFin, String nombreuser) {
        this.id = id;
        this.usuarioback = usuarioback;
        this.tiempodesesion = tiempodesesion;
        this.fechaHoraInicio = fechaHoraInicio;
        this. fechaHoraFin = fechaHoraFin;
         this.nombreuser=nombreuser;

    }

    public InicioSesion(Usuarioback usuarioback, String tiempodesesion, String fechaHoraInicio, String fechaHoraFin, String nombreuser) {
        this.usuarioback = usuarioback;
        this.tiempodesesion = tiempodesesion;
        this. fechaHoraFin = fechaHoraFin;
        this.nombreuser=nombreuser;
    }

    public String getNombreuser() {
        return nombreuser;
    }

    public void setNombreuser(String nombreuser) {
        this.nombreuser = nombreuser;
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

    public String getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(String fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public String getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(String fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }



}