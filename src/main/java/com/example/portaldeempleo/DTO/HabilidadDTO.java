package com.example.portaldeempleo.DTO;

import com.example.portaldeempleo.entities.Habilidad;

import java.util.List;

public class HabilidadDTO {

    private Integer id_candidato;
    private List<Habilidad> listaHabilidades;

    public Integer getId_candidato() {
        return id_candidato;
    }

    public void setId_candidato(Integer id_candidato) {
        this.id_candidato = id_candidato;
    }

    public List<Habilidad> getListaHabilidades() {
        return listaHabilidades;
    }

    public void setListaHabilidades(List<Habilidad> listaHabilidades) {
        this.listaHabilidades = listaHabilidades;
    }
}
