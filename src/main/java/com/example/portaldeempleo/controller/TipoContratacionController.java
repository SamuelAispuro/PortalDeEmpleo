package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.DTO.RespCandDTO;
import com.example.portaldeempleo.DTO.RespRegDTO;
import com.example.portaldeempleo.DTO.TipoContratacionDTO;
import com.example.portaldeempleo.DTO.TipoHorarioDTO;
import com.example.portaldeempleo.entities.TipoContratacion;
import com.example.portaldeempleo.entities.TipoHorario;
import com.example.portaldeempleo.services.TipoContratacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TipoContratacionController {
    @Autowired
    TipoContratacionService tipoContratacionService;


    //Obtener tipos de contratacion
    @GetMapping("/obtenerTiposContratacion")
    public ResponseEntity<?> obtenerTiposContratacion(){
        List<TipoContratacion> listaTiposContratacion = tipoContratacionService.obtenerTiposContratacion();
        return new ResponseEntity<>(listaTiposContratacion, HttpStatus.OK);
    }

    //CREAR TIPO CONTRATACION
    @PutMapping("/crearTipoContratacion")
    public ResponseEntity<?>crearTipoContratacion(@RequestBody TipoContratacionDTO tipoContratacionDTO){
        Integer id_tipoContratacion =0;
        TipoContratacion tipoContratacion = new TipoContratacion();
        RespRegDTO respuesta = new RespRegDTO();
        if(tipoContratacionDTO.getHorario() != null && tipoContratacionDTO.getHorario() != ""){
            tipoContratacion = this.tipoContratacionService.crearTipoContratacion(tipoContratacionDTO.getHorario());
            respuesta.setMensaje("El tipo de contratacion se ha creado correctamente");
            respuesta.setEstatus(true);
        }else{
            respuesta.setMensaje("El tipo de contratacion no puede ser creada con campos vacios o invalidos, intentalo de nuevo");
            respuesta.setEstatus(false);
        }
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    //MODIFICAR TIPO CONTRATACION
    @PutMapping("/modificarTipoContratacion")
    public ResponseEntity<?> modificarTipoContratacion(@RequestBody TipoContratacionDTO tipoContratacionDTO){
        RespCandDTO respuesta = new RespCandDTO();

        try {
            //Se hace uso del servicio ModificarTipoContratacion y se modifica la información que haya cambiado el usuario
            TipoContratacion tipoContratacionModificado = this.tipoContratacionService.modificarTipoContratacion(tipoContratacionDTO.getId_tipoContratacion(), tipoContratacionDTO.getHorario());
            respuesta.setTipoContratacionModificado(tipoContratacionModificado);
            respuesta.setEstatus(true);
            respuesta.setMensaje("Datos modificados exitosamente");
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Algo salio mal, intentalo de nuevo mas tarde",HttpStatus.OK);
        }
    }

    //ELIMINAR TIPO DE CONTRATACION
    @DeleteMapping("/eliminarTipoContratacion/{id_tipoContratacion}")
    public ResponseEntity<?> eliminarTipoContratacion(@PathVariable Integer id_tipoContratacion){
        RespRegDTO respuesta = new RespRegDTO();
        try {
            if(id_tipoContratacion != null && id_tipoContratacion != 0) {
                this.tipoContratacionService.eliminarTipoContratacion(id_tipoContratacion);
                respuesta.setMensaje("tipo de Contratacion eliminado exitosamente");
                respuesta.setEstatus(true);
                return new ResponseEntity<>(respuesta, HttpStatus.OK);
            }else{

                respuesta.setMensaje("El id ingresado no es válido.");
                respuesta.setEstatus(false);
                return new ResponseEntity<>(respuesta, HttpStatus.OK);
            }
        }catch(Exception e){

            respuesta.setMensaje("No se encontró un tipo de Contratacion con este ID");
            respuesta.setEstatus(false);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
    }

}
