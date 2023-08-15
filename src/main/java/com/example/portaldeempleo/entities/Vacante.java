package com.example.portaldeempleo.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="vacante")
public class Vacante {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_vacante")
    private Integer id_vacante;



    @Column(name="nombrevacante")
    private String nombreVacante;

    @Column(name="especialistarequerido")
    private String especialista;

    @Column(name="sueldo")
    private Integer sueldo;

    @Column(name="horario")
    private String horario;
    @Column(name="domicilio")
    private String domicilio;

    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = true
    )
    @JoinColumn(name="id_municipio", updatable = false)
    private Municipio municipio;

    @Column(name="estatus")
    private Boolean estatus;
    @Column(name="descripcion")
    private String descripcion;

    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = false
    )
    @JoinColumn(name="id_empresa", updatable = false)
    private Empresa empresa;

    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = true
    )
    @JoinColumn(name="id_empleador", updatable = false)
   private Empleador empleador;

    @ManyToMany
    @JoinTable(name="postulaciones",
    joinColumns = @JoinColumn(name="id_vacante", updatable = false),
    inverseJoinColumns = @JoinColumn(name="id_candidato", updatable = false))
    private List<Candidato> candidatos = new ArrayList<>();
    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = true
    )
    @JoinColumn(name="id_tipohorario")
    private TipoHorario tipoHorario;

    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = true
    )
    @JoinColumn(name="id_tipocontratacion")
    private TipoContratacion tipoContratacion;

    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = true
    )
    @JoinColumn(name="id_modalidadtrabajo")
    private ModalidadTrabajo modalidadTrabajo;

    @Transient
    private Integer id_postulacion;
    @Transient
    private Boolean booleanPostulacion;


    public Boolean getBooleanPostulacion() {
        return booleanPostulacion;
    }

    public void setBooleanPostulacion(Boolean booleanPostulacion) {
        this.booleanPostulacion = booleanPostulacion;
    }

    public Integer getId_vacante() {
        return id_vacante;
    }

    public void setId_vacante(Integer id_vacante) {
        this.id_vacante = id_vacante;
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

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }

    public List<Candidato> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(List<Candidato> candidatos) {
        this.candidatos = candidatos;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
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

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getId_postulacion() {
        return id_postulacion;
    }

    public void setId_postulacion(Integer id_postulacion) {
        this.id_postulacion = id_postulacion;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
}
