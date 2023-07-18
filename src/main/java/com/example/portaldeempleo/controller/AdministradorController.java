package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.DTO.DataDTO;
import com.example.portaldeempleo.DTO.RespRegDTO;
import com.example.portaldeempleo.entities.Usuario;
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
    public ResponseEntity<?> registroAdministrador(@RequestBody DataDTO administradorDTO){
        Integer id_administrador = 0;
        RespRegDTO respuesta = new RespRegDTO();
        if (administradorDTO.getNombre() != null && administradorDTO.getNombre() != "" && administradorDTO.getApellidoP() != null && administradorDTO.getApellidoP() != "" && administradorDTO.getApellidoM() != null && administradorDTO.getApellidoM() != ""
                && administradorDTO.getCorreoElectronico() != null && administradorDTO.getCorreoElectronico() != "" && administradorDTO.getTelefono() != null && administradorDTO.getTelefono() != "" && administradorDTO.getContrasena() != null && administradorDTO.getContrasena() != "") {

            id_administrador = this.administradorService.registroAdministrador(administradorDTO.getNombre(), administradorDTO.getApellidoP(), administradorDTO.getApellidoM(), administradorDTO.getCorreoElectronico(), administradorDTO.getTelefono(), administradorDTO.getContrasena());
            return new ResponseEntity<>(id_administrador, HttpStatus.OK);
        }else{
            respuesta.setMensaje("No puedes hacer el registro de usuario si dejas algun campo en blanco, vuelve a intentarlo");
            respuesta.setEstatus("ERROR");
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }

    }

    //Eliminar usuario por ID
    @DeleteMapping("/eliminarUsuario/{id_usuario}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Integer id_usuario){
    try {
        if(id_usuario != null && id_usuario != 0) {
            this.administradorService.eliminarUsuario(id_usuario);
        }else{
            RespRegDTO respuesta = new RespRegDTO();
            respuesta.setMensaje("El id ingresado no es válido.");
            respuesta.setEstatus("ERROR");
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
    }catch(Exception e){
        RespRegDTO respuesta = new RespRegDTO();
        respuesta.setMensaje("Error al intentar eliminar el usuario");
        respuesta.setEstatus("ERROR");
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
        return new ResponseEntity<>("Usuario eliminado exitosamente.", HttpStatus.OK);
    }
    //Obtener datos de usuario por correo
    @GetMapping("/obtenerUsuario/{correoElectronico}")
    public Usuario obtenerUsuario(@PathVariable String correoElectronico){
        Usuario usuarioEncontrado = this.administradorService.obtenerUsuario(correoElectronico);
        return usuarioEncontrado;
    }
}
