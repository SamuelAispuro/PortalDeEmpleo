package com.example.portaldeempleo.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="procesosautomaticos")
public class ProcesosAutomaticos {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_proceso")
    private Integer id_proceso;

    @Column(name="tipoproceso")
    private String tipoProceso;

    @Column(name="fechaproceso")
    private LocalDate fechaProceso;
    @Column(name="nombrevacante")
    private String nombreVacante;
    @Column(name="descripcion")
    private String descripcion;

    public Integer getId_proceso() {
        return id_proceso;
    }

    public void setId_proceso(Integer id_proceso) {
        this.id_proceso = id_proceso;
    }

    public String getTipoProceso() {
        return tipoProceso;
    }

    public void setTipoProceso(String tipoProceso) {
        this.tipoProceso = tipoProceso;
    }

    public LocalDate getFechaProceso() {
        return fechaProceso;
    }

    public void setFechaProceso(LocalDate fechaProceso) {
        this.fechaProceso = fechaProceso;
    }

    public String getNombreVacante() {
        return nombreVacante;
    }

    public void setNombreVacante(String nombreVacante) {
        this.nombreVacante = nombreVacante;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
