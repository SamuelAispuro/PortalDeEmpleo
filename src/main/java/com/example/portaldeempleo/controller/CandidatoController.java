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

//Guardar candidato
@PutMapping("/registroCandidato")
public ResponseEntity<Integer> registroCandidato(@RequestBody DataDTO candidatoDTO){
Integer id_candidato = 0;

id_candidato =this.candidatoService.registroCandidato(candidatoDTO.getNombre(), candidatoDTO.getApellidoP(),candidatoDTO.getApellidoM(), candidatoDTO.getCorreoElectronico(), candidatoDTO.getTelefono(), candidatoDTO.getContrasena(), candidatoDTO.getEdad());
return new ResponseEntity<>(id_candidato, HttpStatus.OK);
}
//Login
    @PostMapping("/Login")
public ResponseEntity<?> login(@RequestBody DataDTO requestData){

Usuario usuarioEncontrado = this.candidatoService.login(requestData.getCorreoElectronico(), requestData.getContrasena());
    RespuestaDTO respuesta = new RespuestaDTO();
if (usuarioEncontrado != null){
        usuarioEncontrado.setContraseña(null);
         respuesta.setEstatus(true);
         respuesta.setMensaje("Usuario logeado correctamente");
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }else{
    respuesta.setEstatus(false);
    respuesta.setMensaje("No se encontro un usuario con estos datos");
        return new ResponseEntity<>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

}
