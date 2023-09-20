package com.example.portaldeempleo.DTO;

public class TipoContratacionDTO {
    private String horario;
    private Integer id_tipoContratacion;

    public Integer getId_tipoContratacion() {
        return id_tipoContratacion;
    }

    public void setId_tipoContratacion(Integer id_tipoContratacion) {
        this.id_tipoContratacion = id_tipoContratacion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
