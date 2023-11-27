package com.example.portaldeempleo.services;

import com.example.portaldeempleo.entities.ProcesosAutomaticos;
import com.example.portaldeempleo.entities.Vacante;
import com.example.portaldeempleo.repositories.ProcesosAutomaticosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    //Metodo obtener todas las vacantes paginadas
    @Transactional(readOnly = true)
    public Page<ProcesosAutomaticos> findAllPage(Pageable pageable){
        Page<ProcesosAutomaticos> listaProcesos = procesosAutomaticosRepository.findAll(pageable);

        return listaProcesos;
    }

}
