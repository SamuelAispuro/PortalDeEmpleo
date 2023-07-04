package com.example.portaldeempleo.repositories;

import com.example.portaldeempleo.entities.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Integer> {
}
