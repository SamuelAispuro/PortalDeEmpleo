package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.DTO.*;
import com.example.portaldeempleo.entities.ModalidadTrabajo;
import com.example.portaldeempleo.entities.TipoContratacion;
import com.example.portaldeempleo.entities.TipoHorario;
import com.example.portaldeempleo.services.ModalidadTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ModalidadTrabajoController {
    @Autowired
    ModalidadTrabajoService modalidadTrabajoService;

    //Obtener tipos de horario
    @GetMapping("/obtenerModalidadesTrabajo")
    public ResponseEntity<?> obtenerModalidadesTrabajo(){
        List<ModalidadTrabajo> listaModalidadesTrabajo = modalidadTrabajoService.obtenerModalidadesTrabajo();
        return new ResponseEntity<>(listaModalidadesTrabajo, HttpStatus.OK);
    }

    //CREAR MODALIDAD TRABAJO
    @PutMapping("/crearModalidadTrabajo")
    public ResponseEntity<?>crearModalidadTrabajo(@RequestBody ModalidadTrabajoDTO modalidadTrabajoDTO){
        Integer id_modalidadTrabajo =0;
        ModalidadTrabajo modalidadTrabajo = new ModalidadTrabajo();
        RespRegDTO respuesta = new RespRegDTO();
        if(modalidadTrabajoDTO.getModalidad() != null && modalidadTrabajoDTO.getModalidad() != ""){
            modalidadTrabajo = this.modalidadTrabajoService.crearModalidadTrabajo(modalidadTrabajoDTO.getModalidad());
            respuesta.setMensaje("La modalidad de trabajo se ha creado correctamente");
            respuesta.setEstatus(true);
        }else{
            respuesta.setMensaje("La modalidad de trabajo no puede ser creada con campos vacios o invalidos, intentalo de nuevo");
            respuesta.setEstatus(false);
        }
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    //MODIFICAR MODALIDAD TRABAJO
    @PutMapping("/modificarModalidadTrabajo")
    public ResponseEntity<?> modificarModalidadTrabajo(@RequestBody ModalidadTrabajoDTO modalidadTrabajoDTO){
        RespCandDTO respuesta = new RespCandDTO();

        try {
            //Se hace uso del servicio ModificarModalidadTrabajo y se modifica la información que haya cambiado el usuario
            ModalidadTrabajo modalidadTrabajoModificado = this.modalidadTrabajoService.modificarModalidadTrabajo(modalidadTrabajoDTO.getId_modalidadTrabajo(), modalidadTrabajoDTO.getModalidad());
            respuesta.setModalidadTrabajoModificado(modalidadTrabajoModificado);
            respuesta.setEstatus(true);
            respuesta.setMensaje("Datos modificados exitosamente");
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Algo salio mal, intentalo de nuevo mas tarde",HttpStatus.OK);
        }
    }

    //ELIMINAR MODALIDAD DE TRABAJO
    @DeleteMapping("/eliminarModalidadTrabajo/{id_modalidadTrabajo}")
    public ResponseEntity<?> eliminarModalidadTrabajo(@PathVariable Integer id_modalidadTrabajo){
        RespRegDTO respuesta = new RespRegDTO();
        try {
            if(id_modalidadTrabajo != null && id_modalidadTrabajo != 0) {
                this.modalidadTrabajoService.eliminarModalidadTrabajo(id_modalidadTrabajo);
                respuesta.setMensaje("modalidad de trabajo eliminado exitosamente");
                respuesta.setEstatus(true);
                return new ResponseEntity<>(respuesta, HttpStatus.OK);
            }else{

                respuesta.setMensaje("El id ingresado no es válido.");
                respuesta.setEstatus(false);
                return new ResponseEntity<>(respuesta, HttpStatus.OK);
            }
        }catch(Exception e){

            respuesta.setMensaje("No se encontró una modalidad de trabajo con este ID");
            respuesta.setEstatus(false);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
    }

}
