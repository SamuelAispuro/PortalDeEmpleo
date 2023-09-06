package com.example.portaldeempleo.DTO;

import com.example.portaldeempleo.entities.Idioma;

import java.util.List;

public class IdiomaDTO {

    private Integer id_candidato;
    private List<Idioma> listaIdiomas;

    public Integer getId_candidato() {
        return id_candidato;
    }

    public void setId_candidato(Integer id_candidato) {
        this.id_candidato = id_candidato;
    }

    public List<Idioma> getListaIdiomas() {
        return listaIdiomas;
    }

    public void setListaIdiomas(List<Idioma> listaIdiomas) {
        this.listaIdiomas = listaIdiomas;
    }
}
