package com.example.portaldeempleo.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="estado")
public class Estado {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_estado")
    private Integer id_estado;

    @Column(name="nombreestado")
    private String nombreEstado;
    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL

    )
    @JoinColumn(name="id_municipio", updatable=false)
    private List<Municipio> municipios = new ArrayList<>();

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }
}
