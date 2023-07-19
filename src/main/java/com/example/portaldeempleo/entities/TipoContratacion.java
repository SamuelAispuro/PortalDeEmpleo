package com.example.portaldeempleo.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tipocontratacion")
public class TipoContratacion {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_tipocontratacion")
    private Integer id_contratacion;

    @Column(name="horario")
    private Integer horario;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL

    )
    @JoinColumn(name="id_vacante", updatable=false)
    private List<Vacante> tipoContratacion_vacantes = new ArrayList<>();

    public Integer getId_contratacion() {
        return id_contratacion;
    }

    public void setId_contratacion(Integer id_contratacion) {
        this.id_contratacion = id_contratacion;
    }

    public Integer getHorario() {
        return horario;
    }

    public void setHorario(Integer horario) {
        this.horario = horario;
    }

    public List<Vacante> getTipoContratacion_vacantes() {
        return tipoContratacion_vacantes;
    }

    public void setTipoContratacion_vacantes(List<Vacante> tipoContratacion_vacantes) {
        this.tipoContratacion_vacantes = tipoContratacion_vacantes;
    }
}
