package com.example.portaldeempleo.entities;

import jakarta.persistence.*;

@Entity
@Table(name="idiomaCandidato")
public class IdiomaCandidato {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_idiomacandidato")
    private Integer id_idiomacandidato;

    @OneToOne
    @JoinColumn(name="id_idioma", updatable = false)
    private Idioma idioma;

    @OneToOne
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
