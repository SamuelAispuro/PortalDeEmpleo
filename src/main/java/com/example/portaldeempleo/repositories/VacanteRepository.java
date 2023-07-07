package com.example.portaldeempleo.repositories;

import com.example.portaldeempleo.entities.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacanteRepository extends JpaRepository<Vacante, Integer> {
}
