package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.entities.TipoContratacion;
import com.example.portaldeempleo.services.TipoContratacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
