package com.example.portaldeempleo.services;

import com.example.portaldeempleo.entities.Candidato;
import com.example.portaldeempleo.entities.Empleador;
import com.example.portaldeempleo.entities.Usuario;
import com.example.portaldeempleo.entities.Vacante;
import com.example.portaldeempleo.repositories.EmpleadorRepository;
import com.example.portaldeempleo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Empleador registroEmpleador(String nombre, String apellidoP, String apellidoM, String correoElectronico, String contraseña){
        Usuario usuarioEncontrado = usuarioRepository.findByCorreoElectronicoAndEstatusUsuario(correoElectronico, true);
        if(usuarioEncontrado == null) {
            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setCorreoElectronico(correoElectronico);
            usuario.setContraseña(passwordEncryption.encryptText(contraseña));
            usuario.setApellidoP(apellidoP);
            usuario.setApellidoM(apellidoM);
            usuario.setTipoUsuario(3); //"3" es el tipo de usuario de un empleador
            usuario = usuarioRepository.save(usuario);

            Empleador empleador = new Empleador();
            empleador.setUsuario(usuario);
            empleador = empleadorRepository.save(empleador);

            return empleador;
        }
        return null;
    }

    //metodo para obtener todos los empleadores
    public List<Empleador> obtenerEmpleadoresTodos(){
        List<Empleador> listaEmpleadoresTodos = new ArrayList<>();
        listaEmpleadoresTodos = empleadorRepository.findAll();
        for(Empleador empleador:listaEmpleadoresTodos){
            empleador.setVacantes(null);

        }
        return listaEmpleadoresTodos;
    }

    //login
    public Usuario login(String correoElectronico, String contraseña){
        Usuario usuarioEncontrado = this.usuarioRepository.findByCorreoElectronicoAndContraseñaAndEstatusUsuario(correoElectronico, contraseña,true);

        return usuarioEncontrado;
    }

}


