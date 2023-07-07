package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.DTO.VacanteDTO;
import com.example.portaldeempleo.services.CandidatoService;
import com.example.portaldeempleo.services.VacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
