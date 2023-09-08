package com.lazywork.entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "rol_usuario")
public class RolUsuario {

    @Id
    /*
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     */
    @Column
    private Long rolUsuarioID;

    @ManyToOne
    @JoinColumn(name = "UsuarioID", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "RolID", nullable = false)
    private Rol rol;


    public Long getRolUsuarioID() {
        return rolUsuarioID;
    }

    public void setRolUsuarioID(Long rolUsuarioID) {
        this.rolUsuarioID = rolUsuarioID;
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
}