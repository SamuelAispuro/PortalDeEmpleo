package com.example.portaldeempleo.entities;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="candidato")
public class Candidato {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_candidato")
    private Integer id_candidato;

    @Column(name="edad")
    private Integer edad;

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

    @OneToOne
    @JoinColumn(name="id_usuario")
    Usuario usuario;

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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
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
}
