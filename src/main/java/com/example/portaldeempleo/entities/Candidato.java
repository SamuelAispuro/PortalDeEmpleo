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

    @OneToOne
    @JoinColumn(name="id_usuario")
    Usuario usuario;

    @ManyToMany(mappedBy = "candidatos")
    private List<Vacante> postulaciones = new ArrayList<>();
    @ManyToMany(mappedBy = "candidatos")
    private List<Idioma> idiomas = new ArrayList<>();

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
}
