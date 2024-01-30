package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.DTO.DataDTO;
import com.example.portaldeempleo.DTO.IdPostulacionDTO;
import com.example.portaldeempleo.DTO.RespPostDTO;
import com.example.portaldeempleo.DTO.RespRegDTO;
import com.example.portaldeempleo.entities.Empleador;
import com.example.portaldeempleo.entities.Postulacion;
import com.example.portaldeempleo.entities.Vacante;
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
        Empleador empleador = new Empleador();
        RespRegDTO respuesta = new RespRegDTO();
        try{
        if (empleadorDTO.getNombre() != null && empleadorDTO.getNombre() != "" && empleadorDTO.getApellidoP() != null && empleadorDTO.getApellidoP() != "" && empleadorDTO.getApellidoM() != null && empleadorDTO.getApellidoM() != ""
                && empleadorDTO.getCorreoElectronico() != null && empleadorDTO.getCorreoElectronico() != "" && empleadorDTO.getContrasena() != null && empleadorDTO.getContrasena() != "") {

            empleador = this.empleadorService.registroEmpleador(empleadorDTO.getNombre(), empleadorDTO.getApellidoP(), empleadorDTO.getApellidoM(), empleadorDTO.getCorreoElectronico(), empleadorDTO.getTelefono(), empleadorDTO.getContrasena(), empleadorDTO.getRutaImagenPerfil(), empleadorDTO.getRutaImagenPortada());
            if (empleador != null) {
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
            return new ResponseEntity<>("Algo salio mal, intentalo de nuevo mas tarde",HttpStatus.OK);
        }
    }
    //Obtener todos los empleadores
    @GetMapping("/obtenerEmpleadoresTodos")
    public List<Empleador> obtenerEmpleadoresTodos(){
        List<Empleador> listaEmpleadoresTodos = empleadorService.obtenerEmpleadoresTodos();
        return listaEmpleadoresTodos;
    }

    //Obtener vacantes publicadas de empleador
    @GetMapping("/obtenerVacantesPorIdEmpleador/{id_empleador}")
    public List<Vacante> vacantesPublicadasPorId(@PathVariable Integer id_empleador){
        List<Vacante> listaVacantes = empleadorService.vacantesPublicadasPorId(id_empleador);
        return listaVacantes;
    }

    //Aceptar postulación
    @PutMapping("/aceptarPostulacion")
    public ResponseEntity<?> aceptarPostulacion(@RequestBody IdPostulacionDTO idPostulacionDTO){
        RespPostDTO respuesta = new RespPostDTO();

        Postulacion postulacionModificada = this.empleadorService.aceptarPostulacion(idPostulacionDTO.getId_postulacion());
        respuesta.setEstatus(true);
        respuesta.setMensaje("La postulación fue aceptada con exito");
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }


    //OBTENER LISTA EMPLEADORES
    @GetMapping("/obtenerListaEmpleadores")
    public ResponseEntity<?> obtenerListaEmpleadores(){
        List<Empleador> listaEmpleadores = empleadorService.obtenerListaEmpleadores();
        return new ResponseEntity<>(listaEmpleadores,HttpStatus.OK);
    }

    //OBTENER EMPLEADORES ACTIVOS
    @GetMapping("/obtenerListaEmpleadoresActivos")
    public ResponseEntity<?> obtenerListaEmpleadoresActivos(){
        List<Empleador> listaEmpleadoresActivos = empleadorService.obtenerListaEmpleadoresActivos();
        return new ResponseEntity<>(listaEmpleadoresActivos,HttpStatus.OK);
    }

    //OBTENER EMPLEADORES INACTIVOS
    @GetMapping("/obtenerListaEmpleadoresInactivos")
    public ResponseEntity<?> obtenerListaEmpleadoresInactivos(){
        List<Empleador> listaEmpleadoresInactivos = empleadorService.obtenerListaEmpleadoresInactivos();
        return new ResponseEntity<>(listaEmpleadoresInactivos,HttpStatus.OK);
    }


}
