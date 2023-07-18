package com.example.portaldeempleo.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="modalidadtrabajo")
public class ModalidadTrabajo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_modalidad")
    private Integer id_modalidad;
    @Column
    private Integer modalidad;

    public Integer getId_modalidad() {
        return id_modalidad;
    }

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL

    )
    @JoinColumn(name="id_vacante", updatable=false)
    private List<Vacante> modalidadTrabajo_vacante = new ArrayList<>();

    public void setId_modalidad(Integer id_modalidad) {
        this.id_modalidad = id_modalidad;
    }

    public Integer getModalidad() {
        return modalidad;
    }

    public void setModalidad(Integer modalidad) {
        this.modalidad = modalidad;
    }

    public List<Vacante> getModalidadTrabajo_vacante() {
        return modalidadTrabajo_vacante;
    }

    public void setModalidadTrabajo_vacante(List<Vacante> modalidadTrabajo_vacante) {
        this.modalidadTrabajo_vacante = modalidadTrabajo_vacante;
    }
}
