package com.example.portaldeempleo.services;

import com.example.portaldeempleo.entities.TipoContratacion;
import com.example.portaldeempleo.entities.TipoHorario;
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

    //CREAR TIPO CONTRATACION
    public TipoContratacion crearTipoContratacion(String horario){
        TipoContratacion tipoContratacion = new TipoContratacion();

        tipoContratacion.setHorario(horario);
        tipoContratacion = tipoContratacionRepository.save(tipoContratacion);
        return tipoContratacion;
    }

    //MODIFICAR TIPO CONTRATACION
    public TipoContratacion modificarTipoContratacion(Integer id_tipoContratacion, String horario) throws Exception {
        try{
            TipoContratacion tipoContratacion = tipoContratacionRepository.findById(id_tipoContratacion).orElse(null);

            if(tipoContratacion==null){
                throw new Exception("No se encontro un tipo contratacion");
            }

            //Se valida que los datos a modificar no vengan vacios, de ser así no se ejecutara la modificación
            if (horario != null && horario != "") {
                tipoContratacion.setHorario(horario);
            }
            tipoContratacion = tipoContratacionRepository.save(tipoContratacion);

            return tipoContratacion;
        }catch(Exception e){
            throw new Exception("Algo salio mal, intentalo de nuevo mas tarde");
        }
    }

}
