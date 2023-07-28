package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.entities.Estado;
import com.example.portaldeempleo.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EstadoController {
    @Autowired
    EstadoService estadoService;

    //Obtener lista de estados
    @GetMapping("/obtenerListaEstados")
    public List<Estado> obtenerListaEstados(){
        List<Estado> listaEstados = this.estadoService.obtenerListaEstados();
        return listaEstados;
    }

}
