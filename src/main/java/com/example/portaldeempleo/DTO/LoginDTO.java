package com.example.portaldeempleo.DTO;

import com.example.portaldeempleo.entities.Usuario;

public class LoginDTO {

    private String mensaje;
    private Boolean estatus;
    private Usuario usuario;

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
