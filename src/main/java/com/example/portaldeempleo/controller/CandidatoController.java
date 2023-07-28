package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.DTO.DataDTO;
import com.example.portaldeempleo.DTO.RespRegDTO;
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
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CandidatoController {
    @Autowired
    public CandidatoService candidatoService;

//Registrar candidato
@PutMapping("/registroCandidato")
public ResponseEntity<?> registroCandidato(@RequestBody DataDTO candidatoDTO){
Integer id_candidato = 0;
Candidato candidato = new Candidato();

    RespRegDTO respuesta = new RespRegDTO();
if(candidatoDTO.getNombre() != null && candidatoDTO.getNombre() !="" && candidatoDTO.getApellidoP() != null && candidatoDTO.getApellidoP() !="" && candidatoDTO.getApellidoM() != null && candidatoDTO.getApellidoM() !=""
        && candidatoDTO.getCorreoElectronico()!=null && candidatoDTO.getCorreoElectronico()!="" && candidatoDTO.getTelefono()!=null && candidatoDTO.getTelefono()!=""&& candidatoDTO.getContrasena()!=null && candidatoDTO.getContrasena()!="" && candidatoDTO.getEdad()!=null && candidatoDTO.getId_municipio()!=null && candidatoDTO.getId_estado()!=null && candidatoDTO.getDomicilio()!=null) {

    candidato = this.candidatoService.registroCandidato(candidatoDTO.getNombre(), candidatoDTO.getApellidoP(), candidatoDTO.getApellidoM(), candidatoDTO.getCorreoElectronico(), candidatoDTO.getTelefono(), candidatoDTO.getContrasena(), candidatoDTO.getEdad(), candidatoDTO.getId_municipio(), candidatoDTO.getId_estado(), candidatoDTO.getDomicilio());
    if(candidato != null) {
        return new ResponseEntity<>(candidato, HttpStatus.OK);
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
//Obtener postulaciones de un candidato por ID
@GetMapping("/obtenerPostulacionesPorIdDeCandidato/{id}")
    public ResponseEntity<?> obtenerPostulacionesPorIdDeCandidato(@PathVariable Integer id){
    List<Postulacion> listaPostulacionesCandidato = new ArrayList<Postulacion>();
    listaPostulacionesCandidato = this.candidatoService.obtenerPostulacionesPorIdDeCandidato(id);
    return new ResponseEntity<>(listaPostulacionesCandidato, HttpStatus.OK);

}
//Obtener un candidato por ID
@GetMapping("/obtenerCandidatoPorId/{id}")
    public ResponseEntity<?> obtenerCandidatoPorId(@PathVariable Integer id){
    Candidato candidatoEncontrado = this.candidatoService.obtenerCandidatoPorId(id);
    return new ResponseEntity<>(candidatoEncontrado, HttpStatus.OK);
}
//Modificar datos de un candidato
    @PutMapping("/modificarCandidato")
    public ResponseEntity<?> modificarCandidato(@RequestBody DataDTO candidatoDTO){
    Candidato candidatoModificado = this.candidatoService.modificarCandidato(candidatoDTO.getNombre(), candidatoDTO.getApellidoP(), candidatoDTO.getApellidoM(), candidatoDTO.getDomicilio(), candidatoDTO.getDescripcion(), candidatoDTO.getCentroEducativo(), candidatoDTO.getPuestoActual(), candidatoDTO.getId_municipio(), candidatoDTO.getId_estado(), candidatoDTO.getId_candidato());
    return new ResponseEntity<>(candidatoModificado, HttpStatus.OK);
    }

    /**
     * Subir imagen de Curriculum
     * @param id
     * @param imagen
     * @return respuesta
     * @throws IOException
     */
    @PostMapping("/subirImagenCv")
    public ResponseEntity<?> subirImagenCv(@RequestParam String id, @RequestParam MultipartFile imagen) throws IOException {
        // Verificar que se haya enviado una imagen
        if (imagen == null || imagen.isEmpty()) {
            return new ResponseEntity<>("Debe enviar una imagen", HttpStatus.BAD_REQUEST);
        }

        String respuesta = candidatoService.subirFotoCv(imagen,id);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);

    }

}
