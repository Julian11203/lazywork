package com.lazywork.entidad;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
@Entity
@Table(name = "inicio")
public class Inicio {
    @Id
    @Column(name = "idinicio", length = 20, nullable = false)
    private String idInicio;
    @Column(nullable = false)
    private Time hora;
    @Column(nullable = false)
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "iduser", referencedColumnName = "idUser", nullable = false)
    private Usuario usuario;

    public Inicio(String idInicio, Time hora, Date fecha, Usuario usuario) {
        this.idInicio = idInicio;
        this.hora = hora;
        this.fecha = fecha;
        this.usuario = usuario;
    }

    public Inicio() {
    }

    public Inicio(String idInicio) {
        this.idInicio = idInicio;
    }

    public String getIdInicio() {
        return idInicio;
    }

    public void setIdInicio(String idInicio) {
        this.idInicio = idInicio;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
