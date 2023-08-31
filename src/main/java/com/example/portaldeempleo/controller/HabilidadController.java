package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.entities.Habilidad;
import com.example.portaldeempleo.entities.Vacante;
import com.example.portaldeempleo.services.HabilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
