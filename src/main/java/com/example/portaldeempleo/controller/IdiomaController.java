package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.entities.Habilidad;
import com.example.portaldeempleo.entities.Idioma;
import com.example.portaldeempleo.services.IdiomaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class IdiomaController {
    @Autowired
    IdiomaService idiomaService;

    //Obtener lista de idiomas
    @GetMapping("/obtenerListaIdiomas")
    public List<Idioma> obtenerListaIdiomas(){
        List<Idioma> listaIdiomas = this.idiomaService.obtenerListaIdiomas();
        return listaIdiomas;
    }

}
