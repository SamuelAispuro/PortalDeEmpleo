package com.example.portaldeempleo.services;

import com.example.portaldeempleo.entities.TipoContratacion;
import com.example.portaldeempleo.entities.TipoHorario;
import com.example.portaldeempleo.repositories.TipoHorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoHorarioService {
    @Autowired
    TipoHorarioRepository tipoHorarioRepository;

    //Obtener las vacantes por tipo de horario
    public List<TipoHorario> obtenerTiposHorario(){
        List<TipoHorario> listaTipoHorario = tipoHorarioRepository.findAll();
        for(TipoHorario tipoHorario:listaTipoHorario){
            tipoHorario.setTipoHorario_vacantes(null);
        }
        return listaTipoHorario;
    }


}
