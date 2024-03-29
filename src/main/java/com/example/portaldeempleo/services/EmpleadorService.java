package com.example.portaldeempleo.services;

import com.example.portaldeempleo.DTO.RespPostDTO;
import com.example.portaldeempleo.dominio.FormatoExcel;
import com.example.portaldeempleo.entities.*;
import com.example.portaldeempleo.repositories.EmpleadorRepository;
import com.example.portaldeempleo.repositories.PostulacionRepository;
import com.example.portaldeempleo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
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
    @Autowired
    PostulacionRepository postulacionRepository;

    //Metodo para registrar un empleador

    public Empleador registroEmpleador(String nombre, String apellidoP, String apellidoM, String correoElectronico,String telefono, String contraseña, String rutaImagenPerfil, String rutaImagenPortada){
        Usuario usuarioEncontrado = usuarioRepository.findByCorreoElectronicoAndEstatusUsuario(correoElectronico, true);
        if(usuarioEncontrado == null) {
            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setCorreoElectronico(correoElectronico);
            usuario.setTelefono(telefono);
            usuario.setContraseña(passwordEncryption.encryptText(contraseña));
            usuario.setApellidoP(apellidoP);
            usuario.setApellidoM(apellidoM);
            usuario.setEstatusUsuario(true);
            usuario.setRutaImagenPerfil(rutaImagenPerfil);
            usuario.setRutaImagenPortada(rutaImagenPortada);
            usuario.setTipoUsuario(3); //"3" es el tipo de usuario de un empleador
            usuario = usuarioRepository.save(usuario);

            //Se crea un objeto de tipo empleador y se le agrega la información capturada anteriormente.
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

        FormatoExcel formatoExcel = new FormatoExcel();
        formatoExcel.generateExcelReport(listaEmpleadoresTodos, "C:\\Users\\52667\\Desktop\\Reportes\\reporteExcel_.xlsx");

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

    //Obtener vacantes publicadas de un empleador
    public List<Vacante> vacantesPublicadasPorId(Integer id_empleador) {

        Empleador empleadorEncontrado = this.empleadorRepository.findById(id_empleador).orElse(null);
        List<Vacante> listaVacantes = empleadorEncontrado.getVacantes();
        empleadorEncontrado.setVacantes(null);
        for(Vacante vacante:listaVacantes){
        vacante.setCandidatos(null);
        vacante.getMunicipio().setVacantes_municipios(null);
        vacante.getMunicipio().getEstado().setMunicipios(null);
        vacante.getEmpresa().setVacantes_empresa(null);
        vacante.getModalidadTrabajo().setModalidadTrabajo_vacante(null);
        vacante.getTipoContratacion().setTipoContratacion_vacantes(null);
        vacante.getTipoHorario().setTipoHorario_vacantes(null);
        vacante.getMunicipio().getEstado().setVacantes_estado(null);
        vacante.getEstado().setVacantes_estado(null);
        vacante.getEstado().setMunicipios(null);
        }
        return listaVacantes;
    }

    //Aceptar postulación
    public Postulacion aceptarPostulacion(Integer id_postulacion){
        RespPostDTO respuesta = new RespPostDTO();
        Postulacion postulacionEncontrada = this.postulacionRepository.findById(id_postulacion).orElse(null);
        postulacionEncontrada.setEstatus(2); //2 corresponde a un estatus "Aceptado"
        postulacionEncontrada = this.postulacionRepository.save(postulacionEncontrada);
        respuesta.setEstatus(true);
        respuesta.setMensaje("La postulación fue aceptada con exito");

        return postulacionEncontrada;
    }

    //Rechazar postulación
    public RespPostDTO rechazarPostulacion(Integer id_postulacion){
        RespPostDTO respuesta = new RespPostDTO();
        Postulacion postulacionEncontrada = this.postulacionRepository.findById(id_postulacion).orElse(null);
        postulacionEncontrada.setEstatus(3); //2 corresponde a un estatus "Rechazado"

        respuesta.setEstatus(true);
        respuesta.setMensaje("La postulación fue rechazada con exito");
        return respuesta;
    }

    //OBTENER LISTA EMPLEADORES
    public List<Empleador> obtenerListaEmpleadores(){

        List<Empleador> listaEmpleadores = empleadorRepository.findAll();
        for(Empleador empleador:listaEmpleadores){
            empleador.setVacantes(null);
        }

        return listaEmpleadores;

    }

    //OBTENER LISTA EMPLEADORES ACTIVOS
    public List<Empleador> obtenerListaEmpleadoresActivos(){

        List<Empleador> listaEmpleadores = empleadorRepository.findAll();
        List<Empleador> listaEmpleadoresActivos = new ArrayList<>();

        for(Empleador empleador:listaEmpleadores){
            empleador.setVacantes(null);
            if(empleador.getUsuario().getEstatusUsuario() == true){
                listaEmpleadoresActivos.add(empleador);
            }
        }
        return listaEmpleadoresActivos;
    }

    //OBTENER LISTA EMPLEADORES INACTIVOS
    public List<Empleador> obtenerListaEmpleadoresInactivos(){

        List<Empleador> listaEmpleadores = empleadorRepository.findAll();
        List<Empleador> listaEmpleadoresInactivos = new ArrayList<>();

        for(Empleador empleador:listaEmpleadores){
            empleador.setVacantes(null);
            if(empleador.getUsuario().getEstatusUsuario() == false){
                listaEmpleadoresInactivos.add(empleador);
            }
        }
        return listaEmpleadoresInactivos;
    }

}


