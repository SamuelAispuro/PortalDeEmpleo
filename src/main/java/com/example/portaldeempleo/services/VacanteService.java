package com.example.portaldeempleo.services;

import com.example.portaldeempleo.DTO.RespuestaDTO;
import com.example.portaldeempleo.entities.*;
import com.example.portaldeempleo.repositories.VacanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VacanteService {
    @Autowired
    VacanteRepository vacanteRepository;


    //Metodo para crear una vacante
    public Integer crearVacante(String nombreVacante, String especialista, Integer sueldo, Integer id_empresa){
        Empresa empresa = new Empresa();
        empresa.setId_empresa(id_empresa);

        Vacante vacante = new Vacante();
        vacante.setEmpresa(empresa);
        vacante.setNombreVacante(nombreVacante);
        vacante.setEspecialista(especialista);
        vacante.setSueldo(sueldo);

        vacante = vacanteRepository.save(vacante);

        return vacante.getId_vacante();
    }

    //Metodo obtener todas las vacantes
    public List<Vacante> obtenerListaVacantes(){
        List<Vacante> listaVacantes = new ArrayList<>();
        listaVacantes = vacanteRepository.findAll();
        for(Vacante vacante:listaVacantes){
            vacante.setCandidatos(null);
            vacante.getEmpresa().setVacantes(null);

        }
    return listaVacantes;
    }
    //Eliminar vacante
    public String eliminarVacante(Integer id){
    this.vacanteRepository.deleteById(id);
    return "Vacante eliminada exitosamente";
    }
    //Obtener vacante por id
    public Vacante obtenerVacantePorId(Integer id){
       Vacante vacanteEncontrada = this.vacanteRepository.findById(id).orElse(null);
       vacanteEncontrada.setCandidatos(null);
       vacanteEncontrada.getEmpresa().setVacantes(null);
        return vacanteEncontrada;

    }

    //Modififcar una vacante
public Vacante modificarVacante(String nombreVacante, String especialista, Integer sueldo, String horario, Integer id_municipio, Boolean estatus, Integer id_empresa, Integer id_empleador, Integer id_tipoHorario, Integer id_tipoContratacion, Integer id_modalidadTrabajo,String descripcion,Integer id_vacante){
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
        tipoContratacion.setId_contratacion(id_tipoContratacion);
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
vacante = vacanteRepository.save(vacante);
return vacante;
}



}
