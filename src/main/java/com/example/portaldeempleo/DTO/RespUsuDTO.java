package com.example.portaldeempleo.DTO;

import com.example.portaldeempleo.entities.Candidato;
import com.example.portaldeempleo.entities.Usuario;

public class RespUsuDTO {

    private Boolean estatus;
    private String mensaje;
    private Usuario usuarioModificado;

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

    public Usuario getUsuarioModificado() {
        return usuarioModificado;
    }

    public void setUsuarioModificado(Usuario usuarioModificado) {
        this.usuarioModificado = usuarioModificado;
    }
}
