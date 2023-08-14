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
    private Integer id_tipoContratacion;

    @Column(name="horario")
    private String horario;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL

    )
    @JoinColumn(name="id_vacante", updatable=false)
    private List<Vacante> tipoContratacion_vacantes = new ArrayList<>();

    public Integer getId_tipoContratacion() {
        return id_tipoContratacion;
    }

    public void setId_tipoContratacion(Integer id_tipoContratacion) {
        this.id_tipoContratacion = id_tipoContratacion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public List<Vacante> getTipoContratacion_vacantes() {
        return tipoContratacion_vacantes;
    }

    public void setTipoContratacion_vacantes(List<Vacante> tipoContratacion_vacantes) {
        this.tipoContratacion_vacantes = tipoContratacion_vacantes;
    }
}
