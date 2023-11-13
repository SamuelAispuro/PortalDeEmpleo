package com.example.portaldeempleo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Cascade;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="vacante")
public class Vacante {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    @Column(name="id_vacante")
    private Integer id_vacante;


    @NonNull
    @Column(name="nombrevacante")
    private String nombreVacante;

    @NonNull
    @Column(name="especialistarequerido")
    private String especialista;

    @NonNull
    @Column(name="sueldo")
    private Integer sueldo;

    @NonNull
    @Column(name="horario")
    private String horario;

    @NonNull
    @Column(name="domicilio")
    private String domicilio;

    @NonNull
    @Column(name="fechapublicacion")
    private LocalDate fechaPublicacion;

    @Getter
    @Transient
    private Integer diasPublicada;

    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = true
    )

    @NonNull
    @JoinColumn(name="id_municipio")
    private Municipio municipio;

    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = true
    )

    @NonNull
    @JoinColumn(name="id_estado")
    private Estado estado;

    @NonNull
    @Column(name="estatus")
    private Boolean estatus;

    @NonNull
    @Column(name="descripcion")
    private String descripcion;

    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = false
    )

    @NonNull
    @JoinColumn(name="id_empresa")
    private Empresa empresa;

    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = true
    )

    @NonNull
    @JoinColumn(name="id_empleador")
   private Empleador empleador;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="postulaciones",
    joinColumns = @JoinColumn(name="id_vacante", updatable = false),
    inverseJoinColumns = @JoinColumn(name="id_candidato", updatable = false))
    private List<Candidato> candidatos = new ArrayList<>();
    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = true
    )

    @NonNull
    @JoinColumn(name="id_tipohorario")
    private TipoHorario tipoHorario;

    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = true
    )

    @NonNull
    @JoinColumn(name="id_tipocontratacion")
    private TipoContratacion tipoContratacion;

    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = true
    )

    @NonNull
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public LocalDate getFechaPublicacion() {

        Period periodoDias = Period.between(fechaPublicacion, LocalDate.now());
        this.diasPublicada = periodoDias.getDays();

        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {

        Period periodoDias = Period.between(fechaPublicacion, LocalDate.now());
        this.diasPublicada = periodoDias.getDays();

        this.fechaPublicacion = fechaPublicacion;
    }

    public void setDiasPublicada(Integer diasPublicada) {
        this.diasPublicada = diasPublicada;
    }

	public Integer getDiasPublicada() {
		return diasPublicada;
	}
    
    
}
