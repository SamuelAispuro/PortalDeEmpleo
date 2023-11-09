package com.example.portaldeempleo.entities;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
@Table(name="usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    @Column(name="id_usuario")
    private Integer id_usuario;

    @NonNull
    @Column(name="nombre")
    private String nombre;
    //Inv anotación de formato de correo electronico.

    @NonNull
    @Column(name="correoelectronico", unique = true)
    private String correoElectronico;
    @NonNull
    @Column(name="contraseña")
    private String contraseña;
    @NonNull
    @Column(name="tipousuario")
    private Integer tipoUsuario;
    @NonNull
    @Column(name="apellidop")
    private String apellidoP;
    @NonNull
    @Column(name="apellidoM")
    private String apellidoM;
    @NonNull
    @Column(name="telefono", unique = true)
    private String telefono;

    @Column(name="estatususuario")
    private Boolean estatusUsuario;
    @Column(name="imagenperfil")
    private String rutaImagenPerfil;
    @Column(name="imagenportada")
    private String rutaImagenPortada;

    //GETTERS Y SETTERS
    public String getRutaImagenPerfil() {
        return rutaImagenPerfil;
    }

    public void setRutaImagenPerfil(String rutaImagenPerfil) {
        this.rutaImagenPerfil = rutaImagenPerfil;
    }

    public String getRutaImagenPortada() {
        return rutaImagenPortada;
    }

    public void setRutaImagenPortada(String rutaImagenPortada) {
        this.rutaImagenPortada = rutaImagenPortada;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Integer getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Boolean getEstatusUsuario() {
        return estatusUsuario;
    }

    public void setEstatusUsuario(Boolean estatusUsuario) {
        this.estatusUsuario = estatusUsuario;
    }
}
