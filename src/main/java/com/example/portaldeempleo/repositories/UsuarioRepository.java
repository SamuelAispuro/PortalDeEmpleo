package com.example.portaldeempleo.repositories;

import com.example.portaldeempleo.entities.Usuario;
import com.example.portaldeempleo.entities.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    public Usuario findByCorreoElectronicoAndContraseñaAndEstatusUsuario(String correoElectronico,String contraseña,Boolean estatusUsuario);
    public Usuario findByCorreoElectronico(String correoElectronico);
    public Usuario findByCorreoElectronicoAndEstatusUsuario(String correoElectronico, Boolean estatusUsuario);

    public List<Usuario> findAllByEstatusUsuario(Boolean estatusUsuario);
}
