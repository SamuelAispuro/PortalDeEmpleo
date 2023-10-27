package com.example.portaldeempleo.entities;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.List;

@Entity
@Table(name="idioma_candidato")
public class IdiomaCandidato {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    @Column(name="id_idiomacandidato")
    private Integer id_idiomacandidato;

    @OneToOne
    @NonNull
    @JoinColumn(name="id_idioma", updatable = false)
    private Idioma idioma;

    @OneToOne
    @NonNull
    @JoinColumn(name="id_candidato", updatable = false)
    private Candidato candidato;

    public Integer getId_idiomacandidato() {
        return id_idiomacandidato;
    }

    public void setId_idiomacandidato(Integer id_idiomacandidato) {
        this.id_idiomacandidato = id_idiomacandidato;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }
}
