package com.example.portaldeempleo.Mail;

import com.example.portaldeempleo.DTO.PasswordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class Controller {

    @Autowired
    Authenticate authenticate;
    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody PasswordRequest passwordRequest){

        authenticate.sendMensaggeUser(passwordRequest.getEmailUser(), passwordRequest.getMessage());

        return ResponseEntity.ok()
                .body("FUNCIONANDO...");
    }

}
