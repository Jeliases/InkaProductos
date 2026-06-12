package pe.cibertec.inkaproductos.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.inkaproductos.dto.LoginRequest;
import pe.cibertec.inkaproductos.services.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        try {
            return ResponseEntity.ok(authService.login(req));
        } catch (Exception e) {
            // Muestra el error en la terminal del backend
            e.printStackTrace();
            // Devuelve el mensaje real en lugar de un código genérico
            return ResponseEntity.status(400).body("Error en el login: " + e.getMessage());
        }
    }
}
