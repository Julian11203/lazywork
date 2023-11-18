package com.example.demo.Entidad;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "IniciosSesion")
public class InicioSesion{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InicioID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UsuarioID", referencedColumnName = "UsuarioID", nullable = false)
    private Usuarioback usuarioback;


    @Column(nullable = false)
    private LocalDateTime tiempodesesion;

    @Column(name = "FechaHoraInicio", nullable = false)
    private LocalDateTime fechaHoraInicio;


    @Column(nullable = true)
    private LocalDateTime FechaHoraFin;
    // Constructor por defecto
    public InicioSesion() {
    }

    // Constructor con parámetros
    public InicioSesion(Usuarioback usuarioback, LocalDateTime fechaHoraInicio) {
        this.usuarioback = usuarioback;
        this.fechaHoraInicio = fechaHoraInicio;
    }

    // Getters y setters

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

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public void setTiempodesesion(LocalDateTime tiempodesesion) {
        this.tiempodesesion = tiempodesesion;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        FechaHoraFin = fechaHoraFin;
    }
// Puedes agregar otros métodos y personalizar la entidad según tus necesidades


    @Override
    public String toString() {
        return "InicioSesion{" +
                "id=" + id +
                ", usuarioback=" + usuarioback +
                ", Tiempodesesion=" + tiempodesesion +
                ", fechaHoraInicio=" + fechaHoraInicio +
                ", FechaHoraFin=" + FechaHoraFin +
                '}';
    }
}