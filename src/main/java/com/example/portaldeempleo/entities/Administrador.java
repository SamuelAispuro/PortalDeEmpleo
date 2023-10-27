package com.example.portaldeempleo.entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
@Table(name="administrador")
public class Administrador {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Nonnull
    @Column(name="id_administrador")
    private Integer id_administrador;

    @OneToOne
    @NonNull
    @JoinColumn(name="id_usuario")
    Usuario usuario;


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

}
