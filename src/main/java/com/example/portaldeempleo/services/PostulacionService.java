package com.example.portaldeempleo.services;

import com.example.portaldeempleo.entities.Candidato;
import com.example.portaldeempleo.entities.Postulacion;
import com.example.portaldeempleo.entities.Vacante;
import com.example.portaldeempleo.repositories.CandidatoRepository;
import com.example.portaldeempleo.repositories.PostulacionRepository;
import com.example.portaldeempleo.repositories.VacanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        Candidato candidatoId = new Candidato();
        candidatoId.setId_candidato(id_candidato);

        Vacante vacanteId = new Vacante();
        vacanteId.setId_vacante(id_vacante);

        Postulacion postulacion = new Postulacion();
        postulacion.setCandidato(candidatoId);
        postulacion.setVacante(vacanteId);
        postulacion.setEstatus(1); //"1" postulación en revisión
        postulacion = postulacionRepository.save(postulacion);
        return postulacion;
    }

}
