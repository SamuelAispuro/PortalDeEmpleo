package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.DTO.*;
import com.example.portaldeempleo.entities.Candidato;
import com.example.portaldeempleo.entities.Postulacion;
import com.example.portaldeempleo.entities.Vacante;
import com.example.portaldeempleo.services.CandidatoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CandidatoController {
    @Autowired
    public CandidatoService candidatoService;

    //Registrar candidato
    @PutMapping("/registroCandidato")
    public ResponseEntity<?> registroCandidato(@RequestBody DataDTO candidatoDTO) {

        Candidato candidato = new Candidato();
        RespRegDTO respuesta = new RespRegDTO();
        try {

            //Se evalua que los datos ingresados por el usuario no vengan vacios
            if (candidatoDTO.getNombre() != null && candidatoDTO.getNombre() != "" && candidatoDTO.getApellidoP() != null && candidatoDTO.getApellidoP() != "" && candidatoDTO.getApellidoM() != null && candidatoDTO.getApellidoM() != ""
                    && candidatoDTO.getCorreoElectronico() != null && candidatoDTO.getCorreoElectronico() != "" && candidatoDTO.getTelefono() != null && candidatoDTO.getTelefono() != "" && candidatoDTO.getContrasena() != null && candidatoDTO.getContrasena() != "" && candidatoDTO.getFechaNacimientoStr() != null && candidatoDTO.getFechaNacimientoStr() != "" && candidatoDTO.getId_municipio() != null && candidatoDTO.getId_estado() != null) {
                DateTimeFormatter format = new DateTimeFormatterBuilder().append(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toFormatter();
                LocalDate fechaNacimientoFormateada = LocalDate.parse(candidatoDTO.getFechaNacimientoStr(),format);
                candidatoDTO.setFechaNacimiento(fechaNacimientoFormateada);
                //Se hace uso del servicio registroCandidato para crear una cuenta de candidato en caso de que sean validos todos los datos requeridos
                candidato = this.candidatoService.registroCandidato(candidatoDTO.getNombre(), candidatoDTO.getApellidoP(), candidatoDTO.getApellidoM(), candidatoDTO.getCorreoElectronico(), candidatoDTO.getTelefono(), candidatoDTO.getContrasena(), candidatoDTO.getFechaNacimiento(), candidatoDTO.getId_municipio(), candidatoDTO.getId_estado(), candidatoDTO.getDomicilio(), candidatoDTO.getPuestoActual(), candidatoDTO.getDescripcion(), candidatoDTO.getCentroEducativo());

                //Caso de exito
                if (candidato != null) {
                    respuesta.setMensaje("Cuenta creada exitosamente");
                    respuesta.setEstatus(true);
                    return new ResponseEntity<>(respuesta, HttpStatus.OK);
                }
                //Caso de fracaso: el correo que se quiere registrar ya se encuentra asociado a una cuenta
                else {
                    respuesta.setMensaje("Correo ya registrado");
                    respuesta.setEstatus(false);
                    return new ResponseEntity<>(respuesta, HttpStatus.OK);
                }
            }
            //Caso de fracaso: el usuario dejo algun campo obligatorio en blanco al momento del registro
            else {
                respuesta.setMensaje("No puedes hacer el registro de usuario si dejas algun campo en blanco, vuelve a intentarlo");
                respuesta.setEstatus(false);
                return new ResponseEntity<>(respuesta, HttpStatus.OK);
            }
        }catch(Exception e){
            respuesta.setMensaje(e.getMessage());
            respuesta.setEstatus(false);
            return new ResponseEntity<>(respuesta,HttpStatus.OK);
        }
    }

//Obtener postulaciones de un candidato por ID
@GetMapping("/obtenerPostulacionesPorIdDeCandidato/{id}")
    public ResponseEntity<?> obtenerPostulacionesPorIdDeCandidato(@PathVariable Integer id){
    List<Postulacion> listaPostulacionesCandidato = new ArrayList<Postulacion>();

    try {
        //Se buscan las postulaciones de un candidato mediante su ID
        listaPostulacionesCandidato = this.candidatoService.obtenerPostulacionesPorIdDeCandidato(id);
        return new ResponseEntity<>(listaPostulacionesCandidato, HttpStatus.OK);
    }catch(Exception e){
        return new ResponseEntity<>("Algo salio mal, intentalo de nuevo mas tarde", HttpStatus.OK);
    }
}
//Obtener un candidato por ID
@GetMapping("/obtenerCandidatoPorId/{id}")
    public ResponseEntity<?> obtenerCandidatoPorId(@PathVariable Integer id){
    Candidato candidatoEncontrado = new Candidato();
    try {
        if (id != null && id > 0) {
            //Se busca la información de un candidato mediante su ID
            candidatoEncontrado = this.candidatoService.obtenerCandidatoPorId(id);
            return new ResponseEntity<>(candidatoEncontrado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("el id enviado no es válido", HttpStatus.OK);
        }
    }catch(Exception e){
        return new ResponseEntity<>("Algo salio mal, intentalo de nuevo mas tarde",HttpStatus.OK);
    }
}

    /**
     * Modificar datos de un candidato
     * @param candidatoDTO
     * @return
     */
    @PutMapping("/modificarCandidato")
    public ResponseEntity<?> modificarCandidato(@RequestBody DataDTO candidatoDTO){
        RespCandDTO respuesta = new RespCandDTO();

        try {
            //Se hace uso del servicio ModificarCandidato y se modifica la información que haya cambiado el usuario
            Candidato candidatoModificado = this.candidatoService.modificarCandidato(candidatoDTO.getId_candidato(), candidatoDTO.getNombre(), candidatoDTO.getApellidoP(), candidatoDTO.getApellidoM(), candidatoDTO.getDomicilio(), candidatoDTO.getDescripcion(), candidatoDTO.getCentroEducativo(), candidatoDTO.getPuestoActual(), candidatoDTO.getId_municipio(), candidatoDTO.getId_estado(), candidatoDTO.getTelefono(), candidatoDTO.getProfesion(), candidatoDTO.getFechaNacimientoStr());
            respuesta.setCandidatoModificado(candidatoModificado);
            respuesta.setEstatus(true);
            respuesta.setMensaje("Datos modificados exitosamente");
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Algo salio mal, intentalo de nuevo mas tarde",HttpStatus.OK);
        }
    }

    //Anadir idiomas
    @PutMapping("/agregarIdiomas")
    public ResponseEntity<RespPostDTO> añadirIdiomas(@RequestBody IdiomaDTO idiomaDTO){
        RespPostDTO respuesta = new RespPostDTO();
        if(idiomaDTO.getId_candidato()!=null && idiomaDTO.getId_candidato()>0){
            candidatoService.añadirIdiomas(idiomaDTO);
            respuesta.setMensaje("Idiomas modificados correctamente");
            respuesta.setEstatus(true);
        }else{
            respuesta.setMensaje("No se pudo hacer la modificacion, intentelo de nuevo");
            respuesta.setEstatus(false);
        }
        return new ResponseEntity<>(respuesta,HttpStatus.OK);
    }

    //Anadir habilidades
    @PutMapping("/agregarHabilidades")
    public ResponseEntity<RespPostDTO> añadirHabilidades(@RequestBody HabilidadDTO habilidadDTO){
        RespPostDTO respuesta = new RespPostDTO();
        if(habilidadDTO.getId_candidato()!=null && habilidadDTO.getId_candidato()>0){
            candidatoService.añadirHabilidades(habilidadDTO);
            respuesta.setMensaje("Habilidades modificadas correctamente");
            respuesta.setEstatus(true);
        }else{
            respuesta.setMensaje("No se pudo hacer la modificacion, intentelo de nuevo");
            respuesta.setEstatus(false);
        }
        return new ResponseEntity<>(respuesta,HttpStatus.OK);
    }

}
