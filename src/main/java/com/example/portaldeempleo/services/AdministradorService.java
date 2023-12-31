package com.example.portaldeempleo.services;

import com.example.portaldeempleo.DTO.DataDTO;
import com.example.portaldeempleo.DTO.RespuestaDTO;
import com.example.portaldeempleo.entities.*;
import com.example.portaldeempleo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;

@Service
public class AdministradorService {

    @Autowired
    AdministradorRepository administradorRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    CandidatoRepository candidatoRepository;
    @Autowired
    PostulacionRepository postulacionRepository;
    @Autowired
    PasswordEncryption passwordEncryption;
    @Autowired
    EmpleadorRepository empleadorRepository;

    // metodo para registrar un administrador

    public Administrador registroAdministrador(String nombre, String apellidoP, String apellidoM, String correoElectronico, String telefono, String contraseña, String rutaImagenPerfil, String rutaImagenPortada){
    Usuario usuarioEncontrado = usuarioRepository.findByCorreoElectronicoAndEstatusUsuario(correoElectronico, true);
    if(usuarioEncontrado == null) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setCorreoElectronico(correoElectronico);
        usuario.setContraseña(passwordEncryption.encryptText(contraseña));
        usuario.setApellidoP(apellidoP);
        usuario.setApellidoM(apellidoM);
        usuario.setEstatusUsuario(true);
        usuario.setTelefono(telefono);
        usuario.setRutaImagenPerfil(rutaImagenPerfil);
        usuario.setRutaImagenPortada(rutaImagenPortada);
        usuario.setTipoUsuario(1); //"1" es el tipo de usuario de un administrador
        usuario = usuarioRepository.save(usuario);

        Administrador administrador = new Administrador();
        administrador.setUsuario(usuario);
        administrador = administradorRepository.save(administrador);

        return administrador;
    }
    return null;
    }

    //Metodo para eliminar un usuario
    public void eliminarUsuario(Integer id_usuario){
        Usuario usuario = usuarioRepository.findById(id_usuario).orElse(null);

        usuarioRepository.delete(usuario);
    }
    //Obtener datos de un usuario por correo
    public Usuario obtenerUsuario(String correoElectronico){
       Usuario usuarioEncontrado = usuarioRepository.findByCorreoElectronico(correoElectronico);
        return usuarioEncontrado;
    }
    //Obtener datos completos de un usuario por correo
    public Object obtenerUsuarioCompleto(String correoElectronico){
        Usuario usuarioEncontrado = usuarioRepository.findByCorreoElectronico(correoElectronico);
        if(usuarioEncontrado==null){
            return null;
        }
        Object objetoEncontrado = new Object();
        if(usuarioEncontrado.getTipoUsuario()==2) {

            Candidato candidatoEncontrado = candidatoRepository.findByUsuario(usuarioEncontrado);
            candidatoEncontrado.getEstado().setMunicipios(null);
            candidatoEncontrado.setPostulaciones(null);
            candidatoEncontrado.getMunicipio().setVacantes_municipios(null);
            candidatoEncontrado.getMunicipio().getEstado().setVacantes_estado(null);
            candidatoEncontrado.getEstado().setVacantes_estado(null);
            candidatoEncontrado.getEstado().setVacantes_estado(null);
            for(Idioma idioma: candidatoEncontrado.getIdiomas()){
                idioma.setCandidatos(null);
            }
            for(Habilidad habilidad: candidatoEncontrado.getHabilidades()){
                habilidad.setCandidatos(null);
            }

             objetoEncontrado = candidatoEncontrado;
        }
        if(usuarioEncontrado.getTipoUsuario()==3){

            Empleador empleadorEncontrado = empleadorRepository.findByUsuario(usuarioEncontrado);
            empleadorEncontrado.setVacantes(null);
            objetoEncontrado = empleadorEncontrado;
        }
        if(usuarioEncontrado.getTipoUsuario()==1){

            Administrador administradorEncontrado = administradorRepository.findByUsuario(usuarioEncontrado);
            objetoEncontrado = administradorEncontrado;
        }

        return objetoEncontrado;
    }
}
