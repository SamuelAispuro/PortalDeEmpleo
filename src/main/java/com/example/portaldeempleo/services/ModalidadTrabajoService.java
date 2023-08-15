package com.example.portaldeempleo.services;

import com.example.portaldeempleo.entities.ModalidadTrabajo;
import com.example.portaldeempleo.entities.TipoHorario;
import com.example.portaldeempleo.repositories.ModalidadTrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModalidadTrabajoService {
    @Autowired
    ModalidadTrabajoRepository modalidadTrabajoRepository;

    //Obtener las modalidades de trabajo
    public List<ModalidadTrabajo> obtenerModalidadesTrabajo(){
        List<ModalidadTrabajo> listaModalidadesTrabajo = modalidadTrabajoRepository.findAll();
        for(ModalidadTrabajo modalidadTrabajo:listaModalidadesTrabajo){
            modalidadTrabajo.setModalidadTrabajo_vacante(null);
        }
        return listaModalidadesTrabajo;
    }

}
