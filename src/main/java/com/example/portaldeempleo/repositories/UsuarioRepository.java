package com.example.portaldeempleo.repositories;

import com.example.portaldeempleo.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    public Usuario findByCorreoElectronicoAndContraseña(String correoElectronico,String contraseña);
    public Usuario findByCorreoElectronico(String correoElectronico);
}
