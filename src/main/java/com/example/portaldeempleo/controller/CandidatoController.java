package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.DTO.DataDTO;
import com.example.portaldeempleo.entities.Usuario;
import com.example.portaldeempleo.services.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CandidatoController {
    @Autowired
    public CandidatoService candidatoService;

//Guardar candidato
@PutMapping("/registroCandidato")
public ResponseEntity<Integer> registroCandidato(@RequestBody DataDTO candidatoDTO){
Integer id_candidato = 0;
id_candidato =this.candidatoService.registroCandidato(candidatoDTO.getNombre(), candidatoDTO.getApellidoP(),candidatoDTO.getApellidoM(), candidatoDTO.getCorreoElectronico(), candidatoDTO.getTelefono(), candidatoDTO.getContraseña(), candidatoDTO.getEdad());
return new ResponseEntity<>(id_candidato, HttpStatus.OK);
}
//Login
    @PostMapping("/Login")
public ResponseEntity<?> login(@RequestBody DataDTO requestData){

Usuario usuarioEncontrado = this.candidatoService.login(requestData.getCorreoElectronico(), requestData.getContraseña());
    if (usuarioEncontrado != null){
        usuarioEncontrado.setContraseña(null);
        return new ResponseEntity<>(usuarioEncontrado, HttpStatus.OK);
    }else{
        return new ResponseEntity<>("No se encontró una cuenta con estos datos",HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

}
