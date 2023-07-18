package com.example.portaldeempleo.services;

import com.example.portaldeempleo.DTO.RespuestaDTO;
import com.example.portaldeempleo.entities.Usuario;
import com.example.portaldeempleo.repositories.CandidatoRepository;
import com.example.portaldeempleo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PasswordEncryption passwordEncryption;

    //login
    public Usuario login(String correoElectronico, String contraseña){
        Usuario usuarioEncontrado = this.usuarioRepository.findByCorreoElectronicoAndContraseña(correoElectronico, passwordEncryption.encryptText(contraseña));

        return usuarioEncontrado;
    }


}
