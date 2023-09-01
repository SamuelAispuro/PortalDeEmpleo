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

@CrossOrigin(origins = "*")
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
        try {
            if (administradorDTO.getNombre() != null && administradorDTO.getNombre() != "" && administradorDTO.getApellidoP() != null && administradorDTO.getApellidoP() != "" && administradorDTO.getApellidoM() != null && administradorDTO.getApellidoM() != ""
                    && administradorDTO.getCorreoElectronico() != null && administradorDTO.getCorreoElectronico() != "" && administradorDTO.getContrasena() != null && administradorDTO.getContrasena() != "") {

                administrador = this.administradorService.registroAdministrador(administradorDTO.getNombre(), administradorDTO.getApellidoP(), administradorDTO.getApellidoM(), administradorDTO.getCorreoElectronico(),administradorDTO.getTelefono(), administradorDTO.getContrasena());
                if (administrador != null) {
                    respuesta.setMensaje("Cuenta creada exitosamente");
                    respuesta.setEstatus(true);
                    return new ResponseEntity<>(respuesta, HttpStatus.OK);
                } else {
                    respuesta.setMensaje("Correo ya registrado");
                    respuesta.setEstatus(false);
                    return new ResponseEntity<>(respuesta, HttpStatus.OK);
                }
            } else {
                respuesta.setMensaje("No puedes hacer el registro de usuario si dejas algun campo en blanco, vuelve a intentarlo");
                respuesta.setEstatus(false);
                return new ResponseEntity<>(respuesta, HttpStatus.OK);
            }
        }catch(Exception e){
            respuesta.setMensaje("Algo salio mal, intentalo de nuevo mas tarde");
            respuesta.setEstatus(false);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }

    }

    //Eliminar usuario por ID
    @DeleteMapping("/eliminarUsuario/{id_usuario}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Integer id_usuario){
    try {
        if(id_usuario != null && id_usuario != 0) {
            this.administradorService.eliminarUsuario(id_usuario);
            return new ResponseEntity<>("Usuario eliminado exitosamente.", HttpStatus.OK);
        }else{
            RespRegDTO respuesta = new RespRegDTO();
            respuesta.setMensaje("El id ingresado no es válido.");
            respuesta.setEstatus(false);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
    }catch(Exception e){
        RespRegDTO respuesta = new RespRegDTO();
        respuesta.setMensaje("No se encontró un usuario con este ID");
        respuesta.setEstatus(false);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
    }
    //Obtener datos de usuario por correo
    @GetMapping("/obtenerUsuario/{correoElectronico}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable String correoElectronico){
        Usuario usuarioEncontrado = new Usuario();
        try {
            usuarioEncontrado = this.administradorService.obtenerUsuario(correoElectronico);
            if (usuarioEncontrado != null) {
                return new ResponseEntity<>(usuarioEncontrado, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("No se encontro un usuario asociado a este correo",HttpStatus.OK);
            }
        }catch(Exception e){
            return new ResponseEntity<>("Algo salio mal, intentalo de nuevo",HttpStatus.OK);
        }
    }

    //Obtener usuario Completo por correo
    @GetMapping("/obtenerUsuarioCompleto/{correoElectronico}")
    public ResponseEntity<?> obtenerUsuarioCompleto(@PathVariable String correoElectronico) {
        Object objetoEncontrado;
        try {
            objetoEncontrado = this.administradorService.obtenerUsuarioCompleto(correoElectronico);
            if (objetoEncontrado != null) {

                return new ResponseEntity<>(objetoEncontrado, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No se encontro un usuario asociado a este correo", HttpStatus.OK);
            }
        }catch(Exception e){
            return new ResponseEntity<>("Algo salio mal, intentalo de nuevo",HttpStatus.OK);
        }
    }

}
