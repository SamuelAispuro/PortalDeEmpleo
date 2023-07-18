package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.DTO.DataDTO;
import com.example.portaldeempleo.DTO.RespRegDTO;
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
public ResponseEntity<?> registroCandidato(@RequestBody DataDTO candidatoDTO){
Integer id_candidato = 0;
    RespRegDTO respuesta = new RespRegDTO();
if(candidatoDTO.getNombre() != null && candidatoDTO.getNombre() !="" && candidatoDTO.getApellidoP() != null && candidatoDTO.getApellidoP() !="" && candidatoDTO.getApellidoM() != null && candidatoDTO.getApellidoM() !=""
        && candidatoDTO.getCorreoElectronico()!=null && candidatoDTO.getCorreoElectronico()!="" && candidatoDTO.getTelefono()!=null && candidatoDTO.getTelefono()!=""&& candidatoDTO.getContrasena()!=null && candidatoDTO.getContrasena()!="" && candidatoDTO.getEdad()!=null /*&& candidatoDTO.getLocalidad()!=null*/) {

    id_candidato = this.candidatoService.registroCandidato(candidatoDTO.getNombre(), candidatoDTO.getApellidoP(), candidatoDTO.getApellidoM(), candidatoDTO.getCorreoElectronico(), candidatoDTO.getTelefono(), candidatoDTO.getContrasena(), candidatoDTO.getEdad()/*, candidatoDTO.getLocalidad()*/);
    return new ResponseEntity<>(id_candidato, HttpStatus.OK);
}else{
    respuesta.setMensaje("No puedes hacer el registro de usuario si dejas algun campo en blanco, vuelve a intentarlo");
    respuesta.setEstatus("ERROR");
    return new ResponseEntity<>(respuesta, HttpStatus.OK);
}
}


}
