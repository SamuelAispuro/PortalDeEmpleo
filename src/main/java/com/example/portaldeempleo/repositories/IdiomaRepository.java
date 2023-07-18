package com.example.portaldeempleo.repositories;

import com.example.portaldeempleo.entities.Idioma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdiomaRepository extends JpaRepository<Idioma, Integer> {
}
