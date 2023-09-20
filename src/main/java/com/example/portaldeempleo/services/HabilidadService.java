package com.example.portaldeempleo.services;

import com.example.portaldeempleo.entities.*;
import com.example.portaldeempleo.repositories.HabilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

@Service
public class HabilidadService {

    @Autowired
    HabilidadRepository habilidadRepository;

    //Obtener la lista de habilidades predeterminadas
    public List<Habilidad> obtenerListaHabilidades(){

        List<Habilidad> listaHabilidades = new ArrayList<>();
        listaHabilidades = habilidadRepository.findAll();
        for(Habilidad habilidad : listaHabilidades){
            habilidad.setCandidatos(null);
        }
    return listaHabilidades;
    }

    //CREAR HABILIDAD
    public Habilidad crearHabilidad(String nombreHabilidad){
        Habilidad habilidad = new Habilidad();

        habilidad.setNombreHabilidad(nombreHabilidad);
        habilidad = habilidadRepository.save(habilidad);
        return habilidad;
    }

    //MODIFICAR HABILIDAD
    public Habilidad modificarHabilidad(Integer id_habilidad,String nombreHabilidad) throws Exception {
        try{
            Habilidad habilidad = habilidadRepository.findById(id_habilidad).orElse(null);

            if(habilidad==null){
                throw new Exception("No se encontro una habilidad");
            }

            //Se valida que los datos a modificar no vengan vacios, de ser así no se ejecutara la modificación
            if (nombreHabilidad != null && nombreHabilidad != "") {
                habilidad.setNombreHabilidad(nombreHabilidad);
            }
            habilidad = habilidadRepository.save(habilidad);

            return habilidad;
        }catch(Exception e){
            throw new Exception("Algo salio mal, intentalo de nuevo mas tarde");
        }
    }
}
