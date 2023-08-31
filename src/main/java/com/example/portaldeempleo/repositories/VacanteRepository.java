package com.example.portaldeempleo.repositories;

import com.example.portaldeempleo.entities.Estado;
import com.example.portaldeempleo.entities.Municipio;
import com.example.portaldeempleo.entities.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacanteRepository extends JpaRepository<Vacante, Integer> {

    public List<Vacante> findAllByMunicipio(Municipio municipio);
    public List<Vacante> findAllByEstado(Estado estado);
    public List<Vacante> findAllByEstatus(Boolean estatus);

    @Modifying
    @Query("delete from Vacante v where v.id_vacante=:id")
    void deleteVacanteById(@Param("id") Integer idVacante);

}
