package com.example.portaldeempleo.entities;

import jakarta.persistence.*;

@Entity
@Table(name="administrador")
public class Administrador {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_administrador")
    private Integer id_administrador;

    @OneToOne
    @JoinColumn(name="id_usuario")
    Usuario usuario;

    @Column(name="imagenperfil")
    private byte[] imagenPerfil;

    @Column(name="imagenportada")
    private byte[] imagenPortada;

    /*@Column(name="id_peticion")
    private Peticion peticion;*/

    /*@Column(name="id_conversacion")
    private  mensaje;*/

    /*@Column(name="id_notificacion")
    private Notificacion notificacion;*/

    public Integer getId_administrador() {
        return id_administrador;
    }

    public void setId_administrador(Integer id_administrador) {
        this.id_administrador = id_administrador;
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
