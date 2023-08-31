package com.example.portaldeempleo.services;

import com.example.portaldeempleo.entities.Estado;
import com.example.portaldeempleo.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstadoService {
    @Autowired
    EstadoRepository estadoRepository;

    //Obtener lista de estados
    public List<Estado> obtenerListaEstados(){
        List<Estado> listaEstados = new ArrayList<>();
        listaEstados = estadoRepository.findAll();
        for(Estado estado:listaEstados){
            estado.setMunicipios(null);
            estado.setVacantes_estado(null);
        }
        return listaEstados;
    }


}
