package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.DTO.PostDTO;
import com.example.portaldeempleo.entities.Postulacion;
import com.example.portaldeempleo.services.PostulacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PostulacionController {

    @Autowired
    PostulacionService postulacionService;

    // Postulación
    @PutMapping("/postulacion")
    public ResponseEntity<?> postulacion(@RequestBody PostDTO postulacionDTO) {
    Postulacion postulacion;

        postulacion = this.postulacionService.postulacion(postulacionDTO.getId_candidato(), postulacionDTO.getId_vacante());
        return new ResponseEntity<>(postulacion, HttpStatus.OK);
    }

}
