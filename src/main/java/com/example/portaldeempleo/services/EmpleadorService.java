package com.example.portaldeempleo.services;

import com.example.portaldeempleo.entities.Candidato;
import com.example.portaldeempleo.entities.Empleador;
import com.example.portaldeempleo.entities.Usuario;
import com.example.portaldeempleo.repositories.EmpleadorRepository;
import com.example.portaldeempleo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadorService {

    @Autowired
    EmpleadorRepository empleadorRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PasswordEncryption passwordEncryption;

    //Metodo para registrar un empleador

    public Integer registroEmpleador(String nombre, String apellidoP, String apellidoM, String correoElectronico, String telefono, String contraseña){

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setCorreoElectronico(correoElectronico);
        usuario.setContraseña(passwordEncryption.encryptText(contraseña));
        usuario.setApellidoP(apellidoP);
        usuario.setApellidoM(apellidoM);
        usuario.setTelefono(telefono);
        usuario.setTipoUsuario(3); //"3" es el tipo de usuario de un empleador
        usuario = usuarioRepository.save(usuario);

        Empleador empleador = new Empleador();
        empleador.setUsuario(usuario);
        empleador = empleadorRepository.save(empleador);

        return empleador.getId_empleador();
    }

    //metodo para obtener todos los empleadores
    public List<Empleador> obtenerTodosEmpleadores(){
        return this.empleadorRepository.findAll();
    }

    //login
    public Usuario login(String correoElectronico, String contraseña){
        Usuario usuarioEncontrado = this.usuarioRepository.findByCorreoElectronicoAndContraseña(correoElectronico, contraseña);

        return usuarioEncontrado;
    }

}


