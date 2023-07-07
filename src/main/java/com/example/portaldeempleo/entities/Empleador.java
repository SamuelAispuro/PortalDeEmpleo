package com.example.portaldeempleo.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="empleador")
public class Empleador {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_empleador")
    private Integer id_empleador;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL

    )
    @JoinColumn(name="id_vacante", updatable=false)
    private List<Vacante> vacantes = new ArrayList<>();

    @OneToOne
    @JoinColumn(name="id_usuario")
    Usuario usuario;

    @Column(name="imagenperfil")
    private byte[] imagenPerfil;

    @Column(name="imagenportada")
    private byte[] imagenPortada;

    /*@OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL

    )
    @JoinColumn(name="id_empresa")
    private List<Empresa> empresas = new ArrayList<>();*/

    public Integer getId_empleador() {
        return id_empleador;
    }

    public void setId_empleador(Integer id_empleador) {
        this.id_empleador = id_empleador;
    }

    public List<Vacante> getVacantes() {
        return vacantes;
    }

    public void setVacantes(List<Vacante> vacantes) {
        this.vacantes = vacantes;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public byte[] getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(byte[] imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }

    public byte[] getImagenPortada() {
        return imagenPortada;
    }

    public void setImagenPortada(byte[] imagenPortada) {
        this.imagenPortada = imagenPortada;
    }
}