package com.example.portaldeempleo.services;

import com.example.portaldeempleo.DTO.RespPostDTO;
import com.example.portaldeempleo.entities.*;
import com.example.portaldeempleo.repositories.CandidatoRepository;
import com.example.portaldeempleo.repositories.PostulacionRepository;
import com.example.portaldeempleo.repositories.VacanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostulacionService {

    @Autowired
    CandidatoRepository candidatoRepository;
    @Autowired
    VacanteRepository vacanteRepository;
    @Autowired
    PostulacionRepository postulacionRepository;

    //metodo para obtener todos los candidatos
    public List<Candidato> obtenerTodos(){
        return this.candidatoRepository.findAll();
    }

    //Metodo para postularse a una vacante
    public Postulacion postulacion(Integer id_candidato, Integer id_vacante) throws Exception {
    Candidato candidatoEncontrado = candidatoRepository.findById(id_candidato).orElse(null);

    List<Postulacion> listaPostulacionesCandidato = postulacionRepository.findAllByCandidato(candidatoEncontrado);
    for(Postulacion postulacion:listaPostulacionesCandidato){
        if(postulacion.getVacante().getId_vacante() == id_vacante){
            throw new Exception("El candidato ya se encuentra postulado a esta vacante");
        }
    }

    candidatoEncontrado.setUsuario(candidatoEncontrado.getUsuario());
    candidatoEncontrado.setMunicipio(candidatoEncontrado.getMunicipio());
    candidatoEncontrado.setEstado(candidatoEncontrado.getEstado());
    candidatoEncontrado.getEstado().setVacantes_estado(null);
    for(Idioma idioma:candidatoEncontrado.getIdiomas()){
        idioma.setCandidatos(null);
    }
    for(Habilidad habilidad:candidatoEncontrado.getHabilidades()){
        habilidad.setCandidatos(null);
    }

    candidatoEncontrado.setFechaNacimiento(candidatoEncontrado.getFechaNacimiento());

    Vacante vacanteEncontrada = vacanteRepository.findById(id_vacante).orElse(null);

        Postulacion postulacion = new Postulacion();
        postulacion.setCandidato(candidatoEncontrado);
        postulacion.setVacante(vacanteEncontrada);
        postulacion.setEstatus(1); //"1" postulación en revisión
        postulacion = postulacionRepository.save(postulacion);

        postulacion.getCandidato().setPostulaciones(null);
        postulacion.getVacante().getEmpresa().setVacantes_empresa(null);
        postulacion.getVacante().setCandidatos(null);
        postulacion.getCandidato().getMunicipio().setEstado(null);
        postulacion.getCandidato().getMunicipio().setVacantes_municipios(null);
        postulacion.getCandidato().getEstado().setMunicipios(null);
        postulacion.getCandidato().setIdiomas(null);
        postulacion.getVacante().getMunicipio().setEstado(null);
        postulacion.getVacante().getMunicipio().setVacantes_municipios(null);
        postulacion.getVacante().getEmpleador().setVacantes(null);
        postulacion.getVacante().getTipoHorario().setTipoHorario_vacantes(null);
        postulacion.getVacante().getTipoContratacion().setTipoContratacion_vacantes(null);
        postulacion.getVacante().getModalidadTrabajo().setModalidadTrabajo_vacante(null);
        postulacion.getCandidato().getEstado().setVacantes_estado(null);
        postulacion.getCandidato().setHabilidades(null);
        postulacion.getCandidato().setIdiomas(null);
        postulacion.getVacante().getEstado().setMunicipios(null);
        postulacion.getVacante().getEstado().setVacantes_estado(null);

        return postulacion;
    }
    //Eliminar una postulacion
    public void eliminarPostulacion(Integer id){
        this.postulacionRepository.deleteById(id);
    }

    //Anadir producto a lista de deseos
    //public  postulacion(Integer id_candidato, Integer id_vacante) throws Exception {
       // Candidato candidatoEncontrado = candidatoRepository.findById(id_candidato).orElse(null);




}
