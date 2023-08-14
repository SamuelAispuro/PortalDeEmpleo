package com.example.portaldeempleo.repositories;

import com.example.portaldeempleo.entities.Administrador;
import com.example.portaldeempleo.entities.Candidato;
import com.example.portaldeempleo.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador,Integer> {

    public Administrador findByUsuario(Usuario usuario);

}
