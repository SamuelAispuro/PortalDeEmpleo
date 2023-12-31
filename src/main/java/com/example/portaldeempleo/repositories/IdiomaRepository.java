package com.example.portaldeempleo.repositories;

import com.example.portaldeempleo.entities.Candidato;
import com.example.portaldeempleo.entities.Idioma;
import com.example.portaldeempleo.entities.IdiomaCandidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdiomaRepository extends JpaRepository<Idioma, Integer> {

}
