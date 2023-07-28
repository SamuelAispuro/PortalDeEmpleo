package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.entities.Municipio;
import com.example.portaldeempleo.services.EstadoService;
import com.example.portaldeempleo.services.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MunicipioController {
    @Autowired
    MunicipioService municipioService;
    @Autowired
    EstadoService estadoService;

    /**
     * Obtener los municipios de un estado
     * @param id_estado
     * @return listaMunicipiosDeEstado
     */
    @GetMapping("/obtenerMunicipiosDeEstado/{id_estado}")
        public ResponseEntity<?> obtenerListaMunicipiosDeEstado(@PathVariable Integer id_estado){
            List<Municipio> listaMunicipiosDeEstado = new ArrayList<>();
            listaMunicipiosDeEstado = this.municipioService.obtenerListaMunicipiosDeEstado(id_estado);
        return new ResponseEntity<>(listaMunicipiosDeEstado, HttpStatus.OK);
        }



}
