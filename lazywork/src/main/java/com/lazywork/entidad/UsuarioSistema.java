package com.lazywork.entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "UsuariosSistema")
public class UsuarioSistema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UsuarioID")
    private Long usuarioID;

    @Column(name = "CodigoUnico", unique = true, nullable = false)
    private String codigoUnico;

    @Column(name = "Nombre", nullable = false)
    private String nombre;

    @Column(name = "Apellido", nullable = false)
    private String apellido;

    @Column(name = "Documento", nullable = false)
    private String documento;

    @Column(name = "NivelSoporte", nullable = false)
    private String nivelSoporte;

    @Column(name = "TiempoUsoSistemaMinutos")
    private Integer tiempoUsoSistemaMinutos;

    // Getters y Setters

    public Long getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Long usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNivelSoporte() {
        return nivelSoporte;
    }

    public void setNivelSoporte(String nivelSoporte) {
        this.nivelSoporte = nivelSoporte;
    }

    public Integer getTiempoUsoSistemaMinutos() {
        return tiempoUsoSistemaMinutos;
    }

    public void setTiempoUsoSistemaMinutos(Integer tiempoUsoSistemaMinutos) {
        this.tiempoUsoSistemaMinutos = tiempoUsoSistemaMinutos;
    }
    private String nombreDeUsuario;

    public UsuarioSistema(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
        // Puedes agregar más lógica de inicialización aquí si es necesario
    }

    // Getters y setters para la propiedad nombreDeUsuario
    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    @Override
    public String toString() {
        return "UsuarioSistema{" +
                "usuarioID=" + usuarioID +
                ", codigoUnico='" + codigoUnico + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", documento='" + documento + '\'' +
                ", nivelSoporte='" + nivelSoporte + '\'' +
                ", tiempoUsoSistemaMinutos=" + tiempoUsoSistemaMinutos +
                '}';
    }
}