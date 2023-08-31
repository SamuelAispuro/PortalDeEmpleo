package com.example.portaldeempleo.services;

import com.example.portaldeempleo.entities.Estado;
import com.example.portaldeempleo.entities.Municipio;
import com.example.portaldeempleo.repositories.EstadoRepository;
import com.example.portaldeempleo.repositories.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class MunicipioService {
    @Autowired
    MunicipioRepository municipioRepository;
    @Autowired
    EstadoRepository estadoRepository;
    @Autowired
    EstadoService estadoService;



    /**
     * Obtener los municipios de un estado
     * @param id_estado(estado)
     * @return listaMunucipiosDeEstado
     */
    public List<Municipio> obtenerListaMunicipiosDeEstado(Integer id_estado){
        /*creacion de un objeto de tipo estado*/
        Estado estadoEncontrado = new Estado();
        /*creacion de la lista que será nuestra respuesta*/
        List<Municipio> listaMunicipiosDeEstado = new ArrayList<>();
        /*a "estadoEncontrado" se le esta asignando el objeto que encuentre el método findById*/
        estadoEncontrado = estadoRepository.findById(id_estado).orElse(null);
        /*se le asignan los municipios del estado encontrado a la lista*/

        listaMunicipiosDeEstado = estadoEncontrado.getMunicipios();

        for(Municipio municipio:listaMunicipiosDeEstado){
            municipio.getEstado().setMunicipios(null);
            municipio.setVacantes_municipios(null);
            municipio.getEstado().setVacantes_estado(null);
        }
    return listaMunicipiosDeEstado;
    }


}
