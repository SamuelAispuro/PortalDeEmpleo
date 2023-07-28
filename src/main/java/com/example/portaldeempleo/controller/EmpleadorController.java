package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.DTO.DataDTO;
import com.example.portaldeempleo.DTO.RespRegDTO;
import com.example.portaldeempleo.entities.Empleador;
import com.example.portaldeempleo.services.EmpleadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EmpleadorController {
    @Autowired
    public EmpleadorService empleadorService;

    //Registrar empleador
    @PutMapping("/registroEmpleador")
    public ResponseEntity<?> registroEmpleador(@RequestBody DataDTO empleadorDTO) {
        Integer id_empleador = 0;
        Empleador empleador = new Empleador();
        RespRegDTO respuesta = new RespRegDTO();
        if (empleadorDTO.getNombre() != null && empleadorDTO.getNombre() != "" && empleadorDTO.getApellidoP() != null && empleadorDTO.getApellidoP() != "" && empleadorDTO.getApellidoM() != null && empleadorDTO.getApellidoM() != ""
                && empleadorDTO.getCorreoElectronico() != null && empleadorDTO.getCorreoElectronico() != "" && empleadorDTO.getContrasena() != null && empleadorDTO.getContrasena() != "" && empleadorDTO.getEdad() != null){

            empleador = this.empleadorService.registroEmpleador(empleadorDTO.getNombre(), empleadorDTO.getApellidoP(), empleadorDTO.getApellidoM(), empleadorDTO.getCorreoElectronico(), empleadorDTO.getContrasena());
            if(empleador != null) {
                return new ResponseEntity<>(empleador, HttpStatus.OK);
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
    //Obtener todos los empleadores
    @GetMapping("/obtenerEmpleadoresTodos")
    public List<Empleador> obtenerEmpleadoresTodos(){
        List<Empleador> listaEmpleadoresTodos = empleadorService.obtenerEmpleadoresTodos();
        return listaEmpleadoresTodos;
    }


}
