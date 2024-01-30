package com.example.portaldeempleo.services;

import com.example.portaldeempleo.dominio.FormatoExcel;
import com.example.portaldeempleo.dominio.FormatoVacantesExcel;
import com.example.portaldeempleo.entities.*;
import com.example.portaldeempleo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class VacanteService {
    @Autowired
    VacanteRepository vacanteRepository;
    @Autowired
    CandidatoRepository candidatoRepository;
    @Autowired
    MunicipioRepository municipioRepository;
    @Autowired
    PostulacionRepository postulacionRepository;
    @Autowired
    EstadoRepository estadoRepository;
    @Autowired
    ProcesosAutomaticosRepository procesosAutomaticosRepository;


    //Metodo para crear una vacante
    public Integer crearVacante(String nombreVacante, String especialista, Integer sueldo, Integer id_empresa,String horario,Integer id_municipio,Integer id_estado, String descripcion,Integer id_empleador,Integer id_tipoHorario,Integer id_tipoContratacion,Integer id_modalidadTrabajo, String domicilio, LocalDate fechaPublicacion, Boolean publicarAhora){
        Empresa empresa = new Empresa();
        empresa.setId_empresa(id_empresa);

        Municipio municipio = new Municipio();
        municipio.setId_municipio(id_municipio);

        Estado estado = new Estado();
        estado.setId_estado(id_estado);

        Empleador empleador = new Empleador();
        empleador.setId_empleador(id_empleador);

        TipoHorario tipoHorario = new TipoHorario();
        tipoHorario.setId_tipoHorario(id_tipoHorario);

        TipoContratacion tipoContratacion = new TipoContratacion();
        tipoContratacion.setId_tipoContratacion(id_tipoContratacion);

        ModalidadTrabajo modalidadTrabajo = new ModalidadTrabajo();
        modalidadTrabajo.setId_modalidad(id_modalidadTrabajo);


        Vacante vacante = new Vacante();
        vacante.setEmpresa(empresa);
        vacante.setNombreVacante(nombreVacante);
        vacante.setEspecialista(especialista);
        vacante.setSueldo(sueldo);
        vacante.setHorario(horario);
        vacante.setMunicipio(municipio);
        vacante.setEstado(estado);
        vacante.setEmpleador(empleador);
        vacante.setDescripcion(descripcion);
        vacante.setEmpleador(empleador);
        vacante.setTipoHorario(tipoHorario);
        vacante.setTipoContratacion(tipoContratacion);
        vacante.setModalidadTrabajo(modalidadTrabajo);
        vacante.setDomicilio(domicilio);
        vacante.setFechaPublicacion(publicarAhora == true ? LocalDate.now() : fechaPublicacion);
        vacante.setEstatus(publicarAhora == true ? true : false); //Se le agrega un estatus en true lo que indica que la vacante esta activa


        vacante = vacanteRepository.save(vacante);

        return vacante.getId_vacante();
    }

    //Metodo para obtener lista de todas las vacantes
    public List<Vacante> obtenerListaVacantesActivas(){
        List<Vacante> listaVacantesActivas = new ArrayList<>();
        listaVacantesActivas = vacanteRepository.findAllByEstatus(true);
        for(Vacante vacante:listaVacantesActivas){
            vacante.setCandidatos(null);
            vacante.getEmpresa().setVacantes_empresa(null);
            vacante.getMunicipio().getEstado().setMunicipios(null);
            vacante.getMunicipio().setVacantes_municipios(null);
            vacante.getEmpleador().setVacantes(null);
            vacante.getTipoHorario().setTipoHorario_vacantes(null);
            vacante.getModalidadTrabajo().setModalidadTrabajo_vacante(null);
            vacante.getTipoContratacion().setTipoContratacion_vacantes(null);
            vacante.getMunicipio().getEstado().setVacantes_estado(null);

        }
        return listaVacantesActivas;
    }
    
    
  //PAGINADOS ----------------

    //Metodo obtener todas las vacantes paginadas
    @Transactional(readOnly = true)
    public Page<Vacante> findAllPage(Pageable pageable){
        Page<Vacante> listaVacantes = vacanteRepository.findAll(pageable);
    for(Vacante vacante:listaVacantes){
        vacante.setCandidatos(null);
        vacante.getEmpresa().setVacantes_empresa(null);
        vacante.getMunicipio().getEstado().setMunicipios(null);
        vacante.getMunicipio().setVacantes_municipios(null);
        vacante.getEmpleador().setVacantes(null);
        vacante.getTipoHorario().setTipoHorario_vacantes(null);
        vacante.getModalidadTrabajo().setModalidadTrabajo_vacante(null);
        vacante.getTipoContratacion().setTipoContratacion_vacantes(null);
        vacante.getMunicipio().getEstado().setVacantes_estado(null);
    }
        return listaVacantes;
    }
    
    //OBTENER VACANTES PAGINADAS CON FILTRO DE ESTATUS
    @Transactional(readOnly = true)
    public Page<Vacante> findAllStatus(Pageable pageable){
    	 Page <Vacante> vac = vacanteRepository.findAllByEstatus(true, pageable); 
    for(Vacante vacante:vac){
        vacante.setCandidatos(null);
        vacante.getEmpresa().setVacantes_empresa(null);
        vacante.getMunicipio().getEstado().setMunicipios(null);
        vacante.getMunicipio().setVacantes_municipios(null);
        vacante.getEmpleador().setVacantes(null);
        vacante.getTipoHorario().setTipoHorario_vacantes(null);
        vacante.getModalidadTrabajo().setModalidadTrabajo_vacante(null);
        vacante.getTipoContratacion().setTipoContratacion_vacantes(null);
        vacante.getMunicipio().getEstado().setVacantes_estado(null);
    }
        return vac;
    }
    
    
    // OBTENER VACANTES PAGINADAS POR FILTRO ESTADO Y ESTATUS
    @Transactional(readOnly = true)
    public Page<Vacante> findAllEstado(Pageable pageable, Integer id_estado ){
    	Estado estadoEncontrado = estadoRepository.findById(id_estado).orElse(null);
    	Page <Vacante> vac = vacanteRepository.findAllByEstadoAndEstatus(estadoEncontrado,true,pageable); 
    	 
    for(Vacante vacante:vac){
        vacante.setCandidatos(null);
        vacante.getEmpresa().setVacantes_empresa(null);
        vacante.getMunicipio().getEstado().setMunicipios(null);
        vacante.getMunicipio().setVacantes_municipios(null);
        vacante.getEmpleador().setVacantes(null);
        vacante.getTipoHorario().setTipoHorario_vacantes(null);
        vacante.getModalidadTrabajo().setModalidadTrabajo_vacante(null);
        vacante.getTipoContratacion().setTipoContratacion_vacantes(null);
        vacante.getMunicipio().getEstado().setVacantes_estado(null);
    }
        return vac;
    }
    
    
    // OBTENER VACANTES PAGINADAS POR FILTRO MUNICIPIO Y ESTATUS
    @Transactional(readOnly = true)
    public Page<Vacante> findAllMunicipio(Pageable pageable, Integer id_municipio ){
    	Municipio municipioEncontrado = municipioRepository.findById(id_municipio).orElse(null);
    	Page <Vacante> vac = vacanteRepository.findAllByMunicipioAndEstatus(municipioEncontrado,true,pageable); 
    	 
    for(Vacante vacante:vac){
        vacante.setCandidatos(null);
        vacante.getEmpresa().setVacantes_empresa(null);
        vacante.getMunicipio().getEstado().setMunicipios(null);
        vacante.getMunicipio().setVacantes_municipios(null);
        vacante.getEmpleador().setVacantes(null);
        vacante.getTipoHorario().setTipoHorario_vacantes(null);
        vacante.getModalidadTrabajo().setModalidadTrabajo_vacante(null);
        vacante.getTipoContratacion().setTipoContratacion_vacantes(null);
        vacante.getMunicipio().getEstado().setVacantes_estado(null);
    }
        return vac;
    }
  
    
    @Transactional(readOnly = true)
    public Page<Vacante> findAllNombreEstatus(String nombre, Pageable pageable ){
    	Page <Vacante> vac = vacanteRepository.findAllByEstatusAndNombreVacante(true, nombre, pageable); 
    	 
    for(Vacante vacante:vac){
        vacante.setCandidatos(null);
        vacante.getEmpresa().setVacantes_empresa(null);
        vacante.getMunicipio().getEstado().setMunicipios(null);
        vacante.getMunicipio().setVacantes_municipios(null);
        vacante.getEmpleador().setVacantes(null);
        vacante.getTipoHorario().setTipoHorario_vacantes(null);
        vacante.getModalidadTrabajo().setModalidadTrabajo_vacante(null);
        vacante.getTipoContratacion().setTipoContratacion_vacantes(null);
        vacante.getMunicipio().getEstado().setVacantes_estado(null);
    }
        return vac;
    }
    
    
    @Transactional(readOnly = true)
    public Page<Vacante> findAllNombreEstatusEstado(String nombre,Integer id_estado, Pageable pageable ){
    	Estado estadoEncontrado = estadoRepository.findById(id_estado).orElse(null);
    	Page <Vacante> vac = vacanteRepository.findAllByEstadoAndEstatusAndNombreVacante(estadoEncontrado,true, nombre, pageable); 
    	 
    for(Vacante vacante:vac){
        vacante.setCandidatos(null);
        vacante.getEmpresa().setVacantes_empresa(null);
        vacante.getMunicipio().getEstado().setMunicipios(null);
        vacante.getMunicipio().setVacantes_municipios(null);
        vacante.getEmpleador().setVacantes(null);
        vacante.getTipoHorario().setTipoHorario_vacantes(null);
        vacante.getModalidadTrabajo().setModalidadTrabajo_vacante(null);
        vacante.getTipoContratacion().setTipoContratacion_vacantes(null);
        vacante.getMunicipio().getEstado().setVacantes_estado(null);
    }
        return vac;
    }
    
    @Transactional(readOnly = true)
    public Page<Vacante> findAllNombreEstatusMunicipio(String nombre,Integer id_municipio, Pageable pageable ){
    	Municipio municipioEncontrado = municipioRepository.findById(id_municipio).orElse(null);
    	Page <Vacante> vac = vacanteRepository.findAllByMunicipioAndEstatusAndNombreVacante(municipioEncontrado,true, nombre, pageable); 
    	 
    for(Vacante vacante:vac){
        vacante.setCandidatos(null);
        vacante.getEmpresa().setVacantes_empresa(null);
        vacante.getMunicipio().getEstado().setMunicipios(null);
        vacante.getMunicipio().setVacantes_municipios(null);
        vacante.getEmpleador().setVacantes(null);
        vacante.getTipoHorario().setTipoHorario_vacantes(null);
        vacante.getModalidadTrabajo().setModalidadTrabajo_vacante(null);
        vacante.getTipoContratacion().setTipoContratacion_vacantes(null);
        vacante.getMunicipio().getEstado().setVacantes_estado(null);
    }
        return vac;
    }
    
  //PAGINADOS ----------------
    
    /*public List<Vacante> obtenerListaVacantesActivas(){
        List<Vacante> listaVacantesActivas = new ArrayList<>();
        listaVacantesActivas = vacanteRepository.findAllByEstatus(true);
        for(Vacante vacante:listaVacantesActivas){
            vacante.setCandidatos(null);
            vacante.getEmpresa().setVacantes_empresa(null);
            vacante.getMunicipio().getEstado().setMunicipios(null);
            vacante.getMunicipio().setVacantes_municipios(null);
            vacante.getEmpleador().setVacantes(null);
            vacante.getTipoHorario().setTipoHorario_vacantes(null);
            vacante.getModalidadTrabajo().setModalidadTrabajo_vacante(null);
            vacante.getTipoContratacion().setTipoContratacion_vacantes(null);
            vacante.getMunicipio().getEstado().setVacantes_estado(null);

        }
    return listaVacantesActivas;
    }
    
    //Buscar vacantes cerca del candidato
    public List<Vacante> buscarVacantesCerca(Integer id_municipio){
        //Se busca un municipio para ver las vacantes que hay publicadas en este municipio
        Municipio municipioEncontrado = municipioRepository.findById(id_municipio).orElse(null);
        List<Vacante> listaVacantesCerca = new ArrayList<>();
        listaVacantesCerca = vacanteRepository.findAllByMunicipio(municipioEncontrado);
        //Se setean las vacantes asociadas a un municipio a la lista que creamos
        //listaVacantesCerca = municipioEncontrado.getVacantes_municipios();
        municipioEncontrado.getEstado().setMunicipios(null);
        for(Vacante vacante:listaVacantesCerca){
            vacante.getMunicipio().getEstado().setVacantes_estado(null);
            vacante.getMunicipio().setVacantes_municipios(null);
            vacante.getEmpleador().setVacantes(null);
            vacante.setCandidatos(null);
            vacante.getTipoHorario().setTipoHorario_vacantes(null);
            vacante.getTipoContratacion().setTipoContratacion_vacantes(null);
            vacante.getModalidadTrabajo().setModalidadTrabajo_vacante(null);
            vacante.getEmpresa().setVacantes_empresa(null);
            vacante.getEstado().setVacantes_estado(null);
        }
        return listaVacantesCerca;
    }
    
    */

    //Buscar vacantes por sueldo
    public List<Vacante> buscarVacantesPorSueldo(){
        List<Vacante> listaVacantesPorSueldo = new ArrayList<>();
        listaVacantesPorSueldo = vacanteRepository.findAllByEstatus(true);
        listaVacantesPorSueldo = listaVacantesPorSueldo.stream().sorted(Comparator.comparingInt(Vacante::getSueldo).reversed()).collect(Collectors.toList());
        for(Vacante vacante:listaVacantesPorSueldo){
            vacante.setCandidatos(null);
            vacante.getEmpresa().setVacantes_empresa(null);
            vacante.getMunicipio().getEstado().setMunicipios(null);
            vacante.getMunicipio().setVacantes_municipios(null);
            vacante.getEmpleador().setVacantes(null);
            vacante.getTipoHorario().setTipoHorario_vacantes(null);
            vacante.getModalidadTrabajo().setModalidadTrabajo_vacante(null);
            vacante.getTipoContratacion().setTipoContratacion_vacantes(null);
            vacante.getEstado().setVacantes_estado(null);
        }

        return listaVacantesPorSueldo;
    }

    
    /*
    //Buscar vacante por palabra clave
    public List<Vacante> buscarPorPalabraClave(String palabraClave){
        List<Vacante> listaVacantes = new ArrayList<>();
        List<Vacante> listaVacantesEncontradasPorPalabraClave = new ArrayList<>();
        listaVacantes = vacanteRepository.findAllByEstatus(true);
        for(Vacante vacante:listaVacantes){
            vacante.setCandidatos(null);
            vacante.getEmpresa().setVacantes_empresa(null);
            vacante.getMunicipio().getEstado().setMunicipios(null);
            vacante.getMunicipio().setVacantes_municipios(null);
            vacante.getEmpleador().setVacantes(null);
            vacante.getTipoHorario().setTipoHorario_vacantes(null);
            vacante.getModalidadTrabajo().setModalidadTrabajo_vacante(null);
            vacante.getTipoContratacion().setTipoContratacion_vacantes(null);
            vacante.getEstado().setVacantes_estado(null);

            if(vacante.getNombreVacante().toLowerCase().contains(palabraClave.toLowerCase())){
                listaVacantesEncontradasPorPalabraClave.add(vacante);
            }
        }
        return listaVacantesEncontradasPorPalabraClave;
    }
*/

    //Eliminar vacante
    @Transactional
    public String eliminarVacante(Integer id){

        Vacante vacanteEncontrada = vacanteRepository.findById(id).orElse(null);

       List<Postulacion> listaPostulaciones = postulacionRepository.findAllByVacante(vacanteEncontrada);
       for(Postulacion postulacion:listaPostulaciones){
           postulacionRepository.deleteById(postulacion.getId_postulacion());
       }

         vacanteRepository.deleteVacanteById(id);

    return "Vacante eliminada exitosamente";
    }
    //Obtener vacante por id
    public Vacante obtenerVacantePorId(Integer id){
       Vacante vacanteEncontrada = this.vacanteRepository.findById(id).orElse(null);


       vacanteEncontrada.getMunicipio().getEstado().setMunicipios(null);
       vacanteEncontrada.getEmpresa().setVacantes_empresa(null);
       vacanteEncontrada.getMunicipio().setVacantes_municipios(null);
       vacanteEncontrada.getTipoHorario().setTipoHorario_vacantes(null);
       vacanteEncontrada.getTipoContratacion().setTipoContratacion_vacantes(null);
       vacanteEncontrada.getModalidadTrabajo().setModalidadTrabajo_vacante(null);
       vacanteEncontrada.getEmpleador().setVacantes(null);
       vacanteEncontrada.getEstado().setVacantes_estado(null);

       for(Candidato candidato:vacanteEncontrada.getCandidatos()){
        candidato.setPostulaciones(null);
        candidato.getEstado().setMunicipios(null);
        candidato.getMunicipio().setVacantes_municipios(null);
        candidato.getEstado().setVacantes_estado(null);
        candidato.setIdiomas(null);
        candidato.setHabilidades(null);

       }
       List<Candidato> listaCandidatos = vacanteEncontrada.getCandidatos();

        return vacanteEncontrada;

    }

    //Obtener vacante por id
    public List<Candidato> obtenerCandidatosDeVacante(Integer id){
        Vacante vacanteEncontrada = this.vacanteRepository.findById(id).orElse(null);


        vacanteEncontrada.getMunicipio().getEstado().setMunicipios(null);
        vacanteEncontrada.getEmpresa().setVacantes_empresa(null);
        vacanteEncontrada.getMunicipio().setVacantes_municipios(null);
        vacanteEncontrada.getTipoHorario().setTipoHorario_vacantes(null);
        vacanteEncontrada.getTipoContratacion().setTipoContratacion_vacantes(null);
        vacanteEncontrada.getModalidadTrabajo().setModalidadTrabajo_vacante(null);
        vacanteEncontrada.getEmpleador().setVacantes(null);


        for(Candidato candidato:vacanteEncontrada.getCandidatos()){
            candidato.setPostulaciones(null);
            candidato.getEstado().setMunicipios(null);
            candidato.getMunicipio().setVacantes_municipios(null);
            candidato.getEstado().setVacantes_estado(null);
                for( Habilidad habilidad:candidato.getHabilidades()){
                    habilidad.setCandidatos(null);
            }
                for( Idioma idioma:candidato.getIdiomas()){
                idioma.setCandidatos(null);
            }
            }



        List<Candidato> listaCandidatos = vacanteEncontrada.getCandidatos();
        return listaCandidatos;

    }



    //Modififcar una vacante
public Vacante modificarVacante(String nombreVacante, String especialista, Integer sueldo, String horario, Integer id_municipio, Boolean estatus, Integer id_empresa, Integer id_empleador, Integer id_tipoHorario, Integer id_tipoContratacion, Integer id_modalidadTrabajo,String descripcion,Integer id_vacante,String domicilio){
Vacante vacante = obtenerVacantePorId(id_vacante);
if(id_municipio != null && id_municipio > 0) {
    Municipio municipio = new Municipio();
    municipio.setId_municipio(id_municipio);
    vacante.setMunicipio(municipio);

}if(id_empresa != null && id_empresa > 0) {
    Empresa empresa = new Empresa();
    empresa.setId_empresa(id_empresa);
    vacante.setEmpresa(empresa);

}if(id_empleador  != null && id_empleador  > 0) {
    Empleador empleador = new Empleador();
    empleador.setId_empleador(id_empleador);
    vacante.setEmpleador(empleador);

}if(id_tipoHorario  != null && id_tipoHorario  > 0) {
    TipoHorario tipoHorario = new TipoHorario();
    tipoHorario.setId_tipoHorario(id_tipoHorario);
    vacante.setTipoHorario(tipoHorario);
}
if(id_tipoContratacion  != null && id_tipoContratacion  > 0) {
        TipoContratacion tipoContratacion = new TipoContratacion();
        tipoContratacion.setId_tipoContratacion(id_tipoContratacion);
        vacante.setTipoContratacion(tipoContratacion);
    }
if(id_modalidadTrabajo  != null && id_modalidadTrabajo  > 0) {
        ModalidadTrabajo modalidadTrabajo = new ModalidadTrabajo();
        modalidadTrabajo.setId_modalidad(id_modalidadTrabajo);
        vacante.setModalidadTrabajo(modalidadTrabajo);
    }
if(nombreVacante != null && nombreVacante !="") {
    vacante.setNombreVacante(nombreVacante);
}
if(especialista != null && especialista != "") {
    vacante.setEspecialista(especialista);
}
if(sueldo != null && sueldo >0) {
    vacante.setSueldo(sueldo);
}
if(horario != null && horario != "") {
    vacante.setHorario(horario);
}
if(estatus != null) {
    vacante.setEstatus(estatus);
}
if(descripcion != null && descripcion != "") {
    vacante.setDescripcion(descripcion);
}
if(domicilio != null && domicilio != ""){
    vacante.setDomicilio(domicilio);
}
vacante = vacanteRepository.save(vacante);
return vacante;
}
//Buscar vacantes cerca y por palabra clave
public List<Vacante> buscarVacantesCercaYPorPalabraClave(Integer id_municipio, String palabraClave){

    //Se busca un municipio para ver las vacantes que hay publicadas en este municipio
    Municipio municipioEncontrado = municipioRepository.findById(id_municipio).orElse(null);
    List<Vacante> listaVacantesCerca = new ArrayList<>();
    List<Vacante> listaVacantesCercaYPalabraClave = new ArrayList<>();
    listaVacantesCerca = vacanteRepository.findAllByMunicipio(municipioEncontrado);

    //Se setean las vacantes asociadas a un municipio a la lista que creamos
    municipioEncontrado.getEstado().setMunicipios(null);
    for(Vacante vacante:listaVacantesCerca){
        vacante.getMunicipio().setEstado(null);
        vacante.getMunicipio().setVacantes_municipios(null);
        vacante.getEmpleador().setVacantes(null);
        vacante.setCandidatos(null);
        vacante.getTipoHorario().setTipoHorario_vacantes(null);
        vacante.getTipoContratacion().setTipoContratacion_vacantes(null);
        vacante.getModalidadTrabajo().setModalidadTrabajo_vacante(null);
        vacante.getEmpresa().setVacantes_empresa(null);
        vacante.getEstado().setVacantes_estado(null);

        if(vacante.getNombreVacante().toLowerCase().contains(palabraClave.toLowerCase())){
            listaVacantesCercaYPalabraClave.add(vacante);
        }
    }

    return listaVacantesCercaYPalabraClave;
}

    //Buscar vacantes por estado
    public List<Vacante> buscarVacantesEstado(Integer id_estado){
        //Se busca un municipio para ver las vacantes que hay publicadas en este municipio
        Estado estadoEncontrado = estadoRepository.findById(id_estado).orElse(null);
        List<Vacante> listaVacantesEstado = new ArrayList<>();
        listaVacantesEstado = vacanteRepository.findAllByEstado(estadoEncontrado);
        //Se setean las vacantes asociadas a un estado a la lista que creamos
        //listaVacantesCerca = municipioEncontrado.getVacantes_municipios();
        estadoEncontrado.setMunicipios(null);
        for(Vacante vacante:listaVacantesEstado){
            vacante.getMunicipio().setEstado(null);
            vacante.getMunicipio().setVacantes_municipios(null);
            vacante.getEmpleador().setVacantes(null);
            vacante.setCandidatos(null);
            vacante.getTipoHorario().setTipoHorario_vacantes(null);
            vacante.getTipoContratacion().setTipoContratacion_vacantes(null);
            vacante.getModalidadTrabajo().setModalidadTrabajo_vacante(null);
            vacante.getEmpresa().setVacantes_empresa(null);
            vacante.getEstado().setVacantes_estado(null);
        }

        return listaVacantesEstado;
    }

    //Buscar vacantes cerca y por palabra clave
    public List<Vacante> buscarVacantesEstadoYPorPalabraClave(Integer id_estado, String palabraClave){

        //Se busca un municipio para ver las vacantes que hay publicadas en este municipio
        Estado estadoEncontrado = estadoRepository.findById(id_estado).orElse(null);
        List<Vacante> listaVacantesEstado = new ArrayList<>();
        List<Vacante> listaVacantesEstadoYPalabraClave = new ArrayList<>();
        listaVacantesEstado = vacanteRepository.findAllByEstado(estadoEncontrado);

        //Se setean las vacantes asociadas a un municipio a la lista que creamos
        estadoEncontrado.setMunicipios(null);
        for(Vacante vacante:listaVacantesEstado){
            vacante.getMunicipio().setEstado(null);
            vacante.getMunicipio().setVacantes_municipios(null);
            vacante.getEmpleador().setVacantes(null);
            vacante.setCandidatos(null);
            vacante.getTipoHorario().setTipoHorario_vacantes(null);
            vacante.getTipoContratacion().setTipoContratacion_vacantes(null);
            vacante.getModalidadTrabajo().setModalidadTrabajo_vacante(null);
            vacante.getEmpresa().setVacantes_empresa(null);
            vacante.getEstado().setVacantes_estado(null);

            if(vacante.getNombreVacante().toLowerCase().contains(palabraClave.toLowerCase())){
                listaVacantesEstadoYPalabraClave.add(vacante);
            }
        }
        return listaVacantesEstadoYPalabraClave;
    }

    //Eliminar vacante automaticamente despues de 30 dias de inactividad
    @Transactional
    @Scheduled(cron ="0 0 20 * * ?")
    public void eliminarVacantePorDias(){
        System.out.println("haciendo evaluación de vacantes "+ LocalDateTime.now());
        List<Vacante> listaVacantes = vacanteRepository.findAllByEstatus(true);



        //Se itera la lista de vacantes y se evalua que tenga alguna postulación llegando a los 30 dias de creacion, de lo contrario será eliminada
        for (Vacante vacante : listaVacantes){
            vacante.setFechaPublicacion(vacante.getFechaPublicacion());

            if (vacante.getDiasPublicada() == 0 || vacante.getDiasPublicada() > 0 && vacante.getCandidatos().isEmpty()){

                ProcesosAutomaticos proceso = new ProcesosAutomaticos();

                proceso.setTipoProceso("eliminacion");
                proceso.setDescripcion("Se hizo la eliminación de esta vacante mediante el proceso de eliminación automatica por que no ha recibido postulaciones en 30 dias");
                proceso.setFechaProceso(LocalDate.now());
                proceso.setNombreVacante(vacante.getNombreVacante());
                procesosAutomaticosRepository.save(proceso);

                vacanteRepository.deleteVacanteById(vacante.getId_vacante());
            }
        }

    }

    //Publicar vacantes programadas
    @Scheduled(cron = "0 0 20 * * ?")
    public void publicarVacanteProgramada(){
        List<Vacante> listaVacantesInactivas = new ArrayList<>();
        listaVacantesInactivas = vacanteRepository.findAllByEstatus(false);
        for(Vacante vacante : listaVacantesInactivas){
            if(vacante.getFechaPublicacion().isEqual(LocalDate.now())){

                ProcesosAutomaticos proceso = new ProcesosAutomaticos();
                proceso.setTipoProceso("publicacion");
                proceso.setDescripcion("Se hizo la publicación de esta vacante mediante el proceso automatico de publicación programada ya que se cumplió la fecha de publicación que indicó el usuario");
                proceso.setFechaProceso(LocalDate.now());
                proceso.setNombreVacante(vacante.getNombreVacante());
                procesosAutomaticosRepository.save(proceso);

                vacante.setEstatus(true);
                vacanteRepository.save(vacante);
            }
        }

    }

    //Metodo para obtener lista de todas las vacantes
    public List<Vacante> obtenerListaVacantesFiltro(String tipoFiltro){
        List<Vacante> listaVacantes = new ArrayList<>();
        if (tipoFiltro.equalsIgnoreCase("activas")){

            listaVacantes = vacanteRepository.findAllByEstatus(true);
            for(Vacante vacante:listaVacantes){
                vacante.setCandidatos(null);
                vacante.getEmpresa().setVacantes_empresa(null);
                vacante.getMunicipio().getEstado().setMunicipios(null);
                vacante.getMunicipio().setVacantes_municipios(null);
                vacante.getEmpleador().setVacantes(null);
                vacante.getTipoHorario().setTipoHorario_vacantes(null);
                vacante.getModalidadTrabajo().setModalidadTrabajo_vacante(null);
                vacante.getTipoContratacion().setTipoContratacion_vacantes(null);
                vacante.getMunicipio().getEstado().setVacantes_estado(null);

            }

        } else if (tipoFiltro.equalsIgnoreCase("inactivas")) {
            listaVacantes = vacanteRepository.findAllByEstatus(false);
            for(Vacante vacante:listaVacantes){
                vacante.setCandidatos(null);
                vacante.getEmpresa().setVacantes_empresa(null);
                vacante.getMunicipio().getEstado().setMunicipios(null);
                vacante.getMunicipio().setVacantes_municipios(null);
                vacante.getEmpleador().setVacantes(null);
                vacante.getTipoHorario().setTipoHorario_vacantes(null);
                vacante.getModalidadTrabajo().setModalidadTrabajo_vacante(null);
                vacante.getTipoContratacion().setTipoContratacion_vacantes(null);
                vacante.getMunicipio().getEstado().setVacantes_estado(null);

            }
        }
        return listaVacantes;
    }


}
