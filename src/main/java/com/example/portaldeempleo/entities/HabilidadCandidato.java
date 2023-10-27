package com.example.portaldeempleo.entities;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
@Table(name="habilidad_candidato")
public class HabilidadCandidato {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    @Column(name="id")
    private Integer id;

    @OneToOne
    @NonNull
    @JoinColumn(name="id_habilidad", updatable = false)
    private Habilidad habilidad;

    @OneToOne
    @NonNull
    @JoinColumn(name="id_candidato", updatable = false)
    private Candidato candidato;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Habilidad getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(Habilidad habilidad) {
        this.habilidad = habilidad;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }
}
