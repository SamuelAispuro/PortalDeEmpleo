package com.example.portaldeempleo.services;

import com.example.portaldeempleo.entities.Empresa;
import com.example.portaldeempleo.entities.Vacante;
import com.example.portaldeempleo.repositories.VacanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VacanteService {
    @Autowired
    VacanteRepository vacanteRepository;

    //Metodo para crear una vacante
    public Integer crearVacante(String nombre, String especialista, Integer sueldo, Integer id_empresa){
        Empresa empresa = new Empresa();
        empresa.setId_empresa(id_empresa);

        Vacante vacante = new Vacante();
        vacante.setEmpresa(empresa);
        vacante.setNombre(nombre);
        vacante.setEspecialista(especialista);
        vacante.setSueldo(sueldo);

        vacante = vacanteRepository.save(vacante);

        return vacante.getId_vacante();
    }
}
