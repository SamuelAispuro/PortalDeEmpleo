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
    private LocalDate fechaproceso;
    @Column(name="nombrevacante")
    private String nombrevacante;

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

    public LocalDate getFechaproceso() {
        return fechaproceso;
    }

    public void setFechaproceso(LocalDate fechaproceso) {
        this.fechaproceso = fechaproceso;
    }

    public String getNombrevacante() {
        return nombrevacante;
    }

    public void setNombrevacante(String nombrevacante) {
        this.nombrevacante = nombrevacante;
    }
}
