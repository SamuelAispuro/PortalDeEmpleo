package com.example.portaldeempleo.DTO;

public class TipoHorarioDTO {
    private String dias;
    private Integer id_tipoHorario;

    public Integer getId_tipoHorario() {
        return id_tipoHorario;
    }

    public void setId_tipoHorario(Integer id_tipoHorario) {
        this.id_tipoHorario = id_tipoHorario;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }
}
