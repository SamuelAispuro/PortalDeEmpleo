package com.example.portaldeempleo.services;

import com.example.portaldeempleo.DTO.RespuestaDTO;
import com.example.portaldeempleo.entities.Candidato;
import com.example.portaldeempleo.entities.Usuario;
import com.example.portaldeempleo.entities.Vacante;
import com.example.portaldeempleo.repositories.CandidatoRepository;
import com.example.portaldeempleo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PasswordEncryption passwordEncryption;
    @Autowired
    CandidatoRepository candidatoRepository;

    @Value("${ruta_local}") // La ruta local donde quieres almacenar las imágenes
    private String rutaLocal;
    //login
    public Usuario login(String correoElectronico, String contraseña){

        Usuario usuarioEncontrado = this.usuarioRepository.findByCorreoElectronicoAndContraseñaAndEstatusUsuario(correoElectronico, passwordEncryption.encryptText(contraseña),true);

        return usuarioEncontrado;
    }

    //Obtener usuario por ID
    public Usuario obtenerUsuarioPorId(Integer id){
        Usuario usuarioEncontrado = this.usuarioRepository.findById(id).orElse(null);
        if(usuarioEncontrado.getEstatusUsuario()==true){
            return usuarioEncontrado;
        }else{
            return null;
        }
    }

    //Guardar o modificar imagen de perfil
    public Usuario guardarArchivo(Integer id_usuario, String rutaImagenPerfil, String rutaImagenPortada, String rutaCv, String rutaEspecialidad){
        Usuario usuarioEncontrado = obtenerUsuarioPorId(id_usuario);

        if(rutaCv != null && rutaCv != ""){
            Candidato candidatoEncontrado = candidatoRepository.findByUsuario(usuarioEncontrado);
            candidatoEncontrado.setRutaCv(rutaCv);
            candidatoEncontrado = candidatoRepository.save(candidatoEncontrado);
        }
        if(rutaEspecialidad != null && rutaEspecialidad != ""){
            Candidato candidatoEncontrado = candidatoRepository.findByUsuario(usuarioEncontrado);
            candidatoEncontrado.setRutaCv(rutaCv);
            candidatoEncontrado.setRutaEspecialidad(rutaEspecialidad);
            candidatoEncontrado = candidatoRepository.save(candidatoEncontrado);
        }
        if(rutaImagenPerfil != null && rutaImagenPerfil != ""){
                usuarioEncontrado.setRutaImagenPerfil(rutaImagenPerfil);
            usuarioRepository.save(usuarioEncontrado);
        }
        if(rutaImagenPortada != null && rutaImagenPortada != ""){
                usuarioEncontrado.setRutaImagenPortada(rutaImagenPortada);
            usuarioRepository.save(usuarioEncontrado);
        }

        return usuarioEncontrado;
    }

    //Suspender cuenta de usuario
    public Usuario suspenderUsuario(Integer id_usuario){
        Usuario usuarioEncontrado = usuarioRepository.findById(id_usuario).orElse(null);
        usuarioEncontrado.setEstatusUsuario(false);
        usuarioRepository.save(usuarioEncontrado);
        return usuarioEncontrado;
    }

}

