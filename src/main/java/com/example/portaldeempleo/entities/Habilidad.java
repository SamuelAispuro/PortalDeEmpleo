package com.example.portaldeempleo.entities;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="habilidad")
public class Habilidad {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    @Column(name="id_habilidad")
    private Integer id_habilidad;

    @NonNull
    @Column(name="nombre")
    private String nombreHabilidad;

    @ManyToMany
    @JoinTable(name="habilidad_candidato",
            joinColumns = @JoinColumn(name="id_habilidad", updatable = false),
            inverseJoinColumns = @JoinColumn(name="id_candidato", updatable = false))
    private List<Candidato> candidatos = new ArrayList<>();


    public Integer getId_habilidad() {
        return id_habilidad;
    }

    public void setId_habilidad(Integer id_habilidad) {
        this.id_habilidad = id_habilidad;
    }

    public String getNombreHabilidad() {
        return nombreHabilidad;
    }

    public void setNombreHabilidad(String nombreHabilidad) {
        this.nombreHabilidad = nombreHabilidad;
    }

    public List<Candidato> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(List<Candidato> candidatos) {
        this.candidatos = candidatos;
    }
}
