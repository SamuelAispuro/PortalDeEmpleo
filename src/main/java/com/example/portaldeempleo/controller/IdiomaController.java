package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.DTO.*;
import com.example.portaldeempleo.entities.Habilidad;
import com.example.portaldeempleo.entities.Idioma;
import com.example.portaldeempleo.entities.TipoContratacion;
import com.example.portaldeempleo.services.IdiomaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class IdiomaController {
    @Autowired
    IdiomaService idiomaService;

    //Obtener lista de idiomas
    @GetMapping("/obtenerListaIdiomas")
    public List<Idioma> obtenerListaIdiomas(){
        List<Idioma> listaIdiomas = this.idiomaService.obtenerListaIdiomas();
        return listaIdiomas;
    }
    //CREAR IDIOMA
    @PutMapping("/crearIdioma")
    public ResponseEntity<?> crearIdioma(@RequestBody IdiomaDTO idiomaDTO){
        Integer id_idioma =0;
        Idioma idioma = new Idioma();
        RespRegDTO respuesta = new RespRegDTO();
        if(idiomaDTO.getNombreIdioma() != null && idiomaDTO.getNombreIdioma() != ""){
            idioma = this.idiomaService.crearIdioma(idiomaDTO.getNombreIdioma());
            respuesta.setMensaje("El idioma se ha creado correctamente");
            respuesta.setEstatus(true);
        }else{
            respuesta.setMensaje("El idioma no puede ser creada con campos vacios o invalidos, intentalo de nuevo");
            respuesta.setEstatus(false);
        }
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    //MODIFICAR IDIOMA
    @PutMapping("/modificarIdioma")
    public ResponseEntity<?> modificarIdioma(@RequestBody IdiomaDTO idiomaDTO){
        RespCandDTO respuesta = new RespCandDTO();

        try {
            //Se hace uso del servicio ModificarIdioma y se modifica la información que haya cambiado el usuario
            Idioma idiomaModificado = this.idiomaService.modificarIdioma(idiomaDTO.getId_idioma(), idiomaDTO.getNombreIdioma());
            respuesta.setIdiomaModificado(idiomaModificado);
            respuesta.setEstatus(true);
            respuesta.setMensaje("Datos modificados exitosamente");
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Algo salio mal, intentalo de nuevo mas tarde",HttpStatus.OK);
        }
    }

}
