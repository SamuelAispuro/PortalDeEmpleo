package com.example.portaldeempleo.controller;

import com.example.portaldeempleo.DTO.DataDTO;
import com.example.portaldeempleo.DTO.LoginDTO;
import com.example.portaldeempleo.DTO.RespuestaDTO;
import com.example.portaldeempleo.entities.Usuario;
import com.example.portaldeempleo.repositories.UsuarioRepository;
import com.example.portaldeempleo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;
    @Value("${ruta_local}") // La ruta local donde quieres almacenar las imágenes
    private String rutaLocal;

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

        //


    }

    //Obtener usuario por ID
    @GetMapping("/obtenerUsuarioPorId/{id}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Integer id) {
        Usuario usuarioEncontrado = new Usuario();
        usuarioEncontrado = this.usuarioService.obtenerUsuarioPorId(id);
        return new ResponseEntity<>(usuarioEncontrado, HttpStatus.OK);
    }

    //Agregar o modificar una foto (perfil)
    @PostMapping("/subirImagenPerfil")
    public ResponseEntity<?> subirImagenPerfil(@RequestParam String id, @RequestParam MultipartFile imagen) throws IOException {
        // Verificar que se haya enviado una imagen
        if (imagen == null || imagen.isEmpty()) {
            return new ResponseEntity<>("Debe enviar una imagen", HttpStatus.BAD_REQUEST);
        }

        String respuesta = usuarioService.subirFotoPerfil(imagen,id);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);

    }

    //Agregar o modificar una foto (portada)
    @PostMapping("/subirImagenPortada")
    public ResponseEntity<?> subirImagenPortada(@RequestParam String id, @RequestParam MultipartFile imagen) throws IOException {
        // Verificar que se haya enviado una imagen
        if (imagen == null || imagen.isEmpty()) {
            return new ResponseEntity<>("Debe enviar una imagen", HttpStatus.BAD_REQUEST);
        }

        String respuesta = usuarioService.subirFotoPortada(imagen,id);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);

    }



}
