package com.example.portaldeempleo.repositories;

import com.example.portaldeempleo.entities.Candidato;
import com.example.portaldeempleo.entities.Postulacion;
import com.example.portaldeempleo.entities.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PostulacionRepository extends JpaRepository<Postulacion, Integer> {
    public void deleteByCandidato (Candidato candidato);
    @Transactional
    @Modifying
    @Query("DELETE FROM Postulacion p WHERE p.candidato = :candidato")
    void deleteWithRelationsById(Candidato candidato);

    public Postulacion findByCandidato(Candidato candidato);

    public List<Postulacion> findAllByCandidato(Candidato candidato);
    
    public List<Postulacion> findAllByVacante(Vacante vacante);

}
