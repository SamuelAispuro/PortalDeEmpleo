package com.example.portaldeempleo.DTO;

import com.example.portaldeempleo.entities.Municipio;

import java.sql.Date;
import java.time.LocalDate;

public class DataDTO {

private Integer id_candidato;
private Integer id_usuario;
private String nombre;
private String apellidoP;
private String apellidoM;
private String correoElectronico;
private String contrasena;
private String telefono;
private Integer edad;
private LocalDate fechaNacimiento;
private String fechaNacimientoStr;
private Integer id_municipio;
private Integer id_estado;
private String domicilio;
private String centroEducativo;
private String puestoActual;
private Integer id_documentoCv;
private String descripcion;
private Boolean estatus;
private String profesion;
private String rutaImagenPerfil;
private String rutaImagenPortada;
private String rutaCv;
private String rutaEspecialidad;

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

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPuestoActual() {
        return puestoActual;
    }

    public void setPuestoActual(String puestoActual) {
        this.puestoActual = puestoActual;
    }

    public Integer getId_documentoCv() {
        return id_documentoCv;
    }

    public void setId_documentoCv(Integer id_documentoCv) {
        this.id_documentoCv = id_documentoCv;
    }

    private Integer tipoUsuario;

    public Integer getId_candidato() {
        return id_candidato;
    }

    public void setId_candidato(Integer id_candidato) {
        this.id_candidato = id_candidato;
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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Integer getId_municipio() {
        return id_municipio;
    }

    public void setId_municipio(Integer id_municipio) {
        this.id_municipio = id_municipio;
    }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public Integer getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCentroEducativo() {
        return centroEducativo;
    }

    public void setCentroEducativo(String centroEducativo) {
        this.centroEducativo = centroEducativo;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaNacimientoStr() {
        return fechaNacimientoStr;
    }

    public void setFechaNacimientoStr(String fechaNacimientoStr) {
        this.fechaNacimientoStr = fechaNacimientoStr;
    }

    public String getRutaCv() {
        return rutaCv;
    }

    public void setRutaCv(String rutaCv) {
        this.rutaCv = rutaCv;
    }

    public String getRutaEspecialidad() {
        return rutaEspecialidad;
    }

    public void setRutaEspecialidad(String rutaEspecialidad) {
        this.rutaEspecialidad = rutaEspecialidad;
    }
}
