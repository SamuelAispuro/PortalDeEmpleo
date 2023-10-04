package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.DTO.*;
import com.example.portaldeempleo.entities.Candidato;
import com.example.portaldeempleo.entities.Habilidad;
import com.example.portaldeempleo.entities.Idioma;
import com.example.portaldeempleo.entities.Vacante;
import com.example.portaldeempleo.services.HabilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HabilidadController {
    @Autowired
    HabilidadService habilidadService;

    //Obtener lista de habilidades
    @GetMapping("/obtenerListaHabilidades")
    public List<Habilidad> obtenerListaHabilidades(){
        List<Habilidad> listaHabilidades = this.habilidadService.obtenerListaHabilidades();
        return listaHabilidades;
    }

    @PutMapping("/crearHabilidad")
    public ResponseEntity<?> crearHabilidad(@RequestBody HabilidadDTO habilidadDTO){
        Integer id_habilidad =0;

        RespRegDTO respuesta = new RespRegDTO();
        if(habilidadDTO.getNombreHabilidad() != null && habilidadDTO.getNombreHabilidad() != ""){
            Habilidad habilidad = new Habilidad();
            habilidad = this.habilidadService.crearHabilidad(habilidadDTO.getNombreHabilidad());
            respuesta.setMensaje("La habilidad se ha creado correctamente");
            respuesta.setEstatus(true);
        }else{
            respuesta.setMensaje("La habilidad no puede ser creada con campos vacios o invalidos, intentalo de nuevo");
            respuesta.setEstatus(false);
        }
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/modificarHabilidad")
    public ResponseEntity<?> modificarHabilidad(@RequestBody HabilidadDTO habilidadDTO){
        RespCandDTO respuesta = new RespCandDTO();

        try {
            //Se hace uso del servicio ModificarHabilidad y se modifica la información que haya cambiado el usuario
            Habilidad habilidadModificada = this.habilidadService.modificarHabilidad(habilidadDTO.getId_habilidad(), habilidadDTO.getNombreHabilidad());
            respuesta.setHabilidadModificada(habilidadModificada);
            respuesta.setEstatus(true);
            respuesta.setMensaje("Datos modificados exitosamente");
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Algo salio mal, intentalo de nuevo mas tarde",HttpStatus.OK);
        }
    }

    //ELIMINAR HABILIDAD
    @DeleteMapping("/eliminarHabilidad/{id_habilidad}")
    public ResponseEntity<?> eliminarHabilidad(@PathVariable Integer id_habilidad){
        RespRegDTO respuesta = new RespRegDTO();
        try {
            if(id_habilidad != null && id_habilidad != 0) {
                this.habilidadService.eliminarHabilidad(id_habilidad);
                respuesta.setMensaje("Habilidad eliminada exitosamente");
                respuesta.setEstatus(true);
                return new ResponseEntity<>(respuesta, HttpStatus.OK);
            }else{

                respuesta.setMensaje("El id ingresado no es válido.");
                respuesta.setEstatus(false);
                return new ResponseEntity<>(respuesta, HttpStatus.OK);
            }
        }catch(Exception e){

            respuesta.setMensaje("No se encontró una habilidad con este ID");
            respuesta.setEstatus(false);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
    }

}
