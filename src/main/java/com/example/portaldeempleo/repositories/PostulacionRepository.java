package com.example.portaldeempleo.repositories;

import com.example.portaldeempleo.entities.Postulacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostulacionRepository extends JpaRepository<Postulacion, Integer> {
}
