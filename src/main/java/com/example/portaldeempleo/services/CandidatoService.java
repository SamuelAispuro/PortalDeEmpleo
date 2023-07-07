package com.example.portaldeempleo.services;

import com.example.portaldeempleo.entities.Candidato;
import com.example.portaldeempleo.entities.Usuario;
import com.example.portaldeempleo.repositories.CandidatoRepository;
import com.example.portaldeempleo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatoService {

    @Autowired
    CandidatoRepository candidatoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    //metodo para registrar un candidato
public Integer registroCandidato(String nombre, String apellidoP, String apellidoM, String correoElectronico, String telefono, String contraseña ,Integer edad){

    Usuario usuario = new Usuario();
    usuario.setNombre(nombre);
    usuario.setCorreoElectronico(correoElectronico);
    usuario.setContraseña(contraseña);
    usuario.setApellidoP(apellidoP);
    usuario.setApellidoM(apellidoM);
    usuario.setTelefono(telefono);
    usuario.setTipoUsuario(2);
    usuario = usuarioRepository.save(usuario);

    Candidato candidato = new Candidato();
    candidato.setUsuario(usuario);
    candidato.setEdad(edad);
    candidato = candidatoRepository.save(candidato);

    return candidato.getId_candidato();
}

//metodo para obtener todos los candidatos
public List<Candidato> obtenerTodos(){
    return this.candidatoRepository.findAll();
}



}
