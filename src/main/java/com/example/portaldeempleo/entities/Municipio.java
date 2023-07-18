package com.example.portaldeempleo.entities;

import jakarta.persistence.*;

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
}
