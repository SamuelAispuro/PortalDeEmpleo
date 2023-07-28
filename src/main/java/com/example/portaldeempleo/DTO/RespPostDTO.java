package com.example.portaldeempleo.DTO;

import com.example.portaldeempleo.entities.Postulacion;

public class RespPostDTO {

    private Boolean estatus;
    private Postulacion postulacion;
    private String mensaje;

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }

    public Postulacion getPostulacion() {
        return postulacion;
    }

    public void setPostulacion(Postulacion postulacion) {
        this.postulacion = postulacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
