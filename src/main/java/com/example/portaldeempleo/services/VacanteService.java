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
    return listaVacantes;
    }
    //Eliminar vacante
    public String eliminarVacante(Integer id){
    this.vacanteRepository.deleteById(id);
    return "Vacante eliminada exitosamente";
    }
    //Obtener vacante por id
    public Vacante obtenerVacantePorId(Integer id){
        return this.vacanteRepository.findById(id).get();
    }

    //Modififcar una vacante
public Vacante modificarVacante(String nombreVacante, String especialista, Integer sueldo, String horario, Integer id_municipio, Boolean estatus, Integer id_empresa, Integer id_empleador, Integer id_tipoHorario, Integer id_tipoContratacion, Integer id_modalidadTrabajo,String descripcion,Integer id_vacante){
Vacante vacante = obtenerVacantePorId(id_vacante);
    Municipio municipio = new Municipio();
    municipio.setId_municipio(id_municipio);

    Empresa empresa = new Empresa();
    empresa.setId_empresa(id_empresa);

    Empleador empleador = new Empleador();
    empleador.setId_empleador(id_empleador);

    TipoHorario tipoHorario = new TipoHorario();
    tipoHorario.setId_tipoHorario(id_tipoHorario);

    TipoContratacion tipoContratacion = new TipoContratacion();
    tipoContratacion.setId_contratacion(id_tipoContratacion);

    ModalidadTrabajo modalidadTrabajo = new ModalidadTrabajo();
    modalidadTrabajo.setId_modalidad(id_modalidadTrabajo);

vacante.setNombreVacante(nombreVacante);
vacante.setEspecialista(especialista);
vacante.setSueldo(sueldo);
vacante.setHorario(horario);
vacante.setMunicipio(municipio);
vacante.setEstatus(estatus);
vacante.setEmpresa(empresa);
vacante.setEmpleador(empleador);
vacante.setTipoHorario(tipoHorario);
vacante.setTipoContratacion(tipoContratacion);
vacante.setModalidadTrabajo(modalidadTrabajo);
vacante.setDescripcion(descripcion);
vacante = vacanteRepository.save(vacante);
return vacante;
}



}
