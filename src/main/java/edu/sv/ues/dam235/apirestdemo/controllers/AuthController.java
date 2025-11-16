package edu.sv.ues.dam235.apirestdemo.controllers;

import edu.sv.ues.dam235.apirestdemo.dtos.LoginDTO;
import edu.sv.ues.dam235.apirestdemo.dtos.RegistroDTO;
import edu.sv.ues.dam235.apirestdemo.dtos.TokenDTO;
import edu.sv.ues.dam235.apirestdemo.services.AuthServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthServices authServices;

    // te recomiendo que el constructor sea public
    public AuthController(AuthServices authServices) {
        this.authServices = authServices;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO authRequest) {
        try {
            System.out.println("DTO enviado : " + authRequest.toString());
            TokenDTO token =
                    authServices.login(authRequest.getUser(), authRequest.getPass());

            if (token == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            } else {
                return ResponseEntity.ok(token);
            }
        } catch (Exception e) {
            log.error("{}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/registro")
    public ResponseEntity<String> registrar(@RequestBody RegistroDTO registroDTO) {
        try {
            authServices.registrar(registroDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Usuario registrado exitosamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error al registrar usuario: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor al registrar usuario.");
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logout exitoso. Token eliminado en el cliente.");
    }
}