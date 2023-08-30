package com.lazywork.entidad;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "estado")
public class Estado {
    @Id
    @Column(name = "idestado", length = 20, nullable = false)
    private String idEstado;
    @Column(name = "tipo_estado", length = 50, nullable = false)
    private String tipoEstado;

    public Estado(String idEstado, String tipoEstado) {
        this.idEstado = idEstado;
        this.tipoEstado = tipoEstado;
    }

    public Estado() {
    }

    public String getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public String getTipoEstado() {
        return tipoEstado;
    }

    public void setTipoEstado(String tipoEstado) {
        this.tipoEstado = tipoEstado;
    }

}
