package com.lazywork.entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UsuarioID")
    private Long id;

    @Column(name = "Nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "Apellido", length = 50, nullable = false)
    private String apellido;

    @Column(name = "Documento", length = 50, nullable = false)
    private String documento;

    @Column(name = "NivelSoporte", length = 50, nullable = false)  // Corrección: Eliminado espacio en el nombre
    private String NivelSoporte;

    // Constructor por defecto
    public Usuario() {
    }

    // Constructor con parámetros
    public Usuario(String nombre, String apellido, String documento, String NivelSoporte) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.NivelSoporte = NivelSoporte;
    }

    // Getters y setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return NivelSoporte;
    }

    public void setNivelSoporte(String nivelSoporte) {
        NivelSoporte = nivelSoporte;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", documento='" + documento + '\'' +
                ", NivelSoporte='" + NivelSoporte + '\'' +
                '}';
    }
}
