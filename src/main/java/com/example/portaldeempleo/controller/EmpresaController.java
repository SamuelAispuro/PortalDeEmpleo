package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.DTO.DataDTO;
import com.example.portaldeempleo.DTO.RespRegDTO;
import com.example.portaldeempleo.entities.Empresa;
import com.example.portaldeempleo.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EmpresaController {
    @Autowired
    EmpresaService empresaService;

    @PostMapping("/crearEmpresa")
    public ResponseEntity<?>crearEmpresa(@RequestBody DataDTO empresaDTO){
        Integer id_empresa =0;
        Empresa empresa = new Empresa();
        RespRegDTO respuesta = new RespRegDTO();
        if(empresaDTO.getNombre() != null && empresaDTO.getDescripcion() != ""){
            empresa = this.empresaService.crearEmpresa(empresaDTO.getNombre(),empresaDTO.getDescripcion());
            respuesta.setMensaje("La empresa se ha creado correctamente");
            respuesta.setEstatus(true);
        }else{
            respuesta.setMensaje("La empresa no puede ser creada con campos vacios o invalidos, intentalo de nuevo");
            respuesta.setEstatus(false);
        }
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

}
