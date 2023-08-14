package com.example.portaldeempleo.repositories;

import com.example.portaldeempleo.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
}
