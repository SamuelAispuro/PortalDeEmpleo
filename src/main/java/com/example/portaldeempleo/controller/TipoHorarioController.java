package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.DTO.*;
import com.example.portaldeempleo.entities.Empresa;
import com.example.portaldeempleo.entities.Idioma;
import com.example.portaldeempleo.entities.TipoContratacion;
import com.example.portaldeempleo.entities.TipoHorario;
import com.example.portaldeempleo.services.TipoHorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TipoHorarioController {
    @Autowired
    TipoHorarioService tipoHorarioService;

    //Obtener tipos de horario
    @GetMapping("/obtenerTiposHorario")
    public ResponseEntity<?> obtenerTiposHorario(){
        List<TipoHorario> listaTiposHorario = tipoHorarioService.obtenerTiposHorario();
        return new ResponseEntity<>(listaTiposHorario, HttpStatus.OK);
    }

    //CREAR TIPO HORARIO
    @PutMapping("/crearTipoHorario")
    public ResponseEntity<?>crearTipoHorario(@RequestBody TipoHorarioDTO tipoHorarioDTO){
        Integer id_tipoHorario =0;
        TipoHorario tipoHorario = new TipoHorario();
        RespRegDTO respuesta = new RespRegDTO();
        if(tipoHorarioDTO.getDias() != null && tipoHorarioDTO.getDias() != ""){
            tipoHorario = this.tipoHorarioService.crearTipoHorario(tipoHorarioDTO.getDias());
            respuesta.setMensaje("El tipo de horario se ha creado correctamente");
            respuesta.setEstatus(true);
        }else{
            respuesta.setMensaje("El tipo de horario no puede ser creada con campos vacios o invalidos, intentalo de nuevo");
            respuesta.setEstatus(false);
        }
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    //MODIFICAR TIPO HORARIO
    @PutMapping("/modificarTipoHorario")
    public ResponseEntity<?> modificarTipoHorario(@RequestBody TipoHorarioDTO tipoHorarioDTO){
        RespCandDTO respuesta = new RespCandDTO();

        try {
            //Se hace uso del servicio ModificarTipoHorario y se modifica la información que haya cambiado el usuario
            TipoHorario tipoHorarioModificado = this.tipoHorarioService.modificarTipoHorario(tipoHorarioDTO.getId_tipoHorario(), tipoHorarioDTO.getDias());
            respuesta.setTipoHorarioModificado(tipoHorarioModificado);
            respuesta.setEstatus(true);
            respuesta.setMensaje("Datos modificados exitosamente");
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Algo salio mal, intentalo de nuevo mas tarde",HttpStatus.OK);
        }
    }

}
