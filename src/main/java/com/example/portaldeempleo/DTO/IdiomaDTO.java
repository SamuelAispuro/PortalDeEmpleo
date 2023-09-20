package com.example.portaldeempleo.DTO;

import com.example.portaldeempleo.entities.Idioma;
import com.example.portaldeempleo.entities.IdiomaCandidato;

import java.util.List;

public class IdiomaDTO {

    private Integer id_candidato;
    private List<Idioma> idiomas;
    private String nombreIdioma;
    private Integer id_idioma;

    public Integer getId_idioma() {
        return id_idioma;
    }

    public void setId_idioma(Integer id_idioma) {
        this.id_idioma = id_idioma;
    }

    public String getNombreIdioma() {
        return nombreIdioma;
    }

    public void setNombreIdioma(String nombreIdioma) {
        this.nombreIdioma = nombreIdioma;
    }

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
