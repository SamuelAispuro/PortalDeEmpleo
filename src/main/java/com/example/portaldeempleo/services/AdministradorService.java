package com.example.portaldeempleo.services;

import com.example.portaldeempleo.DTO.DataDTO;
import com.example.portaldeempleo.DTO.RespuestaDTO;
import com.example.portaldeempleo.entities.Administrador;
import com.example.portaldeempleo.entities.Candidato;
import com.example.portaldeempleo.entities.Usuario;
import com.example.portaldeempleo.repositories.AdministradorRepository;
import com.example.portaldeempleo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;

@Service
public class AdministradorService {

    @Autowired
    AdministradorRepository administradorRepository;
    @Autowired
    UsuarioRepository usuarioRepository;


    // metodo para registrar un administrador

    public Integer registroAdministrador(String nombre, String apellidoP, String apellidoM, String correoElectronico, String telefono, String contraseña){

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setCorreoElectronico(correoElectronico);
        usuario.setContraseña(contraseña);
        usuario.setApellidoP(apellidoP);
        usuario.setApellidoM(apellidoM);
        usuario.setTelefono(telefono);
        usuario.setTipoUsuario(1); //"1" es el tipo de usuario de un administrador
        usuario = usuarioRepository.save(usuario);

        Administrador administrador = new Administrador();
        administrador.setUsuario(usuario);
        administrador = administradorRepository.save(administrador);

        return administrador.getId_administrador();
    }

    //Metodo para eliminar un usuario
    public void eliminarUsuario(Integer id_usuario){
    usuarioRepository.deleteById(id_usuario);

    }
    //Obtener datos de un usuario por correo
    public Usuario obtenerUsuario(String correoElectronico){
       Usuario usuarioEncontrado = usuarioRepository.findByCorreoElectronico(correoElectronico);
        return usuarioEncontrado;
    }
}
