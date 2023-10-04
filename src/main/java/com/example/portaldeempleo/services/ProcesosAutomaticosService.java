package com.example.portaldeempleo.services;

import com.example.portaldeempleo.entities.ProcesosAutomaticos;
import com.example.portaldeempleo.repositories.ProcesosAutomaticosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProcesosAutomaticosService {
    @Autowired
    ProcesosAutomaticosRepository procesosAutomaticosRepository;

    public List<ProcesosAutomaticos> obtenerListaProcesos(){
        List<ProcesosAutomaticos> listaProcesos = new ArrayList<>();
        listaProcesos = procesosAutomaticosRepository.findAll();
        return listaProcesos;
    }



}
