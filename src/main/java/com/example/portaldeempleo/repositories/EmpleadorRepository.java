package com.example.portaldeempleo.repositories;

import com.example.portaldeempleo.entities.Candidato;
import com.example.portaldeempleo.entities.Empleador;
import com.example.portaldeempleo.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadorRepository extends JpaRepository<Empleador, Integer> {

    public Empleador findByUsuario(Usuario usuario);

}
