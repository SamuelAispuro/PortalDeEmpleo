package com.example.portaldeempleo.services;

import com.example.portaldeempleo.entities.Empresa;
import com.example.portaldeempleo.entities.Idioma;
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

    //CREAR TIPO HORARIO
    public TipoHorario crearTipoHorario(String dias){
        TipoHorario tipoHorario = new TipoHorario();

        tipoHorario.setDias(dias);
        tipoHorario = tipoHorarioRepository.save(tipoHorario);
        return tipoHorario;
    }

    //MODIFICAR TIPO HORARIO
    public TipoHorario modificarTipoHorario(Integer id_tipoHorario, String dias) throws Exception {
        try{
            TipoHorario tipoHorario = tipoHorarioRepository.findById(id_tipoHorario).orElse(null);

            if(tipoHorario==null){
                throw new Exception("No se encontro un tipo horario");
            }

            //Se valida que los datos a modificar no vengan vacios, de ser así no se ejecutara la modificación
            if (dias != null && dias != "") {
                tipoHorario.setDias(dias);
            }
            tipoHorario = tipoHorarioRepository.save(tipoHorario);

            return tipoHorario;
        }catch(Exception e){
            throw new Exception("Algo salio mal, intentalo de nuevo mas tarde");
        }
    }

    //ELIMINAR TIPO DE HORARIO
    public String eliminarTipoHorario(Integer id_tipoHorario){

        TipoHorario tipoHorario = tipoHorarioRepository.findById(id_tipoHorario).orElse(null);
        tipoHorarioRepository.delete(tipoHorario);
        return "TipoHorario eliminado exitosamente";
    }


}
