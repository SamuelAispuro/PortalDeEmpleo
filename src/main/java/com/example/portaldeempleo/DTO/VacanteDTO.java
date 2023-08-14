package com.example.portaldeempleo.DTO;

import com.example.portaldeempleo.entities.*;

public class VacanteDTO {

    private Integer id_vacante;
     private String nombreVacante;
     private String especialista;
     private Integer sueldo;
     private Empresa empresa;
     private Integer id_empresa;
     private String horario;
     private Municipio municipio;
     private Integer id_municipio;
     private Boolean estatus;
     private Empleador empleador;
     private Integer id_empleador;
     private TipoHorario tipoHorario;
     private Integer id_tipoHorario;
     private TipoContratacion tipoContratacion;
     private Integer id_tipoContratacion;
     private ModalidadTrabajo modalidadTrabajo;
     private Integer id_modalidadTrabajo;
     private String descripcion;
     private String domicilio;

    public Boolean getEstatus() {
        return estatus;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNombreVacante() {
        return nombreVacante;
    }

    public void setNombreVacante(String nombreVacante) {
        this.nombreVacante = nombreVacante;
    }

    public String getEspecialista() {
        return especialista;
    }

    public void setEspecialista(String especialista) {
        this.especialista = especialista;
    }

    public Integer getSueldo() {
        return sueldo;
    }

    public void setSueldo(Integer sueldo) {
        this.sueldo = sueldo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }

    public Empleador getEmpleador() {
        return empleador;
    }

    public void setEmpleador(Empleador empleador) {
        this.empleador = empleador;
    }

    public TipoHorario getTipoHorario() {
        return tipoHorario;
    }

    public void setTipoHorario(TipoHorario tipoHorario) {
        this.tipoHorario = tipoHorario;
    }

    public TipoContratacion getTipoContratacion() {
        return tipoContratacion;
    }

    public void setTipoContratacion(TipoContratacion tipoContratacion) {
        this.tipoContratacion = tipoContratacion;
    }

    public ModalidadTrabajo getModalidadTrabajo() {
        return modalidadTrabajo;
    }

    public void setModalidadTrabajo(ModalidadTrabajo modalidadTrabajo) {
        this.modalidadTrabajo = modalidadTrabajo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getId_vacante() {
        return id_vacante;
    }

    public void setId_vacante(Integer id_vacante) {
        this.id_vacante = id_vacante;
    }

    public Integer getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(Integer id_empresa) {
        this.id_empresa = id_empresa;
    }

    public Integer getId_municipio() {
        return id_municipio;
    }

    public void setId_municipio(Integer id_municipio) {
        this.id_municipio = id_municipio;
    }

    public Integer getId_empleador() {
        return id_empleador;
    }

    public void setId_empleador(Integer id_empleador) {
        this.id_empleador = id_empleador;
    }

    public Integer getId_tipoHorario() {
        return id_tipoHorario;
    }

    public void setId_tipoHorario(Integer id_tipoHorario) {
        this.id_tipoHorario = id_tipoHorario;
    }

    public Integer getId_tipoContratacion() {
        return id_tipoContratacion;
    }

    public void setId_tipoContratacion(Integer id_tipoContratacion) {
        this.id_tipoContratacion = id_tipoContratacion;
    }

    public Integer getId_modalidadTrabajo() {
        return id_modalidadTrabajo;
    }

    public void setId_modalidadTrabajo(Integer id_modalidadTrabajo) {
        this.id_modalidadTrabajo = id_modalidadTrabajo;
    }
}
