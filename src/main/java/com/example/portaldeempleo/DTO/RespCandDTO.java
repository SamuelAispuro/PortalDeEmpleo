package com.example.portaldeempleo.DTO;

import com.example.portaldeempleo.entities.Candidato;

public class RespCandDTO {

    private Boolean estatus;
    private String mensaje;
    private Candidato candidatoModificado;

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Candidato getCandidatoModificado() {
        return candidatoModificado;
    }

    public void setCandidatoModificado(Candidato candidatoModificado) {
        this.candidatoModificado = candidatoModificado;
    }
}
