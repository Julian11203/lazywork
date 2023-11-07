package com.example.demo.Entidad;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "Roles")
public class Rol {

    @Id
    /*
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     */
    @Column(name = "RolID")
    private String rolID;

    @Column(name = "NombreRol", nullable = false)
    private String nombreRol;


    // Getters y Setters

    public String getRolID() {
        return rolID;
    }

    public void setRolID(String rolID) {
        this.rolID = rolID;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
}