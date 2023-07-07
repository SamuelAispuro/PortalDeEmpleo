package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.DTO.VacanteDTO;
import com.example.portaldeempleo.entities.Vacante;
import com.example.portaldeempleo.services.CandidatoService;
import com.example.portaldeempleo.services.VacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class VacanteController {

    @Autowired
    public VacanteService vacanteService;

    //Crear vacante
    @PutMapping("/crearVacante")
    public ResponseEntity<Integer> crearVacante(@RequestBody VacanteDTO vacanteDTO){
        Integer id_vacante = 0;

        id_vacante =this.vacanteService.crearVacante(vacanteDTO.getNombre(), vacanteDTO.getEspecialista(),vacanteDTO.getSueldo(), vacanteDTO.getId_empresa());
        return new ResponseEntity<>(id_vacante, HttpStatus.OK);
    }

    //Obtener lista de todas las vacantes
    @GetMapping("/obtenerListaVacantes")
        public List<Vacante> obtenerListaVacantes(){
        List<Vacante> listaVacantes = this.vacanteService.obtenerListaVacantes();
        return listaVacantes;

    }




}
