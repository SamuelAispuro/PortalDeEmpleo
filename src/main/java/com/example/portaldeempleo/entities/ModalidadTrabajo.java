package com.example.portaldeempleo.entities;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="modalidadtrabajo")
public class ModalidadTrabajo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    @Column(name="id_modalidadtrabajo")
    private Integer id_modalidadTrabajo;

    @NonNull
    @Column(name="modalidad")
    private String modalidad;

    public Integer getId_modalidad() {
        return id_modalidadTrabajo;
    }

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL

    )
    @JoinColumn(name="id_vacante", updatable=false)
    private List<Vacante> modalidadTrabajo_vacante = new ArrayList<>();

    public void setId_modalidad(Integer id_modalidad) {
        this.id_modalidadTrabajo = id_modalidad;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public List<Vacante> getModalidadTrabajo_vacante() {
        return modalidadTrabajo_vacante;
    }

    public void setModalidadTrabajo_vacante(List<Vacante> modalidadTrabajo_vacante) {
        this.modalidadTrabajo_vacante = modalidadTrabajo_vacante;
    }
}
