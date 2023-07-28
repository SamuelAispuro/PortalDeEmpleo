package com.example.portaldeempleo.services;

import com.example.portaldeempleo.DTO.RespuestaDTO;
import com.example.portaldeempleo.entities.Candidato;
import com.example.portaldeempleo.entities.Usuario;
import com.example.portaldeempleo.entities.Vacante;
import com.example.portaldeempleo.repositories.CandidatoRepository;
import com.example.portaldeempleo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PasswordEncryption passwordEncryption;

    @Value("${ruta_local}") // La ruta local donde quieres almacenar las imágenes
    private String rutaLocal;
    //login
    public Usuario login(String correoElectronico, String contraseña){
        Usuario usuarioEncontrado = this.usuarioRepository.findByCorreoElectronicoAndContraseñaAndEstatusUsuario(correoElectronico, passwordEncryption.encryptText(contraseña),true);

        return usuarioEncontrado;
    }

    //Obtener usuario por ID
    public Usuario obtenerUsuarioPorId(Integer id){
        Usuario usuarioEncontrado = this.usuarioRepository.findById(id).orElse(null);
        if(usuarioEncontrado.getEstatusUsuario()==true){
            return usuarioEncontrado;
        }else{
            return null;
        }



    }

    /**
     * Subir foto de perfil
     * @param imagen
     * @param id_usuario
     * @return
     * @throws IOException
     */
    public String subirFotoPerfil(MultipartFile imagen, String id_usuario) throws IOException {
        Usuario usuario = usuarioRepository.findById(Integer.parseInt(id_usuario)).orElse(null);
        File ruta = new File(rutaLocal+File.separator+id_usuario+File.separator+"perfil");

        if(ruta.exists() == false){
            ruta.mkdirs();
        }

        File archivoLocal = new File(rutaLocal+File.separator+id_usuario+File.separator+"perfil"+File.separator+imagen.getOriginalFilename());

        FileOutputStream fos = new FileOutputStream(archivoLocal);
        fos.write(imagen.getBytes());
        fos.close();
        usuario.setRutaImagenPortada(archivoLocal.getAbsolutePath());
        usuarioRepository.save(usuario);

        return "Archivo guardado exitosamente";
    }

    /**
     * Subir foto de portada
     * @param imagen
     * @param id_usuario
     * @return
     * @throws IOException
     */
    public String subirFotoPortada(MultipartFile imagen, String id_usuario) throws IOException {
        Usuario usuario = usuarioRepository.findById(Integer.parseInt(id_usuario)).orElse(null);
        File ruta = new File(rutaLocal+File.separator+id_usuario+File.separator+"portada");

        if(ruta.exists() == false){
            ruta.mkdirs();
        }

        File archivoLocal = new File(rutaLocal+File.separator+id_usuario+File.separator+"portada"+File.separator+imagen.getOriginalFilename());

        FileOutputStream fos = new FileOutputStream(archivoLocal);
        fos.write(imagen.getBytes());
        fos.close();
        usuario.setRutaImagenPortada(archivoLocal.getAbsolutePath());
        usuarioRepository.save(usuario);

        return "Archivo guardado exitosamente";
    }





}

