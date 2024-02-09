package com.example.demo.Repositorio;

import com.example.demo.Entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioCrudRepository extends JpaRepository<Usuario, Long> {

    Usuario findByDocumento(Long documento);


}