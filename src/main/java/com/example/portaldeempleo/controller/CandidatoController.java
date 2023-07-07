package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.DTO.DataDTO;
import com.example.portaldeempleo.DTO.RespuestaDTO;
import com.example.portaldeempleo.entities.Usuario;
import com.example.portaldeempleo.services.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CandidatoController {
    @Autowired
    public CandidatoService candidatoService;

//Registrar candidato
@PutMapping("/registroCandidato")
public ResponseEntity<Integer> registroCandidato(@RequestBody DataDTO candidatoDTO){
Integer id_candidato = 0;

id_candidato =this.candidatoService.registroCandidato(candidatoDTO.getNombre(), candidatoDTO.getApellidoP(),candidatoDTO.getApellidoM(), candidatoDTO.getCorreoElectronico(), candidatoDTO.getTelefono(), candidatoDTO.getContrasena(), candidatoDTO.getEdad());
return new ResponseEntity<>(id_candidato, HttpStatus.OK);
}


}
