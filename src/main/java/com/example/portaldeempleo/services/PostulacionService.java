package com.example.portaldeempleo.services;

import com.example.portaldeempleo.DTO.RespPostDTO;
import com.example.portaldeempleo.entities.Candidato;
import com.example.portaldeempleo.entities.Postulacion;
import com.example.portaldeempleo.entities.Vacante;
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
    public Postulacion postulacion(Integer id_candidato, Integer id_vacante){
    Candidato candidatoEncontrado = candidatoRepository.findById(id_candidato).orElse(null);
    candidatoEncontrado.setUsuario(candidatoEncontrado.getUsuario());
    candidatoEncontrado.setMunicipio(candidatoEncontrado.getMunicipio());
    candidatoEncontrado.setEstado(candidatoEncontrado.getEstado());

    candidatoEncontrado.setEdad(candidatoEncontrado.getEdad());

    Vacante vacanteEncontrada = vacanteRepository.findById(id_vacante).orElse(null);

        Postulacion postulacion = new Postulacion();
        postulacion.setCandidato(candidatoEncontrado);
        postulacion.setVacante(vacanteEncontrada);
        postulacion.setEstatus(1); //"1" postulación en revisión
        postulacion = postulacionRepository.save(postulacion);

        postulacion.getCandidato().setPostulaciones(null);
        postulacion.getVacante().getEmpresa().setVacantes(null);
        postulacion.getVacante().setCandidatos(null);
        postulacion.getCandidato().getMunicipio().setEstado(null);
        postulacion.getCandidato().getMunicipio().setVacantes_municipios(null);
        postulacion.getCandidato().getEstado().setMunicipios(null);
        return postulacion;
    }
    //Eliminar una postulacion
    public void eliminarPostulacion(Integer id){
        this.postulacionRepository.deleteById(id);
    }

}
