package com.example.portaldeempleo.services;

import com.example.portaldeempleo.DTO.DataDTO;
import com.example.portaldeempleo.DTO.HabilidadDTO;
import com.example.portaldeempleo.DTO.IdiomaDTO;
import com.example.portaldeempleo.entities.*;
import com.example.portaldeempleo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

@Service
public class CandidatoService {

    @Autowired
    CandidatoRepository candidatoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    VacanteRepository vacanteRepository;
    @Autowired
    PostulacionRepository postulacionRepository;
    @Autowired
    PasswordEncryption passwordEncryption;
    @Autowired
    MunicipioRepository municipioRepository;
    @Autowired
    EstadoRepository estadoRepository;
    @Autowired
    IdiomaRepository idiomaRepository;
    @Autowired
    HabilidadRepository habilidadRepository;
    @Autowired
    IdiomaCandidatoRepository idiomaCandidatoRepository;
    @Autowired
    HabilidadCandidatoRepository habilidadCandidatoRepository;

    @Value("${ruta_local}") // La ruta local donde quieres almacenar las imágenes
    private String rutaLocal;

    //metodo para registrar un candidato
public Candidato registroCandidato(String nombre, String apellidoP, String apellidoM, String correoElectronico, String telefono, String contraseña , LocalDate fechaNacimiento, Integer id_municipio, Integer id_estado, String domicilio, String puestoActual, String descripcion, String centroEducativo){
    Usuario usuarioEncontrado = usuarioRepository.findByCorreoElectronicoAndEstatusUsuario(correoElectronico, true);
    //Se crea un nuevo usuario y se le setean los datos ingresados por el usuario al momento del registro
     if(usuarioEncontrado==null) {
         Usuario usuario = new Usuario();
         usuario.setNombre(nombre);
         usuario.setCorreoElectronico(correoElectronico);
         usuario.setContraseña(passwordEncryption.encryptText(contraseña));
         usuario.setApellidoP(apellidoP);
         usuario.setApellidoM(apellidoM);
         usuario.setTelefono(telefono);
         usuario.setEstatusUsuario(true); //true corresponde al estado activo de un usuario, false corresponde a un estado inactivo
         usuario.setTipoUsuario(2); //"2" es el tipo de usuario de un candidato y se le asigna automaticamente al hacer uso de este servicio
         usuario.setEstatusUsuario(true);
         usuario = usuarioRepository.save(usuario);
     //Se crean objetos de tipo estado y municipio para posteriormente setearselo a nuestro candidato
         Estado estado = new Estado();
         estado.setId_estado(id_estado);

         Municipio municipio = new Municipio();
         municipio.setId_municipio(id_municipio);
     //Se crea un objeto de tipo candidato y se le setean los objetos anteriormente creados(usuario, estado y municipio)
         Candidato candidato = new Candidato();

         candidato.setMunicipio(municipio);
         candidato.setEstado(estado);
         candidato.setUsuario(usuario);
         candidato.setFechaNacimiento(fechaNacimiento);
         candidato.setDomicilio(domicilio);
         candidato.setPuestoActual(puestoActual);
         candidato.setDescripcion(descripcion);
         candidato.setCentroEducativo(centroEducativo);

         candidato = candidatoRepository.save(candidato);

         return candidato;
     }else{
         return null;
     }

}
    //Obtener candidato por ID
    public Candidato obtenerCandidatoPorId(Integer id){
    //Se encuentra un candidato mediante su ID
    Candidato candidatoEncontrado = this.candidatoRepository.findById(id).orElse(null);
    //Se nulean datos que contiene el onjeto candidato para que no cause errores de recursion infinita
    candidatoEncontrado.setPostulaciones(null);
    candidatoEncontrado.getMunicipio().setEstado(null);
    candidatoEncontrado.getMunicipio().setVacantes_municipios(null);
    candidatoEncontrado.getEstado().setMunicipios(null);

    return candidatoEncontrado;
    }

    //Obtener postulaciones de un candidato
    public List<Postulacion> obtenerPostulacionesPorIdDeCandidato(Integer id){
        //Se encuentra un candidato mediante su ID para posteriormente visualizar las vacantes a las que se ha postulado
        Candidato candidato = candidatoRepository.findById(id).orElse(null);
       List<Postulacion> listaPostulacionesCandidato = postulacionRepository.findAllByCandidato(candidato);
       //Se itera la lista de postulaciones y se le nulean datos para que no ocasione errores de recursion infinita
        for(Postulacion postulacion:listaPostulacionesCandidato){
            postulacion.getCandidato().setPostulaciones(null);
            postulacion.getCandidato().getMunicipio().setVacantes_municipios(null);
            postulacion.getCandidato().getEstado().setMunicipios(null);
            postulacion.getVacante().getEmpresa().setVacantes_empresa(null);
            postulacion.getVacante().setCandidatos(null);
            postulacion.getCandidato().setIdiomas(null);
            postulacion.getVacante().getMunicipio().setVacantes_municipios(null);
            postulacion.getVacante().getMunicipio().getEstado().setMunicipios(null);
            postulacion.getVacante().getEmpleador().setVacantes(null);
            postulacion.getVacante().getTipoHorario().setTipoHorario_vacantes(null);
            postulacion.getVacante().getTipoContratacion().setTipoContratacion_vacantes(null);
            postulacion.getVacante().getModalidadTrabajo().setModalidadTrabajo_vacante(null);
            postulacion.getVacante().getEstado().setVacantes_estado(null);
            postulacion.getCandidato().getEstado().setVacantes_estado(null);
        }
        return listaPostulacionesCandidato;
    }
    //Modificar un candidato
    public Candidato modificarCandidato(Integer id_candidato,String nombre, String apellidoP, String apellidoM,String domicilio, String descripcion, String centroEducativo, String puestoActual, Integer id_municipio, Integer id_estado, String telefono, String profesion, String fechaNacimientoStr) throws Exception {
        try{
        Candidato candidato = obtenerCandidatoPorId(id_candidato);

        if(candidato==null){
            throw new Exception("No se encontro un candidato (null)");
        }

        //Se valida que los datos a modificar no vengan vacios, de ser así no se ejecutara la modificación
        if (nombre != null && nombre != "") {
            candidato.getUsuario().setNombre(nombre);
        }
        if (apellidoP != null && apellidoP != "") {
            candidato.getUsuario().setApellidoP(apellidoP);
        }
        if (apellidoM != null && apellidoM != "") {
            candidato.getUsuario().setApellidoM(apellidoM);
        }
        if (domicilio != null && domicilio != "") {
            candidato.setDomicilio(domicilio);
        }
        if (descripcion != null && descripcion != "") {
            candidato.setDescripcion(descripcion);
        }
        if (centroEducativo != null && centroEducativo != "") {
            candidato.setCentroEducativo(centroEducativo);
        }
        if (puestoActual != null && puestoActual != "") {
            candidato.setPuestoActual(puestoActual);
        }
        Estado estado = new Estado();
        if (id_estado != null && id_estado > 0) {
            estado = estadoRepository.findById(id_estado).orElse(null);
            candidato.setEstado(estado);
        }
        Municipio municipio = new Municipio();
        if (id_municipio != null && id_municipio > 0) {
            municipio = municipioRepository.findById(id_municipio).orElse(null);
            candidato.setMunicipio(municipio);
        }
        if (telefono != null && telefono != "") {
            candidato.getUsuario().setTelefono(telefono);
        }

        if (profesion != null && profesion != "") {
            candidato.setProfesion(profesion);
        }
        if (fechaNacimientoStr != null && fechaNacimientoStr != "" ){
            DateTimeFormatter format = new DateTimeFormatterBuilder().append(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toFormatter();
            LocalDate fechaNacimientoFormateada = LocalDate.parse(fechaNacimientoStr,format);
            candidato.setFechaNacimiento(fechaNacimientoFormateada);
        }
        candidato = candidatoRepository.save(candidato);
        candidato.setPostulaciones(null);
        candidato.getEstado().setVacantes_estado(null);
        candidato.getMunicipio().setVacantes_municipios(null);
        candidato.getMunicipio().setEstado(null);
        return candidato;
    }catch(Exception e){
            throw new Exception("Algo salio mal, intentalo de nuevo mas tarde");
        }
    }

    //Añadir idiomas
    public String añadirIdiomas(IdiomaDTO idiomaDTO){

    Candidato candidato = candidatoRepository.findById(idiomaDTO.getId_candidato()).orElse(null);

        List<IdiomaCandidato> listaIdiomasCandidato = idiomaCandidatoRepository.findAllByCandidato(candidato);
        if(listaIdiomasCandidato != null && !listaIdiomasCandidato.isEmpty()) {
            idiomaCandidatoRepository.deleteAll(listaIdiomasCandidato);
        }
        for(Idioma idioma:idiomaDTO.getIdiomas()){
            IdiomaCandidato idiomaCandidato = new IdiomaCandidato();
            idiomaCandidato.setCandidato(candidato);
            idiomaCandidato.setIdioma(idioma);
            idiomaCandidatoRepository.save(idiomaCandidato);
        }
         listaIdiomasCandidato = idiomaCandidatoRepository.findAllByCandidato(candidato);
        /*for(IdiomaCandidato idioma:listaIdiomasCandidato){
            idioma.getIdioma().setCandidatos(null);
            idioma.setCandidato(null);
        }*/
        return "Se modificó";
    }

    //Añadir habilidades
    public String añadirHabilidades(HabilidadDTO habilidadDTO){

        Candidato candidato = candidatoRepository.findById(habilidadDTO.getId_candidato()).orElse(null);

        List<HabilidadCandidato> listaHabilidadesCandidato = habilidadCandidatoRepository.findAllByCandidato(candidato);
        if(listaHabilidadesCandidato != null && !listaHabilidadesCandidato.isEmpty()) {
            habilidadCandidatoRepository.deleteAll(listaHabilidadesCandidato);
        }
        for(Habilidad habilidad:habilidadDTO.getHabilidades()){
            HabilidadCandidato habilidadCandidato = new HabilidadCandidato();
            habilidadCandidato.setCandidato(candidato);
            habilidadCandidato.setHabilidad(habilidad);
            habilidadCandidatoRepository.save(habilidadCandidato);
        }
        listaHabilidadesCandidato = habilidadCandidatoRepository.findAllByCandidato(candidato);
        /*for(HabilidadCandidato habilidad:listaHabilidadesCandidato){
            habilidad.getHabilidad().setCandidatos(null);
            habilidad.setCandidato(null);
        }*/
        return "Se modificó";
    }

}
