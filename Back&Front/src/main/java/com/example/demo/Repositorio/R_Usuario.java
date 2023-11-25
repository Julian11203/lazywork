package com.example.demo.Repositorio;

import com.example.demo.Entidad.E_Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface R_Usuario extends JpaRepository<E_Usuario,String> {

   E_Usuario findByEmail(String email);
}
