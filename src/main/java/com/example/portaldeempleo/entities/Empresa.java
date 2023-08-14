package com.example.portaldeempleo.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_empresa")
    private Integer id_empresa;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL

    )
    @JoinColumn(name="id_vacante", updatable=false)
    private List<Vacante> vacantes_empresa = new ArrayList<>();

    @Column(name="nombreempresa")
    private String nombre;

    @Column(name="descripcion")
    private String descripcion;

    public Integer getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(Integer id_empresa) {
        this.id_empresa = id_empresa;
    }

    public List<Vacante> getVacantes_empresa() {
        return vacantes_empresa;
    }

    public void setVacantes_empresa(List<Vacante> vacantes_empresa) {
        this.vacantes_empresa = vacantes_empresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
