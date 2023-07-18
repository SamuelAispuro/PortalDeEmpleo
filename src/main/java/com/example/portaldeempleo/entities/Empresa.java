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
    private List<Vacante> vacantes = new ArrayList<>();

    @Column(name="nombreempresa")
    private String nombreEmpresa;

    @Column(name="descripcion")
    private String descripcion;

    /*@Column(name="id_reseña")fg
    private Reseña id_reseña;*/

    public Integer getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(Integer id_empresa) {
        this.id_empresa = id_empresa;
    }

    public List<Vacante> getVacantes() {
        return vacantes;
    }

    public void setVacantes(List<Vacante> vacantes) {
        this.vacantes = vacantes;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
