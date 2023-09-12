package com.example.portaldeempleo.repositories;

import com.example.portaldeempleo.entities.Candidato;
import com.example.portaldeempleo.entities.HabilidadCandidato;
import com.example.portaldeempleo.entities.Postulacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabilidadCandidatoRepository extends JpaRepository<HabilidadCandidato,Integer> {

    public List<HabilidadCandidato> findAllByCandidato(Candidato candidato);

}
