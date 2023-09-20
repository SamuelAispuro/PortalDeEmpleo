package com.example.portaldeempleo.services;

import com.example.portaldeempleo.entities.ModalidadTrabajo;
import com.example.portaldeempleo.entities.TipoContratacion;
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

    //CREAR MODALDIAD TRABAJO
    public ModalidadTrabajo crearModalidadTrabajo(String modalidad){
        ModalidadTrabajo modalidadTrabajo = new ModalidadTrabajo();

        modalidadTrabajo.setModalidad(modalidad);
        modalidadTrabajo = modalidadTrabajoRepository.save(modalidadTrabajo);
        return modalidadTrabajo;
    }

    //MODIFICAR MODALIDAD TRABAJO
    public ModalidadTrabajo modificarModalidadTrabajo(Integer id_modalidadTrabajo, String modalidad) throws Exception {
        try{
            ModalidadTrabajo modalidadTrabajo = modalidadTrabajoRepository.findById(id_modalidadTrabajo).orElse(null);

            if(modalidadTrabajo==null){
                throw new Exception("No se encontro una modalidad de trabajo");
            }

            //Se valida que los datos a modificar no vengan vacios, de ser así no se ejecutara la modificación
            if (modalidad != null && modalidad != "") {
                modalidadTrabajo.setModalidad(modalidad);
            }
            modalidadTrabajo = modalidadTrabajoRepository.save(modalidadTrabajo);

            return modalidadTrabajo;
        }catch(Exception e){
            throw new Exception("Algo salio mal, intentalo de nuevo mas tarde");
        }
    }

}
