package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.DTO.DataDTO;
import com.example.portaldeempleo.DTO.RespuestaDTO;
import com.example.portaldeempleo.entities.Usuario;
import com.example.portaldeempleo.repositories.UsuarioRepository;
import com.example.portaldeempleo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;
    //Login
    @PostMapping("/Login")
    public ResponseEntity<?> login(@RequestBody DataDTO requestData){

        Usuario usuarioEncontrado = this.usuarioService.login(requestData.getCorreoElectronico(), requestData.getContrasena());
        RespuestaDTO respuesta = new RespuestaDTO();
        if (requestData.getCorreoElectronico() != null && requestData.getCorreoElectronico() != "" && requestData.getContrasena() != null && requestData.getContrasena() != ""){

            if (usuarioEncontrado != null){
                usuarioEncontrado.setContraseña(null);
                respuesta.setEstatus(true);
                respuesta.setMensaje("Usuario logeado correctamente");
                return new ResponseEntity<>(respuesta, HttpStatus.OK);
            }else{
                respuesta.setEstatus(false);
                respuesta.setMensaje("No se encontro un usuario con estos datos");
                return new ResponseEntity<>(respuesta,HttpStatus.OK);
            }

        }else{
            respuesta.setMensaje("Los campos no pueden ser enviados en blanco, intentalo nuevamente");
            respuesta.setEstatus(false);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }




    }

}
