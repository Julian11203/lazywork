package com.example.demo.Entidad;

import javax.persistence.*;


@Entity
public class Usuario {
    @Id
    private String user_id;
    @Column
    private  String name;

    public Usuario() {
    }

    public Usuario(String user_id, String name) {
        this.user_id = user_id;
        this.name = name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
