package com.example.portaldeempleo.DTO;

import com.example.portaldeempleo.entities.Habilidad;

import java.util.List;

public class HabilidadDTO {

    private Integer id_candidato;
    private List<Habilidad> habilidades;

    public Integer getId_candidato() {
        return id_candidato;
    }

    public void setId_candidato(Integer id_candidato) {
        this.id_candidato = id_candidato;
    }

    public List<Habilidad> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<Habilidad> habilidades) {
        this.habilidades = habilidades;
    }
}
