package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.DTO.DataDTO;
import com.example.portaldeempleo.services.AdministradorService;
import com.example.portaldeempleo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AdministradorController {

    @Autowired
    AdministradorService administradorService;
    @Autowired
    UsuarioService usuarioService;

    //Registrar administrador
    @PutMapping("/registroAdministrador")
    public ResponseEntity<Integer> registroAdministrador(@RequestBody DataDTO administradorDTO){
        Integer id_administrador = 0;

        id_administrador =this.administradorService.registroAdministrador(administradorDTO.getNombre(), administradorDTO.getApellidoP(),administradorDTO.getApellidoM(), administradorDTO.getCorreoElectronico(), administradorDTO.getTelefono(), administradorDTO.getContrasena());
        return new ResponseEntity<>(id_administrador, HttpStatus.OK);
    }
    //Eliminar usuario por ID
    @DeleteMapping("/eliminarUsuario/{id_usuario}")
    public void eliminarUsuario(@PathVariable Integer id_usuario){
    this.administradorService.eliminarUsuario(id_usuario);
    }

}
