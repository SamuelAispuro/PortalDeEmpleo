package com.example.portaldeempleo.repositories;

import com.example.portaldeempleo.entities.Candidato;
import com.example.portaldeempleo.entities.Habilidad;
import com.example.portaldeempleo.entities.Idioma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabilidadRepository extends JpaRepository<Habilidad, Integer> {



}
