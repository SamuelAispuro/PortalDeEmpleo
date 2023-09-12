package com.example.portaldeempleo.DTO;

import com.example.portaldeempleo.entities.Idioma;
import com.example.portaldeempleo.entities.IdiomaCandidato;

import java.util.List;

public class IdiomaDTO {

    private Integer id_candidato;
    private List<Idioma> idiomas;

    public Integer getId_candidato() {
        return id_candidato;
    }

    public void setId_candidato(Integer id_candidato) {
        this.id_candidato = id_candidato;
    }

    public List<Idioma> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<Idioma> idiomas) {
        this.idiomas = idiomas;
    }
}
