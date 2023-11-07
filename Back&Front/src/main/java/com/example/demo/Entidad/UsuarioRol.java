package com.example.demo.Entidad;

import javax.persistence.*;

@Entity
@Table(name = "UsuarioRol")
public class UsuarioRol {

    @Id
    /*
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     */
    @Column(name = "UsuarioRolID")
    private Long usuarioRolID;

    @ManyToOne
    @JoinColumn(name = "Usuarioid", nullable = false)
    private Usuarioback usuarioback;

    @ManyToOne
    @JoinColumn(name = "RolID", nullable = false)
    private Rol rol;

    public UsuarioRol(Long usuarioRolID, Usuarioback usuarioback, Rol rol) {
        this.usuarioRolID = usuarioRolID;
        this.usuarioback = usuarioback;
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

    public Usuarioback getUsuario() {
        return usuarioback;
    }

    public void setUsuario(Usuarioback usuarioback) {
        this.usuarioback = usuarioback;
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
                ", usuario=" + usuarioback +
                ", rol=" + rol +
                '}';
    }
}