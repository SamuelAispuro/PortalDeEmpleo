package com.example.portaldeempleo.entities;
import jakarta.persistence.*;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="candidato")
public class Candidato {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_candidato")
    private Integer id_candidato;

    @Column(name="domicilio")
    private String domicilio;
    @Column(name="puestoactual")
    private String puestoActual;
    @Column(name="descripcion")
    private String descripcion;
    @Column(name="centroeducativo")
    private String centroEducativo;
    @Column(name="documentocv")
    private String rutaCv;
    @Column(name="profesion")
    private String profesion;
    @Column(name="fechanacimiento")
    private LocalDate fechaNacimiento;
    @Column(name="especialidad")
    private String rutaEspecialidad;
    @Column(name="especialidad2")
    private String rutaEspecialidad2;
    @Column(name="especialidad3")
    private String rutaEspecialidad3;
    @Column(name="descripcionespecialidad1")
    private String descripcionEspecialidad1;
    @Column(name="descripcionespecialidad2")
    private String descripcionEspecialidad2;
    @Column(name="descripcionespecialidad3")
    private String descripcionEspecialidad3;



    @Transient
    private Integer edad;


    @OneToOne
    @JoinColumn(name="id_usuario")
    Usuario usuario;

    @ManyToMany(mappedBy = "candidatos")
    private List<Habilidad> habilidades = new ArrayList<>();
    @ManyToMany(mappedBy = "candidatos")
    private List<Vacante> postulaciones = new ArrayList<>();
    @ManyToMany(mappedBy = "candidatos")
    private List<Idioma> idiomas = new ArrayList<>();
    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = true
    )
    @JoinColumn(name="id_municipio")
    private Municipio municipio;

    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = true
    )
    @JoinColumn(name="id_estado")
    private Estado estado;

    public String getRutaCv() {
        return rutaCv;
    }

    public void setRutaCv(String rutaCv) {
        this.rutaCv = rutaCv;
    }

    public String getPuestoActual() {
        return puestoActual;
    }

    public void setPuestoActual(String puestoActual) {
        this.puestoActual = puestoActual;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCentroEducativo() {
        return centroEducativo;
    }

    public void setCentroEducativo(String centroEducativo) {
        this.centroEducativo = centroEducativo;
    }

    public Integer getId_candidato() {
        return id_candidato;
    }

    public void setId_candidato(Integer id_candidato) {
        this.id_candidato = id_candidato;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Vacante> getPostulaciones() {
        return postulaciones;
    }

    public void setPostulaciones(List<Vacante> postulaciones) {
        this.postulaciones = postulaciones;
    }

    public List<Idioma> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<Idioma> idiomas) {
        this.idiomas = idiomas;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public List<Habilidad> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<Habilidad> habilidades) {
        this.habilidades = habilidades;
    }

    public LocalDate getFechaNacimiento() {

        Period periodoEdad = Period.between(fechaNacimiento, LocalDate.now());
        this.edad = periodoEdad.getYears();

        return fechaNacimiento;

    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {

        Period periodoEdad = Period.between(fechaNacimiento, LocalDate.now());
        this.edad = periodoEdad.getYears();

        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getEdad() {
        return edad;
    }

    public String getRutaEspecialidad() {
        return rutaEspecialidad;
    }

    public void setRutaEspecialidad(String rutaEspecialidad) {
        this.rutaEspecialidad = rutaEspecialidad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getRutaEspecialidad2() {
        return rutaEspecialidad2;
    }

    public void setRutaEspecialidad2(String rutaEspecialidad2) {
        this.rutaEspecialidad2 = rutaEspecialidad2;
    }

    public String getRutaEspecialidad3() {
        return rutaEspecialidad3;
    }

    public void setRutaEspecialidad3(String rutaEspecialidad3) {
        this.rutaEspecialidad3 = rutaEspecialidad3;
    }

    public String getDescripcionEspecialidad1() {
        return descripcionEspecialidad1;
    }

    public void setDescripcionEspecialidad1(String descripcionEspecialidad1) {
        this.descripcionEspecialidad1 = descripcionEspecialidad1;
    }

    public String getDescripcionEspecialidad2() {
        return descripcionEspecialidad2;
    }

    public void setDescripcionEspecialidad2(String descripcionEspecialidad2) {
        this.descripcionEspecialidad2 = descripcionEspecialidad2;
    }

    public String getDescripcionEspecialidad3() {
        return descripcionEspecialidad3;
    }

    public void setDescripcionEspecialidad3(String descripcionEspecialidad3) {
        this.descripcionEspecialidad3 = descripcionEspecialidad3;
    }
}
