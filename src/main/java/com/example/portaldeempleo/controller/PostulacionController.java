package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.DTO.PostDTO;
import com.example.portaldeempleo.DTO.RespPostDTO;
import com.example.portaldeempleo.entities.Postulacion;
import com.example.portaldeempleo.services.PostulacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PostulacionController {

    @Autowired
    PostulacionService postulacionService;

    // Postulación
    @PutMapping("/postulacion")
    public ResponseEntity<?> postulacion(@RequestBody PostDTO postulacionDTO) {
    Postulacion postulacion = new Postulacion();
    RespPostDTO respPostulacionDTO = new RespPostDTO();
        postulacion = this.postulacionService.postulacion(postulacionDTO.getId_candidato(), postulacionDTO.getId_vacante());
        respPostulacionDTO.setPostulacion(postulacion);
        respPostulacionDTO.setEstatus(true);

        return new ResponseEntity<>(respPostulacionDTO, HttpStatus.OK);
    }
    //Eliminar una postulacion
    @DeleteMapping("/eliminarPostulacion/{id}")
    public ResponseEntity<?> eliminarPostulacion(@PathVariable Integer id){
        RespPostDTO respuestaDTO = new RespPostDTO();
        this.postulacionService.eliminarPostulacion(id);
        respuestaDTO.setEstatus(true);
        respuestaDTO.setMensaje("Postulacion eliminada exitosamente");
        return new ResponseEntity<>(respuestaDTO, HttpStatus.OK);
    }

}
