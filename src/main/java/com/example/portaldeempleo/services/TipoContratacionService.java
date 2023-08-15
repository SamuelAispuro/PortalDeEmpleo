package com.example.portaldeempleo.services;

import com.example.portaldeempleo.entities.TipoContratacion;
import com.example.portaldeempleo.repositories.TipoContratacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoContratacionService {
    @Autowired
    TipoContratacionRepository tipoContratacionRepository;

    //Obtener las vacantes por tipo de contratacion
    public List<TipoContratacion> obtenerTiposContratacion(){
        List<TipoContratacion> listaTipoContratacion = tipoContratacionRepository.findAll();
        for(TipoContratacion tipoContratacion:listaTipoContratacion){
            tipoContratacion.setTipoContratacion_vacantes(null);
        }
        return listaTipoContratacion;
    }

}
