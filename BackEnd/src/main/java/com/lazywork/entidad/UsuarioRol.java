package com.lazywork.entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "UsuarioRol")
public class UsuarioRol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UsuarioRolID")
    private Long usuarioRolID;

    @ManyToOne
    @JoinColumn(name = "UsuarioID", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "RolID", nullable = false)
    private Rol rol;

    public UsuarioRol(Long usuarioRolID, Usuario usuario, Rol rol) {
        this.usuarioRolID = usuarioRolID;
        this.usuario = usuario;
        this.rol = rol;
    }

    public UsuarioRol() {
    }

    public Long getUsuarioRolID() {
        return usuarioRolID;
    }

    public void setUsuarioRolID(Long usuarioRolID) {
        this.usuarioRolID = usuarioRolID;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "UsuarioRol{" +
                "usuarioRolID=" + usuarioRolID +
                ", usuario=" + usuario +
                ", rol=" + rol +
                '}';
    }
}