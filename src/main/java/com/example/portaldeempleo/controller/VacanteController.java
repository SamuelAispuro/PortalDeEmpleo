package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.DTO.DataDTO;
import com.example.portaldeempleo.DTO.RespuestaDTO;
import com.example.portaldeempleo.DTO.VacanteDTO;
import com.example.portaldeempleo.entities.Candidato;
import com.example.portaldeempleo.entities.Vacante;
import com.example.portaldeempleo.repositories.VacanteRepository;
import com.example.portaldeempleo.services.CandidatoService;
import com.example.portaldeempleo.services.VacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class VacanteController {

    @Autowired
    public VacanteService vacanteService;
    @Autowired

    public VacanteRepository vacanteRepository;

    //Crear vacante
    @PutMapping("/crearVacante")
    public ResponseEntity<Integer> crearVacante(@RequestBody VacanteDTO vacanteDTO){
        Integer id_vacante=0;

        if(vacanteDTO.getNombreVacante()!=null && vacanteDTO.getNombreVacante()!="" && vacanteDTO.getEspecialista()!=null && vacanteDTO.getEspecialista()!="" && vacanteDTO.getSueldo()!=null && vacanteDTO.getId_empresa()!=null && vacanteDTO.getId_empresa()!=0 && vacanteDTO.getHorario()!=null && vacanteDTO.getHorario()!="" && vacanteDTO.getId_municipio()!=null && vacanteDTO.getId_estado()!=null && vacanteDTO.getDescripcion()!=null && vacanteDTO.getDescripcion()!="" && vacanteDTO.getId_empleador()!=null && vacanteDTO.getId_tipoHorario()!=null && vacanteDTO.getId_tipoContratacion()!=null && vacanteDTO.getId_modalidadTrabajo()!=null && vacanteDTO.getDomicilio()!=null && vacanteDTO.getDomicilio()!="" &&  vacanteDTO.getPublicarAhora()!=null) {

            DateTimeFormatter format = new DateTimeFormatterBuilder().append(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toFormatter();

            LocalDate fechaPublicacionFormateada = LocalDate.parse(vacanteDTO.getFechaPublicacionStr(),format);

            vacanteDTO.setFechaPublicacion(fechaPublicacionFormateada);

            id_vacante = this.vacanteService.crearVacante(vacanteDTO.getNombreVacante(), vacanteDTO.getEspecialista(), vacanteDTO.getSueldo(), vacanteDTO.getId_empresa(), vacanteDTO.getHorario(), vacanteDTO.getId_municipio(), vacanteDTO.getId_estado(), vacanteDTO.getDescripcion(), vacanteDTO.getId_empleador(), vacanteDTO.getId_tipoHorario(), vacanteDTO.getId_tipoContratacion(), vacanteDTO.getId_modalidadTrabajo(), vacanteDTO.getDomicilio(), vacanteDTO.getFechaPublicacion(), vacanteDTO.getPublicarAhora());
        }
        return new ResponseEntity<>(id_vacante, HttpStatus.OK);
    }

    //Obtener lista de todas las vacantes
    @GetMapping("/obtenerListaVacantes")
    public List<Vacante> obtenerListaVacantes(){
        List<Vacante> listaVacantesActivas = this.vacanteService.obtenerListaVacantesActivas();
        return listaVacantesActivas;
    }
   
    /*
    //Obtener todas las vacantes paginadas
    @GetMapping("/vacantes/page/{page}")
    public Page<Vacante> consultaPage(@PathVariable Integer page){
        Pageable pageable = PageRequest.of(page, 5, Sort.by("sueldo").descending());
        return vacanteService.findAllPage(pageable);
    }
    
    */
    
    //PAGINADOS ----------------
    
    /*
     * 
     * ORDENAR POR SUELDO
     *Sort.by("sueldo").descending()
     *
     */
    
    
    //OBTENER VACANTES FILTRADAS POR ESTATUS
    @GetMapping("/vacantes/page/{page}")
    public Page<Vacante> consultaPage(@PathVariable Integer page){
        Pageable pageable = PageRequest.of(page, 5, Sort.by("sueldo").descending());
        
        return this.vacanteService.findAllStatus(pageable);
    }
    
    /*
    @GetMapping("/vacantes/page/nombre")
    public ResponseEntity<Map<String, Object>> consultaPage(@RequestParam("nombre") String nombre, @RequestParam("page") Integer page){
        Pageable pageable = PageRequest.of(page,this.vacanteRepository.findAllByEstatus(true).size());
        List<Vacante> vacantes = new ArrayList<Vacante>();
        Page<Vacante> vac = this.vacanteService.findAllStatus(pageable);
        
        for(Vacante vacante:vac.getContent()){
        	  if(vacante.getNombreVacante().toLowerCase().contains(nombre.toLowerCase())){
        		  vacantes.add(vacante);
              }
        }
   
        Map<String, Object> response = new HashMap<>();
        response.put("content", vacantes);
        response.put("currentPage", vac.getNumber());
        response.put("totalItems", vac.getTotalElements());
        response.put("totalPages", 0);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    */
    
    //OBTENER VACANTES FILTRADAS POR ESTADO Y ESTATUS
    @GetMapping("/vacantes/page/estado")
    public Page<Vacante> consultaPageEstado(@RequestParam("id_estado") Integer id_estado, @RequestParam("page") Integer page){
        Pageable pageable = PageRequest.of(page, 5, Sort.by("sueldo").descending());
        return this.vacanteService.findAllEstado(pageable,id_estado);
    }
    
    
    //OBTENER VACANTES FILTRADAS POR ESTADO Y ESTATUS
    @GetMapping("/vacantes/page/municipio")
    public Page<Vacante> consultaPageMunicipio(@RequestParam("id_municipio") Integer id_municipio, @RequestParam("page") Integer page){
        Pageable pageable = PageRequest.of(page, 5,Sort.by("sueldo").descending());
        return this.vacanteService.findAllMunicipio(pageable,id_municipio);
    }
    
    
    //OBTENER VACANTES FILTRADAS POR NOMBRE Y ESTATUS
    @GetMapping("/vacantes/page/nombre")
    public Page<Vacante> consultarPageNombre(@RequestParam("nombre") String nombre, @RequestParam("page") Integer page){
        Pageable pageable = PageRequest.of(page, 5,Sort.by("sueldo").descending());
        return this.vacanteService.findAllNombreEstatus(nombre,pageable);
    }
    
    @GetMapping("/vacantes/page/nombre/estado")
    public Page<Vacante> consultarPageNombreEstado(@RequestParam("nombre") String nombre, @RequestParam("id_estado") Integer id_estado, @RequestParam("page") Integer page){
        Pageable pageable = PageRequest.of(page, 5, Sort.by("sueldo").descending());
        return this.vacanteService.findAllNombreEstatusEstado(nombre,id_estado,pageable);
    }
    
    @GetMapping("/vacantes/page/nombre/municipio")
    public Page<Vacante> consultarPageNombreMunicipio(@RequestParam("nombre") String nombre, @RequestParam("id_municipio") Integer id_municipio, @RequestParam("page") Integer page){
        Pageable pageable = PageRequest.of(page, 5, Sort.by("sueldo").descending());
        return this.vacanteService.findAllNombreEstatusMunicipio(nombre,id_municipio,pageable);
    }
    
    //PAGINADOS ----------------
    
 
    //Eliminar vacante
    @DeleteMapping("/eliminarVacante/{id_vacante}")
    public ResponseEntity<?> eliminarVacante(@PathVariable Integer id_vacante){
        VacanteDTO respuesta = new VacanteDTO();
    this.vacanteService.eliminarVacante(id_vacante);
    respuesta.setMensaje("Vacante eliminada exitosamente");
    respuesta.setEstatus(true);
    return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    //Obtener vacante por id
    @GetMapping("/obtenerVacantePorId/{id_vacante}")
    public ResponseEntity<Vacante> obtenerVacantePorId(@PathVariable Integer id_vacante){
    Vacante vacante = this.vacanteService.obtenerVacantePorId(id_vacante);

    return new ResponseEntity<>(vacante, HttpStatus.OK);
    }

    //Modificar vacante
    @PutMapping("/modificarVacante")
    public ResponseEntity<?> modificarVacante(@RequestBody VacanteDTO vacanteDTO){
        VacanteDTO respuesta = new VacanteDTO();
    Vacante vacanteModificada = this.vacanteService.modificarVacante(vacanteDTO.getNombreVacante(), vacanteDTO.getEspecialista(), vacanteDTO.getSueldo(), vacanteDTO.getHorario(), vacanteDTO.getId_municipio(),vacanteDTO.isEstatus(),vacanteDTO.getId_empresa(), vacanteDTO.getId_empleador(),vacanteDTO.getId_tipoHorario(), vacanteDTO.getId_tipoContratacion(), vacanteDTO.getId_modalidadTrabajo(), vacanteDTO.getDescripcion(), vacanteDTO.getId_vacante(),vacanteDTO.getDomicilio());
    respuesta.setMensaje("La vacante se modifico exitosamente");
    respuesta.setEstatus(true);
    return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    /*
    //Buscar vacantes cercanas al candidato
    @GetMapping("/obtenerVacantesCerca/{id_municipio}")
    public List<Vacante> buscarVacantesCerca(@PathVariable Integer id_municipio){
        List<Vacante> listaVacantesCerca = this.vacanteService.buscarVacantesCerca(id_municipio);
        return listaVacantesCerca;
    }
     */
    
    //Buscar vacantes por filtro SUELDO (Mayor a menor)
    @GetMapping("/obtenerVacantesPorSueldo")
    public List<Vacante> buscarVacantesPorSueldo(){
        List<Vacante> listaVacantesPorSueldo = this.vacanteService.buscarVacantesPorSueldo();

        return listaVacantesPorSueldo;
    }

    /*
    //Buscar vacantes por palabra clave
    @GetMapping("/obtenerVacantesPorPalabraClave/{palabraClave}")
    public List<Vacante> buscarPorPalabraClave(@PathVariable String palabraClave){
        List<Vacante> listaVacantesEncontradasPorPalabraClave = this.vacanteService.buscarPorPalabraClave(palabraClave);
        return listaVacantesEncontradasPorPalabraClave;
    }
	*/
    //Buscar vacantes cerca y por palabra clave
    @GetMapping("/obtenerVacantesCercaYPorPalabraClave")
    public List<Vacante> buscarVacantesCercaYPorPalabraClave(@RequestParam("id_municipio") Integer id_municipio, @RequestParam("palabraClave") String palabraClave){

        List<Vacante> listaVacantesCercaYPalabraClave = this.vacanteService.buscarVacantesCercaYPorPalabraClave(id_municipio, palabraClave);
        return listaVacantesCercaYPalabraClave;
    }

    //Buscar vacantes dentro de un estado
    @GetMapping("/obtenerVacantesEstado/{id_estado}")
    public List<Vacante> buscarVacantesEstado(@PathVariable Integer id_estado){
        List<Vacante> listaVacantesEstado = this.vacanteService.buscarVacantesEstado(id_estado);
        return listaVacantesEstado;
    }

    //Obtener lista candidatos de vacante
    @GetMapping("/obtenerCandidatosVacante/{id_vacante}")
    public List<Candidato> obtenerCandidatosVacante(@PathVariable Integer id_vacante){
        List<Candidato> listaCandidatos = this.vacanteService.obtenerCandidatosDeVacante(id_vacante);
        return listaCandidatos;
    }

    //Buscar vacantes por estado y palabra clave
    @GetMapping("/obtenerVacantesEstadoYPorPalabraClave")
    public List<Vacante> buscarVacantesEstadoYPorPalabraClave(@RequestBody Integer id_estado, @RequestBody String palabraClave){

        List<Vacante> listaVacantesEstadoYPalabraClave = this.vacanteService.buscarVacantesEstadoYPorPalabraClave(id_estado, palabraClave);
        return listaVacantesEstadoYPalabraClave;

    }

    //Eliminar vacante por dias
    @DeleteMapping("/eliminarVacantePorDias")
    public void eliminarVacantePorDias(){
        vacanteService.eliminarVacantePorDias();
    }

    //Obtener lista de vacantes filtro actividad
    @GetMapping("/obtenerListaVacantesActividad")
    public List<Vacante> obtenerListaVacantesFiltro(@RequestParam String tipoFiltro){
        List<Vacante> listaVacantes = this.vacanteService.obtenerListaVacantesFiltro(tipoFiltro);
        return listaVacantes;
    }

}
