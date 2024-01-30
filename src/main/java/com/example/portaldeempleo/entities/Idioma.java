package com.example.portaldeempleo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="idioma")
public class Idioma {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    @Column(name="id_idioma")
    private Integer id_idioma;

    @NonNull
    @Column(name="nombreidioma")
    private String nombreIdioma;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name="idioma_candidato",
            joinColumns = @JoinColumn(name="id_idioma"),
            inverseJoinColumns = @JoinColumn(name="id_candidato"))
    private List<Candidato> candidatos = new ArrayList<>();

    public Integer getId_idioma() {
        return id_idioma;
    }

    public void setId_idioma(Integer id_idioma) {
        this.id_idioma = id_idioma;
    }

    public String getNombreIdioma() {
        return nombreIdioma;
    }

    public void setNombreIdioma(String nombreIdioma) {
        this.nombreIdioma = nombreIdioma;
    }

    public List<Candidato> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(List<Candidato> candidatos) {
        this.candidatos = candidatos;
    }
}
