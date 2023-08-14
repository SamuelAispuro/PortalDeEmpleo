package com.example.portaldeempleo.DTO;

public class RespRegDTO {
    public String mensaje;
    public Boolean estatus;
    private Boolean estatusBoolean;

    public Boolean getEstatusBoolean() {
        return estatusBoolean;
    }

    public void setEstatusBoolean(Boolean estatusBoolean) {
        this.estatusBoolean = estatusBoolean;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }
}
