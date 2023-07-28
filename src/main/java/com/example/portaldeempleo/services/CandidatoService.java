package com.example.portaldeempleo.services;

import com.example.portaldeempleo.DTO.DataDTO;
import com.example.portaldeempleo.entities.*;
import com.example.portaldeempleo.repositories.CandidatoRepository;
import com.example.portaldeempleo.repositories.PostulacionRepository;
import com.example.portaldeempleo.repositories.UsuarioRepository;
import com.example.portaldeempleo.repositories.VacanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
    @Value("${ruta_local}") // La ruta local donde quieres almacenar las imágenes
    private String rutaLocal;

    //metodo para registrar un candidato
public Candidato registroCandidato(String nombre, String apellidoP, String apellidoM, String correoElectronico, String telefono, String contraseña , Integer edad, Integer id_municipio, Integer id_estado, String domicilio){
    Usuario usuarioEncontrado = usuarioRepository.findByCorreoElectronicoAndEstatusUsuario(correoElectronico, true);
     if(usuarioEncontrado==null) {
         Usuario usuario = new Usuario();
         usuario.setNombre(nombre);
         usuario.setCorreoElectronico(correoElectronico);
         usuario.setContraseña(passwordEncryption.encryptText(contraseña));
         usuario.setApellidoP(apellidoP);
         usuario.setApellidoM(apellidoM);
         usuario.setTelefono(telefono);
         usuario.setTipoUsuario(2); //"2" es el tipo de usuario de un candidato
         usuario.setEstatusUsuario(true);
         usuario = usuarioRepository.save(usuario);

         Estado estado = new Estado();
         estado.setId_estado(id_estado);

         Municipio municipio = new Municipio();
         municipio.setId_municipio(id_municipio);

         Candidato candidato = new Candidato();

         candidato.setMunicipio(municipio);
         candidato.setEstado(estado);
         candidato.setUsuario(usuario);
         candidato.setEdad(edad);
         candidato.setDomicilio(domicilio);
         candidato = candidatoRepository.save(candidato);

         return candidato;
     }else{
         return null;
     }

}
    //Obtener candidato por ID
    public Candidato obtenerCandidatoPorId(Integer id){
    Candidato candidatoEncontrado = this.candidatoRepository.findById(id).orElse(null);
    candidatoEncontrado.setPostulaciones(null);
    return candidatoEncontrado;
    }

    //Obtener postulaciones de un candidato
    public List<Postulacion> obtenerPostulacionesPorIdDeCandidato(Integer id){

        Candidato candidato = candidatoRepository.findById(id).orElse(null);
       List<Postulacion> listaPostulacionesCandidato = postulacionRepository.findAllByCandidato(candidato);
        for(Postulacion postulacion:listaPostulacionesCandidato){
            postulacion.getCandidato().setPostulaciones(null);
            postulacion.getCandidato().getMunicipio().setVacantes_municipios(null);
            postulacion.getCandidato().getMunicipio().setEstado(null);
            postulacion.getCandidato().getEstado().setMunicipios(null);
            postulacion.getVacante().getEmpresa().setVacantes(null);
            postulacion.getVacante().setCandidatos(null);
            postulacion.getCandidato().setIdiomas(null);



        }

        return listaPostulacionesCandidato;
    }
    //Modificar un candidato
    public Candidato modificarCandidato(String nombre, String apellidoP, String apellidoM,String domicilio, String descripcion, String centroEducativo, String puestoActual, Integer id_municipio, Integer id_estado, Integer id_candidato){
    Candidato candidato = obtenerCandidatoPorId(id_candidato);

        if(nombre != null && nombre != ""){
            candidato.getUsuario().setNombre(nombre);
        }
        if(apellidoP != null && apellidoP != ""){
            candidato.getUsuario().setApellidoP(apellidoP);
        }
        if(apellidoM != null && apellidoM != ""){
            candidato.getUsuario().setApellidoM(apellidoM);
        }
        if(domicilio != null && domicilio != ""){
            candidato.setDomicilio(domicilio);
        }
        if(descripcion != null && descripcion != ""){
            candidato.setDescripcion(descripcion);
        }
        if(centroEducativo != null && centroEducativo != ""){
            candidato.setCentroEducativo(centroEducativo);
        }
        if (puestoActual != null && puestoActual != ""){
            candidato.setPuestoActual(puestoActual);
        }
        Estado estado = new Estado();
        if(id_estado != null && id_estado > 0){
            estado.setId_estado(id_estado);
            candidato.setEstado(estado);
        }

        if(id_municipio != null && id_municipio > 0) {
            Municipio municipio = new Municipio();
            municipio.setId_municipio(id_municipio);
            municipio.setEstado(estado);
            candidato.setMunicipio(municipio);
        }


        candidato = candidatoRepository.save(candidato);
        return candidato;
    }

    /**
     * Subir Cv
     * @param imagen
     * @param id_candidato
     * @return
     * @throws IOException
     */
    public String subirFotoCv(MultipartFile imagen, String id_candidato) throws IOException {
        Candidato candidato = candidatoRepository.findById(Integer.parseInt(id_candidato)).orElse(null);
        File ruta = new File(rutaLocal+File.separator+candidato.getUsuario().getId_usuario()+File.separator+"CV");

        if(ruta.exists() == false){
            ruta.mkdirs();
        }

        File archivoLocal = new File(rutaLocal+File.separator+candidato.getUsuario().getId_usuario()+File.separator+"CV"+File.separator+imagen.getOriginalFilename());

        FileOutputStream fos = new FileOutputStream(archivoLocal);
        fos.write(imagen.getBytes());
        fos.close();
        candidato.setRutaCv(archivoLocal.getAbsolutePath());


        candidatoRepository.save(candidato);
        return "Archivo guardado exitosamente";
    }

}
