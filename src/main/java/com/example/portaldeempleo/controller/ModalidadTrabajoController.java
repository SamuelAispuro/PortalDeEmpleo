package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.entities.ModalidadTrabajo;
import com.example.portaldeempleo.entities.TipoHorario;
import com.example.portaldeempleo.services.ModalidadTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
