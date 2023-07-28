package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.DTO.DataDTO;
import com.example.portaldeempleo.DTO.RespRegDTO;
import com.example.portaldeempleo.entities.Administrador;
import com.example.portaldeempleo.entities.Candidato;
import com.example.portaldeempleo.entities.Usuario;
import com.example.portaldeempleo.repositories.CandidatoRepository;
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
    @Autowired
    CandidatoRepository candidatoRepository;


    //Registrar administrador
    @PutMapping("/registroAdministrador")
    public ResponseEntity<?> registroAdministrador(@RequestBody DataDTO administradorDTO){
        Integer id_administrador = 0;
        Administrador administrador = new Administrador();
        RespRegDTO respuesta = new RespRegDTO();
        if (administradorDTO.getNombre() != null && administradorDTO.getNombre() != "" && administradorDTO.getApellidoP() != null && administradorDTO.getApellidoP() != "" && administradorDTO.getApellidoM() != null && administradorDTO.getApellidoM() != ""
                && administradorDTO.getCorreoElectronico() != null && administradorDTO.getCorreoElectronico() != "" && administradorDTO.getContrasena() != null && administradorDTO.getContrasena() != "") {

            administrador = this.administradorService.registroAdministrador(administradorDTO.getNombre(), administradorDTO.getApellidoP(), administradorDTO.getApellidoM(), administradorDTO.getCorreoElectronico(), administradorDTO.getContrasena());
            if(administrador != null) {
            return new ResponseEntity<>(administrador, HttpStatus.OK);
        }else{
                respuesta.setMensaje("Correo ya registrado");
                respuesta.setEstatus("ERROR");
                return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
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
        respuesta.setMensaje("No se encontró un usuario con este ID");
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

    //Obtener usuario Completo por correo
    @GetMapping("/obtenerUsuarioCompleto/{correoElectronico}")
    public ResponseEntity<?> obtenerUsuarioCompleto(@PathVariable String correoElectronico){

        Object candidatoEncontrado = this.administradorService.obtenerUsuarioCompleto(correoElectronico);

        return new ResponseEntity<>(candidatoEncontrado, HttpStatus.OK);
    }

}
