package com.example.portaldeempleo.entities;

import jakarta.persistence.*;

@Entity
@Table(name="postulaciones")
public class Postulacion {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_postulacion")
    private Integer id_postulacion;
    @Column(name="estatus")
    private Integer estatus;
    @OneToOne
    @JoinColumn(name="id_vacante", updatable = false)
    private Vacante vacante;
    @OneToOne
    @JoinColumn(name="id_candidato", updatable = false)
    private Candidato candidato;
    public Integer getId_postulacion() {
        return id_postulacion;
    }

    public void setId_postulacion(Integer id_postulacion) {
        this.id_postulacion = id_postulacion;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public Vacante getVacante() {
        return vacante;
    }

    public void setVacante(Vacante vacante) {
        this.vacante = vacante;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }
}
