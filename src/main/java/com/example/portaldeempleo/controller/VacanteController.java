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

        id_vacante =this.vacanteService.crearVacante(vacanteDTO.getNombreVacante(), vacanteDTO.getEspecialista(),vacanteDTO.getSueldo(), vacanteDTO.getId_empresa(), vacanteDTO.getHorario(), vacanteDTO.getId_municipio(), vacanteDTO.getDescripcion(), vacanteDTO.getId_empleador(),vacanteDTO.getId_tipoHorario(),vacanteDTO.getId_tipoContratacion(),vacanteDTO.getId_modalidadTrabajo(),vacanteDTO.getDomicilio());
        return new ResponseEntity<>(id_vacante, HttpStatus.OK);
    }

    //Obtener lista de todas las vacantes
    @GetMapping("/obtenerListaVacantes")
        public List<Vacante> obtenerListaVacantes(){
        List<Vacante> listaVacantes = this.vacanteService.obtenerListaVacantes();
        return listaVacantes;

    }
    //Eliminar vacante
    @DeleteMapping("/eliminarVacante/{id_vacante}")
    public String eliminarVacante(@PathVariable Integer id_vacante){
    this.vacanteService.eliminarVacante(id_vacante);
    return "Vacante eliminada exitosamente";
    }
    //Obtener vacante por id
    @GetMapping("/obtenerVacantePorId/{id_vacante}")
    public ResponseEntity<Vacante> obtenerVacantePorId(@PathVariable Integer id_vacante){
    Vacante vacante = this.vacanteService.obtenerVacantePorId(id_vacante);

return new ResponseEntity<>(vacante, HttpStatus.OK);
    }
    //Modificar vacante
    @PutMapping("/modificarVacante")
    public ResponseEntity<Vacante> modificarVacante(@RequestBody VacanteDTO vacanteDTO){
    Vacante vacanteModificada = this.vacanteService.modificarVacante(vacanteDTO.getNombreVacante(), vacanteDTO.getEspecialista(), vacanteDTO.getSueldo(), vacanteDTO.getHorario(), vacanteDTO.getId_municipio(),vacanteDTO.isEstatus(),vacanteDTO.getId_empresa(), vacanteDTO.getId_empleador(),vacanteDTO.getId_tipoHorario(), vacanteDTO.getId_tipoContratacion(), vacanteDTO.getId_modalidadTrabajo(), vacanteDTO.getDescripcion(), vacanteDTO.getId_vacante(),vacanteDTO.getDomicilio());

    return new ResponseEntity<>(vacanteModificada, HttpStatus.OK);
    }

    //Buscar vacantes cercanas al candidato
    @GetMapping("/obtenerVacantesCerca/{id_municipio}")
    public List<Vacante> buscarVacantesCerca(@PathVariable Integer id_municipio){
        List<Vacante> listaVacantesCerca = this.vacanteService.buscarVacantesCerca(id_municipio);
        return listaVacantesCerca;
    }

    //Buscar vacantes por filtro SUELDO (Mayor a menor)
    @GetMapping("/obtenerVacantesPorSueldo")
    public List<Vacante> buscarVacantesPorSueldo(){
        List<Vacante> listaVacantesPorSueldo = this.vacanteService.buscarVacantesPorSueldo();

        return listaVacantesPorSueldo;
    }

    //Buscar vacantes por palabra clave
    @GetMapping("/obtenerVacantesPorPalabraClave/{palabraClave}")
    public List<Vacante> buscarPorPalabraClave(@PathVariable String palabraClave){
        List<Vacante> listaVacantesEncontradasPorPalabraClave = this.vacanteService.buscarPorPalabraClave(palabraClave);
        return listaVacantesEncontradasPorPalabraClave;
    }

    //Buscar vacantes cerca y por palabra clave
    @GetMapping("/obtenerVacantesCercaYPorPalabraClave")
    public List<Vacante> buscarVacantesCercaYPorPalabraClave(@RequestParam("id_municipio") Integer id_municipio, @RequestParam("palabraClave") String palabraClave){

        List<Vacante> listaVacantesCercaYPalabraClave = this.vacanteService.buscarVacantesCercaYPorPalabraClave(id_municipio, palabraClave);
        return listaVacantesCercaYPalabraClave;

    }


}
