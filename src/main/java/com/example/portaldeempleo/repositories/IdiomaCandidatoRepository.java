package com.example.portaldeempleo.repositories;

import com.example.portaldeempleo.entities.Candidato;
import com.example.portaldeempleo.entities.IdiomaCandidato;
import com.example.portaldeempleo.entities.Postulacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IdiomaCandidatoRepository extends JpaRepository<IdiomaCandidato,Integer> {

    public List<IdiomaCandidato> findAllByCandidato(Candidato candidato);

}
