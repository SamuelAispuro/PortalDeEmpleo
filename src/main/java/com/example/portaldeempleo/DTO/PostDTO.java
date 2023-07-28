package com.example.portaldeempleo.DTO;

public class PostDTO {

    private Integer id_candidato;
    private Integer id_vacante;
    private Boolean verif;
    private Integer id_postulacion;

    public Boolean getVerif() {
        return verif;
    }

    public void setVerif(Boolean verif) {
        this.verif = verif;
    }

    public Integer getId_postulacion() {
        return id_postulacion;
    }

    public void setId_postulacion(Integer id_postulacion) {
        this.id_postulacion = id_postulacion;
    }

    public Integer getId_candidato() {
        return id_candidato;
    }

    public void setId_candidato(Integer id_candidato) {
        this.id_candidato = id_candidato;
    }

    public Integer getId_vacante() {
        return id_vacante;
    }

    public void setId_vacante(Integer id_vacante) {
        this.id_vacante = id_vacante;
    }
}
