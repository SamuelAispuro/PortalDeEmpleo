package com.example.portaldeempleo.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="municipio")
public class Municipio {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_municipio")
    private Integer id_municipio;

    @Column(name="nombremunicipio")
    private String nombreMunicipio;
    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = true
    )
    @JoinColumn(name="id_estado")
    private Estado estado;
    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL

    )
    @JoinColumn(name="id_vacante", updatable=false)
    private List<Vacante> vacantes_municipios = new ArrayList<>();

    public Integer getId_municipio() {
        return id_municipio;
    }

    public void setId_municipio(Integer id_municipio) {
        this.id_municipio = id_municipio;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Vacante> getVacantes_municipios() {
        return vacantes_municipios;
    }

    public void setVacantes_municipios(List<Vacante> vacantes_municipios) {
        this.vacantes_municipios = vacantes_municipios;
    }
}
