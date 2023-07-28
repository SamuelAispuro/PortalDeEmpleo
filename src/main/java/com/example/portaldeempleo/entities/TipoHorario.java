package com.example.portaldeempleo.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tipohorario")
public class TipoHorario {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_tipohorario")
    private Integer id_tipoHorario;

    @Column(name="dias")
    private String dias;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL

    )
    @JoinColumn(name="id_vacante", updatable=false)
    private List<Vacante> tipoHorario_vacantes = new ArrayList<>();

    public Integer getId_tipoHorario() {
        return id_tipoHorario;
    }

    public void setId_tipoHorario(Integer id_tipoHorario) {
        this.id_tipoHorario = id_tipoHorario;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public List<Vacante> getTipoHorario_vacantes() {
        return tipoHorario_vacantes;
    }

    public void setTipoHorario_vacantes(List<Vacante> tipoHorario_vacantes) {
        this.tipoHorario_vacantes = tipoHorario_vacantes;
    }
}
