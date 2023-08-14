package com.example.portaldeempleo.repositories;

import com.example.portaldeempleo.entities.Municipio;
import com.example.portaldeempleo.entities.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacanteRepository extends JpaRepository<Vacante, Integer> {

    public List<Vacante> findAllByMunicipio(Municipio municipio);

}
