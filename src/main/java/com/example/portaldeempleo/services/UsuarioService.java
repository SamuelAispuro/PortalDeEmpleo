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
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PasswordEncryption passwordEncryption;
    @Autowired
    CandidatoRepository candidatoRepository;

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
    public Usuario guardarArchivo(Integer id_usuario, String rutaImagenPerfil, String rutaImagenPortada, String rutaCv, String rutaEspecialidad, String rutaEspecialidad2, String rutaEspecialidad3, String descripcionEspecialidad1, String descripcionEspecialidad2, String descripcionEspecialidad3){
        Usuario usuarioEncontrado = obtenerUsuarioPorId(id_usuario);

        if(rutaCv != null && rutaCv != ""){
            Candidato candidatoEncontrado = candidatoRepository.findByUsuario(usuarioEncontrado);
            candidatoEncontrado.setRutaCv(rutaCv);
            candidatoEncontrado = candidatoRepository.save(candidatoEncontrado);
        }
        if(rutaEspecialidad != null && rutaEspecialidad != ""){
            Candidato candidatoEncontrado = candidatoRepository.findByUsuario(usuarioEncontrado);
            candidatoEncontrado.setRutaEspecialidad(rutaEspecialidad);
            candidatoEncontrado = candidatoRepository.save(candidatoEncontrado);
        }
        if(descripcionEspecialidad1!=null && descripcionEspecialidad1!=""){
            Candidato candidatoEncontrado = candidatoRepository.findByUsuario(usuarioEncontrado);
            candidatoEncontrado.setDescripcionEspecialidad1(descripcionEspecialidad1);
            candidatoRepository.save(candidatoEncontrado);
        }
        if(rutaEspecialidad2 != null && rutaEspecialidad2 != ""){
            Candidato candidatoEncontrado = candidatoRepository.findByUsuario(usuarioEncontrado);
            candidatoEncontrado.setRutaEspecialidad2(rutaEspecialidad2);
            candidatoEncontrado = candidatoRepository.save(candidatoEncontrado);
        }
        if(descripcionEspecialidad2!=null && descripcionEspecialidad2!=""){
            Candidato candidatoEncontrado = candidatoRepository.findByUsuario(usuarioEncontrado);
            candidatoEncontrado.setDescripcionEspecialidad2(descripcionEspecialidad2);
            candidatoRepository.save(candidatoEncontrado);
        }
        if(rutaEspecialidad3 != null && rutaEspecialidad3 != ""){
            Candidato candidatoEncontrado = candidatoRepository.findByUsuario(usuarioEncontrado);
            candidatoEncontrado.setRutaEspecialidad3(rutaEspecialidad3);
            candidatoEncontrado = candidatoRepository.save(candidatoEncontrado);
        }
        if(descripcionEspecialidad3!=null && descripcionEspecialidad3!=""){
            Candidato candidatoEncontrado = candidatoRepository.findByUsuario(usuarioEncontrado);
            candidatoEncontrado.setDescripcionEspecialidad3(descripcionEspecialidad3);
            candidatoRepository.save(candidatoEncontrado);
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
    
    public boolean buscarCorreo(String correo) {
   	   boolean encontrado = false;
   	  Usuario usuario = usuarioRepository.findByCorreoElectronico(correo);
   	  
   	  if(usuario == null) {
   		  encontrado = false; 
   		  	return encontrado;
   	  } else {
   		  encontrado = true; 
   		  	return encontrado;
   	  }
   }
    
	  public boolean cambiarContra(String correo, String contraseña){
     	   boolean cambio = false;
     	   Usuario usuario = usuarioRepository.findByCorreoElectronico(correo);
     	  if(usuario == null) {
     		 cambio = false; 
       	 	 return cambio;
       	  } else {
       		 usuario.setContraseña(passwordEncryption.encryptText(contraseña));
       		 usuarioRepository.save(usuario);
       		 cambio = true; 
       	 	 return cambio;
       	  }
  	  }

    //Metodo para obtener lista de todos los usuarios por actividad
    public List<Usuario> obtenerListaUsuariosFiltro(String tipoFiltro){
        List<Usuario> listaUsuarios = new ArrayList<>();
        if (tipoFiltro.equalsIgnoreCase("activos")) {
            listaUsuarios = usuarioRepository.findAllByEstatusUsuario(true);
        } else if (tipoFiltro.equalsIgnoreCase("inactivos")) {
            listaUsuarios = usuarioRepository.findAllByEstatusUsuario(false);
        }
        return listaUsuarios;
    }

    //Metodo para obtener lista de usuarios por rol
    public List<Usuario> obtenerListaUsuariosRol(String rol){
        List<Usuario> listaUsuariosTodos = new ArrayList<>();
        listaUsuariosTodos = usuarioRepository.findAll();
        List<Usuario> listaUsuariosRol = new ArrayList<>();
        if (rol.equalsIgnoreCase("administrador")){
            for (Usuario usuario:listaUsuariosTodos){
                if (usuario.getTipoUsuario()==1){
                    listaUsuariosRol.add(usuario);
                }
            }
        }
        if (rol.equalsIgnoreCase("candidato")){
            for (Usuario usuario:listaUsuariosTodos){
                if (usuario.getTipoUsuario()==2){
                    listaUsuariosRol.add(usuario);
                }
            }
        }
        if (rol.equalsIgnoreCase("empleador")){
            for (Usuario usuario:listaUsuariosTodos){
                if (usuario.getTipoUsuario()==3){
                    listaUsuariosRol.add(usuario);
                }
            }
        }
        return listaUsuariosRol;
    }

}

