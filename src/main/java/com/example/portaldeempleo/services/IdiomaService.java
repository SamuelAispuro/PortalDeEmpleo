package com.example.portaldeempleo.services;

import com.example.portaldeempleo.entities.Candidato;
import com.example.portaldeempleo.entities.Habilidad;
import com.example.portaldeempleo.entities.Idioma;
import com.example.portaldeempleo.entities.TipoContratacion;
import com.example.portaldeempleo.repositories.CandidatoRepository;
import com.example.portaldeempleo.repositories.IdiomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IdiomaService {
    @Autowired
    IdiomaRepository idiomaRepository;
    @Autowired
    CandidatoRepository candidatoRepository;

    //Obtener lista de idiomas
    public List<Idioma> obtenerListaIdiomas(){

        List<Idioma> listaIdiomas = new ArrayList<>();
        listaIdiomas = idiomaRepository.findAll();
        for(Idioma idioma : listaIdiomas){
            idioma.setCandidatos(null);
        }
        return listaIdiomas;

    }
    //CREAR IDIOMA
    public Idioma crearIdioma(String nombreIdioma){
        Idioma idioma = new Idioma();

        idioma.setNombreIdioma(nombreIdioma);
        idioma = idiomaRepository.save(idioma);
        return idioma;
    }

    //MODIFICAR IDIOMA
    public Idioma modificarIdioma(Integer id_idioma,String nombreIdioma) throws Exception {
        try{
            Idioma idioma = idiomaRepository.findById(id_idioma).orElse(null);

            if(idioma==null){
                throw new Exception("No se encontro un idioma");
            }

            //Se valida que los datos a modificar no vengan vacios, de ser así no se ejecutara la modificación
            if (nombreIdioma != null && nombreIdioma != "") {
                idioma.setNombreIdioma(nombreIdioma);
            }
            idioma = idiomaRepository.save(idioma);

            return idioma;
        }catch(Exception e){
            throw new Exception("Algo salio mal, intentalo de nuevo mas tarde");
        }
    }

}
