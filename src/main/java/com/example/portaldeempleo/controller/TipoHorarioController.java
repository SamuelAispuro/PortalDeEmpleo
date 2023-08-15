package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.entities.TipoContratacion;
import com.example.portaldeempleo.entities.TipoHorario;
import com.example.portaldeempleo.services.TipoHorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
