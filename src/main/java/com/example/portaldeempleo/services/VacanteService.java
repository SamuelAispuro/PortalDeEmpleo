package com.example.portaldeempleo.services;

import com.example.portaldeempleo.DTO.RespuestaDTO;
import com.example.portaldeempleo.entities.*;
import com.example.portaldeempleo.repositories.CandidatoRepository;
import com.example.portaldeempleo.repositories.MunicipioRepository;
import com.example.portaldeempleo.repositories.PostulacionRepository;
import com.example.portaldeempleo.repositories.VacanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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


    //Metodo para crear una vacante
    public Integer crearVacante(String nombreVacante, String especialista, Integer sueldo, Integer id_empresa,String horario,Integer id_municipio,String descripcion,Integer id_empleador,Integer id_tipoHorario,Integer id_tipoContratacion,Integer id_modalidadTrabajo, String domicilio){
        Empresa empresa = new Empresa();
        empresa.setId_empresa(id_empresa);

        Municipio municipio = new Municipio();
        municipio.setId_municipio(id_municipio);

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
        vacante.setEmpleador(empleador);
        vacante.setDescripcion(descripcion);
        vacante.setEmpleador(empleador);
        vacante.setTipoHorario(tipoHorario);
        vacante.setTipoContratacion(tipoContratacion);
        vacante.setModalidadTrabajo(modalidadTrabajo);
        vacante.setDomicilio(domicilio);
        vacante.setEstatus(true); //Se le agrega un estatus en true lo que indica que la vacante esta activa


        vacante = vacanteRepository.save(vacante);

        return vacante.getId_vacante();
    }

    //Metodo obtener todas las vacantes
    public List<Vacante> obtenerListaVacantes(){
        List<Vacante> listaVacantes = new ArrayList<>();
        listaVacantes = vacanteRepository.findAll();
        for(Vacante vacante:listaVacantes){
            vacante.setCandidatos(null);
            vacante.getEmpresa().setVacantes_empresa(null);
            vacante.getMunicipio().getEstado().setMunicipios(null);
            vacante.getMunicipio().setVacantes_municipios(null);
            vacante.getEmpleador().setVacantes(null);
            vacante.getTipoHorario().setTipoHorario_vacantes(null);
            vacante.getModalidadTrabajo().setModalidadTrabajo_vacante(null);
            vacante.getTipoContratacion().setTipoContratacion_vacantes(null);

        }
    return listaVacantes;
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
            vacante.getMunicipio().setEstado(null);
            vacante.getMunicipio().setVacantes_municipios(null);
            vacante.getEmpleador().setVacantes(null);
            vacante.setCandidatos(null);
            vacante.getTipoHorario().setTipoHorario_vacantes(null);
            vacante.getTipoContratacion().setTipoContratacion_vacantes(null);
            vacante.getModalidadTrabajo().setModalidadTrabajo_vacante(null);
            vacante.getEmpresa().setVacantes_empresa(null);
        }
        return listaVacantesCerca;
    }

    //Buscar vacantes por sueldo
    public List<Vacante> buscarVacantesPorSueldo(){
        List<Vacante> listaVacantesPorSueldo = new ArrayList<>();
        listaVacantesPorSueldo = vacanteRepository.findAll();
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
        }

        return listaVacantesPorSueldo;
    }

    //Buscar vacante por palabra clave
    public List<Vacante> buscarPorPalabraClave(String palabraClave){
        List<Vacante> listaVacantes = new ArrayList<>();
        List<Vacante> listaVacantesEncontradasPorPalabraClave = new ArrayList<>();
        listaVacantes = vacanteRepository.findAll();
        for(Vacante vacante:listaVacantes){
            vacante.setCandidatos(null);
            vacante.getEmpresa().setVacantes_empresa(null);
            vacante.getMunicipio().getEstado().setMunicipios(null);
            vacante.getMunicipio().setVacantes_municipios(null);
            vacante.getEmpleador().setVacantes(null);
            vacante.getTipoHorario().setTipoHorario_vacantes(null);
            vacante.getModalidadTrabajo().setModalidadTrabajo_vacante(null);
            vacante.getTipoContratacion().setTipoContratacion_vacantes(null);

            if(vacante.getNombreVacante().toLowerCase().contains(palabraClave.toLowerCase())){
                listaVacantesEncontradasPorPalabraClave.add(vacante);
            }
        }
        return listaVacantesEncontradasPorPalabraClave;
    }


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

       for(Candidato candidato:vacanteEncontrada.getCandidatos()){
        candidato.setPostulaciones(null);
        candidato.getEstado().setMunicipios(null);
        candidato.getMunicipio().setVacantes_municipios(null);

       }

        return vacanteEncontrada;

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

        if(vacante.getNombreVacante().toLowerCase().contains(palabraClave.toLowerCase())){
            listaVacantesCercaYPalabraClave.add(vacante);
        }
    }

    return listaVacantesCercaYPalabraClave;
}




}
