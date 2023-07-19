package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.DTO.RespuestaDTO;
import com.example.portaldeempleo.DTO.VacanteDTO;
import com.example.portaldeempleo.entities.Vacante;
import com.example.portaldeempleo.services.CandidatoService;
import com.example.portaldeempleo.services.VacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class VacanteController {

    @Autowired
    public VacanteService vacanteService;

    //Crear vacante
    @PutMapping("/crearVacante")
    public ResponseEntity<Integer> crearVacante(@RequestBody VacanteDTO vacanteDTO){
        Integer id_vacante = 0;

        id_vacante =this.vacanteService.crearVacante(vacanteDTO.getNombreVacante(), vacanteDTO.getEspecialista(),vacanteDTO.getSueldo(), vacanteDTO.getId_empresa());
        return new ResponseEntity<>(id_vacante, HttpStatus.OK);
    }

    //Obtener lista de todas las vacantes
    @GetMapping("/obtenerListaVacantes")
        public List<Vacante> obtenerListaVacantes(){
        List<Vacante> listaVacantes = this.vacanteService.obtenerListaVacantes();
        return listaVacantes;

    }
    //Eliminar vacante
    @DeleteMapping("/eliminarVacante/{id}")
    public String eliminarVacante(@PathVariable Integer id_vacante){
    this.vacanteService.eliminarVacante(id_vacante);
    return "Vacante eliminada exitosamente";
    }
    //Obtener vacante por id
    @GetMapping("/obtenerVacantePorId/{id}")
    public ResponseEntity<Vacante> obtenerVacantePorId(@PathVariable Integer id_vacante){
    Vacante vacante = this.vacanteService.obtenerVacantePorId(id_vacante);

return new ResponseEntity<>(vacante, HttpStatus.OK);
    }
    //Modificar vacante
    @PutMapping("/modificarVacante")
    public ResponseEntity<Vacante> modificarVacante(@RequestBody VacanteDTO vacanteDTO){
    Vacante vacanteModificada = this.vacanteService.modificarVacante(vacanteDTO.getNombreVacante(), vacanteDTO.getEspecialista(), vacanteDTO.getSueldo(), vacanteDTO.getHorario(), vacanteDTO.getId_municipio(),vacanteDTO.isEstatus(),vacanteDTO.getId_empresa(), vacanteDTO.getId_empleador(),vacanteDTO.getId_tipoHorario(), vacanteDTO.getId_tipoContratacion(), vacanteDTO.getId_modalidadTrabajo(), vacanteDTO.getDescripcion(), vacanteDTO.getId_vacante());

    return new ResponseEntity<>(vacanteModificada, HttpStatus.OK);
    }


}
