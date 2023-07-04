package com.example.portaldeempleo.entities;
import jakarta.persistence.*;
@Entity
@Table(name="candidato")
public class Candidato {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_candidato")
    private Integer id_candidato;

    /*Por definir
    private int id_tipoUsuario=0;*/
    @OneToOne
    @JoinColumn(name="id_usuario")
    Usuario usuario;

    @Column(name="apellidop")
    private String apellidoP;

    @Column(name="apellidom")
    private String apellidoM;

    @Column(name="edad")
    private Integer edad;

    @Column(name="telefono")
    private String telefono;

    public Integer getId_candidato() {
        return id_candidato;
    }

    public void setId_candidato(Integer id_candidato) {
        this.id_candidato = id_candidato;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
