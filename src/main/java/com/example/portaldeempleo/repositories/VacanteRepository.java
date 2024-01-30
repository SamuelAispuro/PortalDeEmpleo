package com.example.portaldeempleo.repositories;

import com.example.portaldeempleo.entities.Estado;
import com.example.portaldeempleo.entities.Municipio;
import com.example.portaldeempleo.entities.Vacante;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacanteRepository extends JpaRepository<Vacante, Integer> {

    public List<Vacante> findAllByMunicipio(Municipio municipio);
    public Page<Vacante> findAllByMunicipioAndEstatus(Municipio municipio,Boolean estatus, Pageable pageable);
    public List<Vacante> findAllByEstado(Estado estado);
    public Page<Vacante> findAllByEstadoAndEstatus(Estado estado,Boolean estatus, Pageable pageable);
    public List<Vacante> findAllByEstatus(Boolean estatus);
    public Page<Vacante> findAllByEstatus(Boolean estatus,Pageable pageable);
    
    
    //public Page<Vacante> findAllByEstatusAndNombreVacante(Boolean estatus,String nombreVacante,Pageable pageable);
    public Page<Vacante> findAllByEstadoAndEstatusAndNombreVacante(Estado estado,Boolean estatus, String nombreVacante, Pageable pageable);
    public Page<Vacante> findAllByMunicipioAndEstatusAndNombreVacante(Municipio municipio, Boolean estatus, String nombreVacante, Pageable pageable);


    @Query("SELECT v FROM Vacante v WHERE LOWER(v.nombreVacante) = LOWER(:nombreVacante) AND v.estatus = :estatus")
    Page<Vacante> findAllByEstatusAndNombreVacanteIgnoreCase(
            @Param("estatus") Boolean estatus,
            @Param("nombreVacante") String nombreVacante,
            Pageable pageable
    );
  

    @Modifying
    @Query("delete from Vacante v where v.id_vacante=:id")
    void deleteVacanteById(@Param("id") Integer idVacante);

}
