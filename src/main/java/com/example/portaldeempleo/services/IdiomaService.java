package com.example.portaldeempleo.services;

import com.example.portaldeempleo.entities.Candidato;
import com.example.portaldeempleo.entities.Habilidad;
import com.example.portaldeempleo.entities.Idioma;
import com.example.portaldeempleo.repositories.CandidatoRepository;
import com.example.portaldeempleo.repositories.IdiomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IdiomaService {
    @Autowired
    IdiomaRepository idiomaRepository;
    @Autowired
    CandidatoRepository candidatoRepository;

    //Obtener lista de idiomas
    public List<Idioma> obtenerListaIdiomas(){

        List<Idioma> listaIdiomas = new ArrayList<>();
        listaIdiomas = idiomaRepository.findAll();
        for(Idioma idioma : listaIdiomas){
            idioma.setCandidatos(null);
        }
        return listaIdiomas;

    }

    //Agregar idioma
    public List<Idioma> añadirIdioma(Integer id_candidato, Integer id_idioma){
        List<Idioma> listaIdiomasCandidato = new ArrayList<>();
        Candidato candidatoEncontrado = candidatoRepository.findById(id_candidato).orElse(null);
        Idioma idiomaEncontrado = idiomaRepository.findById(id_idioma).orElse(null);


        listaIdiomasCandidato = candidatoEncontrado.getIdiomas();
        listaIdiomasCandidato.add(idiomaEncontrado);
        candidatoRepository.save(candidatoEncontrado);
        return listaIdiomasCandidato;
    }

}
