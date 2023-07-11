package com.example.portaldeempleo.entities;

import jakarta.persistence.*;

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

    @Column(name="id_tipohorario")
    private Integer id_tipoHorario;

    @Column(name="id_modalidadtrabajo")
    private Integer id_modalidadTrabajo;

    @Column(name="id_tipocontratacion")
    private Integer id_tipoContratacion;

    @Column(name="id_localidad")
    private Integer id_localidad;

    @Column(name="estatus")
    private Boolean estatus;

    @Column(name="id_candidato")
    private Integer id_candidato;

    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = false
    )
    @JoinColumn(name="id_empresa")
    private Empresa empresa;

    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = true
    )
    @JoinColumn(name="id_empleador")
   private Empleador empleador;

    @ManyToMany
    @JoinTable(name="postulaciones",
    joinColumns = @JoinColumn(name="id_vacante"),
    inverseJoinColumns = @JoinColumn(name="id_candidato"))
    private List<Candidato> candidatos = new ArrayList<>();


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

    public Integer getId_tipoHorario() {
        return id_tipoHorario;
    }

    public void setId_tipoHorario(Integer id_tipoHorario) {
        this.id_tipoHorario = id_tipoHorario;
    }

    public Integer getId_modalidadTrabajo() {
        return id_modalidadTrabajo;
    }

    public void setId_modalidadTrabajo(Integer id_modalidadTrabajo) {
        this.id_modalidadTrabajo = id_modalidadTrabajo;
    }

    public Integer getId_tipoContratacion() {
        return id_tipoContratacion;
    }

    public void setId_tipoContratacion(Integer id_tipoContratacion) {
        this.id_tipoContratacion = id_tipoContratacion;
    }

    public Integer getId_localidad() {
        return id_localidad;
    }

    public void setId_localidad(Integer id_localidad) {
        this.id_localidad = id_localidad;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }

    public Integer getId_candidato() {
        return id_candidato;
    }

    public void setId_candidato(Integer id_candidato) {
        this.id_candidato = id_candidato;
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


}
