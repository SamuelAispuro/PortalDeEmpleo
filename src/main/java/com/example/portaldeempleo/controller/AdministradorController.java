package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.DTO.DataDTO;
import com.example.portaldeempleo.services.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AdministradorController {

    @Autowired
    AdministradorService administradorService;

    //Registrar administrador
    @PutMapping("/registroAdministrador")
    public ResponseEntity<Integer> registroAdministrador(@RequestBody DataDTO administradorDTO){
        Integer id_administrador = 0;

        id_administrador =this.administradorService.registroAdministrador(administradorDTO.getNombre(), administradorDTO.getApellidoP(),administradorDTO.getApellidoM(), administradorDTO.getCorreoElectronico(), administradorDTO.getTelefono(), administradorDTO.getContrasena());
        return new ResponseEntity<>(id_administrador, HttpStatus.OK);
    }

}
