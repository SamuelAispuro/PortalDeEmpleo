package com.example.portaldeempleo.DTO;

import com.example.portaldeempleo.entities.*;

public class RespCandDTO {

    private Boolean estatus;
    private String mensaje;
    private Candidato candidatoModificado;
    private Habilidad habilidadModificada;
    private Idioma idiomaModificado;
    private TipoHorario tipoHorarioModificado;
    private TipoContratacion tipoContratacionModificado;
    private ModalidadTrabajo modalidadTrabajoModificado;

    public Habilidad getHabilidadModificada() {
        return habilidadModificada;
    }

    public void setHabilidadModificada(Habilidad habilidadModificada) {
        this.habilidadModificada = habilidadModificada;
    }

    public Idioma getIdiomaModificado() {
        return idiomaModificado;
    }

    public void setIdiomaModificado(Idioma idiomaModificado) {
        this.idiomaModificado = idiomaModificado;
    }

    public TipoHorario getTipoHorarioModificado() {
        return tipoHorarioModificado;
    }

    public void setTipoHorarioModificado(TipoHorario tipoHorarioModificado) {
        this.tipoHorarioModificado = tipoHorarioModificado;
    }

    public TipoContratacion getTipoContratacionModificado() {
        return tipoContratacionModificado;
    }

    public void setTipoContratacionModificado(TipoContratacion tipoContratacionModificado) {
        this.tipoContratacionModificado = tipoContratacionModificado;
    }

    public ModalidadTrabajo getModalidadTrabajoModificado() {
        return modalidadTrabajoModificado;
    }

    public void setModalidadTrabajoModificado(ModalidadTrabajo modalidadTrabajoModificado) {
        this.modalidadTrabajoModificado = modalidadTrabajoModificado;
    }

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
