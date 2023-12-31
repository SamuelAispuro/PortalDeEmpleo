package com.example.portaldeempleo.controller;

import java.time.*;
import com.example.portaldeempleo.dominio.*;
import com.example.portaldeempleo.DTO.*;

import com.example.portaldeempleo.Mail.Authenticate;
import com.example.portaldeempleo.entities.Usuario;
import com.example.portaldeempleo.entities.Vacante;
import com.example.portaldeempleo.repositories.UsuarioRepository;
import com.example.portaldeempleo.services.UsuarioService;
import com.itextpdf.text.DocumentException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    Authenticate authenticate;

    //Login
    @PostMapping("/Login")
    public ResponseEntity<?> login(@RequestBody DataDTO requestData) {

        Usuario usuarioEncontrado = this.usuarioService.login(requestData.getCorreoElectronico(), requestData.getContrasena());
        LoginDTO respuesta = new LoginDTO();
        //RespuestaDTO respuesta = new RespuestaDTO();
        if (requestData.getCorreoElectronico() != null && requestData.getCorreoElectronico() != "" && requestData.getContrasena() != null && requestData.getContrasena() != "") {

            if (usuarioEncontrado != null) {
                usuarioEncontrado.setContraseña(null);
                respuesta.setUsuario(usuarioEncontrado);

                respuesta.setEstatus(true);
                respuesta.setMensaje("Usuario logeado correctamente");
                return new ResponseEntity<>(respuesta, HttpStatus.OK);
            } else {
                respuesta.setEstatus(false);
                respuesta.setMensaje("Los datos ingresados son incorrectos");
                return new ResponseEntity<>(respuesta, HttpStatus.OK);
            }

        } else {
            respuesta.setMensaje("Los campos no pueden ser enviados en blanco, intentalo nuevamente");
            respuesta.setEstatus(false);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
    }

    //Obtener usuario por ID
    @GetMapping("/obtenerUsuarioPorId/{id}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Integer id) {
        try {
            if (id != null && id > 0) {
                Usuario usuarioEncontrado = new Usuario();
                usuarioEncontrado = this.usuarioService.obtenerUsuarioPorId(id);
                return new ResponseEntity<>(usuarioEncontrado, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("El id enviado no es válido", HttpStatus.OK);
            }
        }catch(Exception e){
            return new ResponseEntity<>("Algo salio mal, intentelo de nuevo", HttpStatus.OK);
        }
    }

    //Guardar o modificar foto de perfil de un usuario
    @PutMapping("/guardarArchivo")
    public ResponseEntity<?> guardarArchivo(@RequestBody DataDTO usuarioDTO){
        RespUsuDTO respuesta = new RespUsuDTO();
        Usuario usuarioModificado = this.usuarioService.guardarArchivo(usuarioDTO.getId_usuario(), usuarioDTO.getRutaImagenPerfil(),
                usuarioDTO.getRutaImagenPortada(), usuarioDTO.getRutaCv(), usuarioDTO.getRutaEspecialidad(),usuarioDTO.getRutaEspecialidad2(),
                usuarioDTO.getRutaEspecialidad3(), usuarioDTO.getDescripcionEspecialidad1(), usuarioDTO.getDescripcionEspecialidad2(), usuarioDTO.getDescripcionEspecialidad3());
        respuesta.setUsuarioModificado(usuarioModificado);
        respuesta.setEstatus(true);
        respuesta.setMensaje("Cambio realizado correctamente");
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    //Suspender usuario
    @PutMapping("/suspenderUsuario")
    public ResponseEntity<?> suspenderUsuario(@RequestBody UsuarioDTO usuarioDTO ){
       RespPostDTO respuesta = new RespPostDTO();
       Usuario usuarioEncontrado = usuarioService.suspenderUsuario(usuarioDTO.getId_usuario());
        respuesta.setMensaje("Usuario suspendido correctamente");
        respuesta.setEstatus(true);
       return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
    
     
     // ENVIAR CORREOS
     @GetMapping("/peticion/{correo}")
     public ResponseEntity<?> peticion_deCambio(@PathVariable String correo) {
         RespPostDTO respuesta = new RespPostDTO();
         
         // ESTE ES EL URL QUE DEBES MANDAR
         String url ="http://localhost:4200/cambiar_contraseña/"+correo;
         
         respuesta.setEstatus(this.usuarioService.buscarCorreo(correo));
         if(respuesta.getEstatus()) {

             authenticate.sendMensaggeUser(correo,url);
         	
         	respuesta.setMensaje("Se ha enviado un correo al mail indicado");
         } else {
         	respuesta.setMensaje("El correo no esta registrado en nuestra base de datos");
         }
     	 return new ResponseEntity<>(respuesta, HttpStatus.OK);
     }
  
     
  // ENVIAR CAMBIAR CONTRASEÑA
     @PostMapping("/cambio_dePass")
     public ResponseEntity<?> peticion_deCambio(@RequestBody DataDTO requestData) {
    	 RespPostDTO respuesta = new RespPostDTO();
    	 respuesta.setEstatus(this.usuarioService.cambiarContra(requestData.getCorreoElectronico(),requestData.getContrasena()));
    	 if(respuesta.getEstatus()) {
    		 respuesta.setMensaje("La contraseña fue cambiada correctamente, esta pestaña se puede cerrar");
    	 } else{
    		 respuesta.setMensaje("Se ha producido un error que nos impidio realizar el cambio");
    	 }

    	 return new ResponseEntity<>(respuesta, HttpStatus.OK);
     }

    //Obtener lista de vacantes filtro actividad
    @GetMapping("/obtenerListaUsuariosActividad")
    public ResponseEntity<?>  obtenerListaUsuariosFiltro(@RequestParam String tipoFiltro, @RequestParam("id_usuario") Integer id_usuario) throws FileNotFoundException, DocumentException {
        Usuario admin = usuarioService.obtenerUsuarioPorId(id_usuario);
    	List<Usuario> listaUsuarios = this.usuarioService.obtenerListaUsuariosFiltro(tipoFiltro);
        Formato reporte = new Formato();
        boolean error;
        try {
        reporte.crearDocumento();
        reporte.abrirDocumento();
        reporte.agregarTitulo("REPORTE DE USUARIOS "+ tipoFiltro.toUpperCase());
        reporte.agregarSaltoDeLinea();
        reporte.agregarTablaUsuarios(listaUsuarios);
        reporte.agregarSaltoDeLinea();
        reporte.agregarParrafo("Reporte generado por "+admin.getNombre()+" "+admin.getApellidoP()+" "+admin.getApellidoM()+".");
        reporte.cerrarDocumento();
        error = false;
    	} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		error = true;
		}
        
        return new ResponseEntity<>(error, HttpStatus.OK);
    }

    //Obtener lista de usuarios por rol
    @GetMapping("/obtenerListaUsuariosRol")
    public ResponseEntity<?>  obtenerListaUsuariosRol(@RequestParam String rol, @RequestParam("id_usuario") Integer id_usuario) throws FileNotFoundException, DocumentException {
        List<Usuario> listaUsuarios = this.usuarioService.obtenerListaUsuariosRol(rol);
        Usuario admin = usuarioService.obtenerUsuarioPorId(id_usuario);
        Formato reporte = new Formato();
        boolean error;

        try {
			reporte.crearDocumento();
			reporte.abrirDocumento();
	        reporte.agregarTitulo("REPORTE DE USUARIOS CON ROL " + rol.toUpperCase());
	        reporte.agregarSaltoDeLinea();
	        reporte.agregarTablaUsuarios(listaUsuarios);
	        reporte.agregarSaltoDeLinea();
	        reporte.agregarParrafo("Reporte generado por "+admin.getNombre()+" "+admin.getApellidoP()+" "+admin.getApellidoM()+".");
	        reporte.cerrarDocumento();
	        error = false;

		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = true;
		
		}
        return new ResponseEntity<>(error, HttpStatus.OK);
    }
    
}
