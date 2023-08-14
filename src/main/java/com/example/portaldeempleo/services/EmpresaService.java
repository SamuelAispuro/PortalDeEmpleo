package com.example.portaldeempleo.services;

import com.example.portaldeempleo.entities.Empresa;
import com.example.portaldeempleo.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {
    @Autowired
    EmpresaRepository empresaRepository;

    //Dar de alta una empresa
    public Empresa crearEmpresa(String nombre, String descripcion){
        Empresa empresa = new Empresa();

        empresa.setNombre(nombre);
        empresa.setDescripcion(descripcion);
        empresa = empresaRepository.save(empresa);
        return empresa;
    }

}
