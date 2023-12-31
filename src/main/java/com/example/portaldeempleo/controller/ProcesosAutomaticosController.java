package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.entities.ProcesosAutomaticos;
import com.example.portaldeempleo.entities.Vacante;
import com.example.portaldeempleo.services.ProcesosAutomaticosService;
import com.example.portaldeempleo.services.VacanteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProcesosAutomaticosController {
    @Autowired
    ProcesosAutomaticosService procesosAutomaticosService;
   

    //Obtener lista de procesos
    @GetMapping("/obtenerListaProcesos")
    public List<ProcesosAutomaticos> obtenerListaProcesos(){
        List<ProcesosAutomaticos> listaProcesos = this.procesosAutomaticosService.obtenerListaProcesos();
        return listaProcesos;

    }
   
    //Obtener todas las procesos paginados
    @GetMapping("/procesos/page/{page}")
    public Page<ProcesosAutomaticos> consultaPage(@PathVariable Integer page){
        Pageable pageable = PageRequest.of(page, 8);
        return procesosAutomaticosService.findAllPage(pageable);
    }


}
