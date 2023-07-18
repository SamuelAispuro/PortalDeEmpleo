package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.DTO.DataDTO;
import com.example.portaldeempleo.DTO.RespRegDTO;
import com.example.portaldeempleo.services.EmpleadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EmpleadorController {
    @Autowired
    public EmpleadorService empleadorService;

    //Registrar empleador
    @PutMapping("/registroEmpleador")
    public ResponseEntity<?> registroEmpleador(@RequestBody DataDTO empleadorDTO) {
        Integer id_empleador = 0;
        RespRegDTO respuesta = new RespRegDTO();
        if (empleadorDTO.getNombre() != null && empleadorDTO.getNombre() != "" && empleadorDTO.getApellidoP() != null && empleadorDTO.getApellidoP() != "" && empleadorDTO.getApellidoM() != null && empleadorDTO.getApellidoM() != ""
                && empleadorDTO.getCorreoElectronico() != null && empleadorDTO.getCorreoElectronico() != "" && empleadorDTO.getTelefono() != null && empleadorDTO.getTelefono() != "" && empleadorDTO.getContrasena() != null && empleadorDTO.getContrasena() != "" && empleadorDTO.getEdad() != null){

            id_empleador = this.empleadorService.registroEmpleador(empleadorDTO.getNombre(), empleadorDTO.getApellidoP(), empleadorDTO.getApellidoM(), empleadorDTO.getCorreoElectronico(), empleadorDTO.getTelefono(), empleadorDTO.getContrasena());
        return new ResponseEntity<>(id_empleador, HttpStatus.OK);
    }else{
            respuesta.setMensaje("No puedes hacer el registro de usuario si dejas algun campo en blanco, vuelve a intentarlo");
            respuesta.setEstatus("ERROR");
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
    }


}
