package com.example.portaldeempleo.services;

import com.example.portaldeempleo.entities.Habilidad;
import com.example.portaldeempleo.repositories.HabilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HabilidadService {

    @Autowired
    HabilidadRepository habilidadRepository;

    //Obtener la lista de habilidades predeterminadas
    public List<Habilidad> obtenerListaHabilidades(){

        List<Habilidad> listaHabilidades = new ArrayList<>();
        listaHabilidades = habilidadRepository.findAll();
        for(Habilidad habilidad : listaHabilidades){
            habilidad.setCandidatos(null);
        }
    return listaHabilidades;
    }

}
