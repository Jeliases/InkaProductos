package com.example.inkasoporte.controller;

import com.example.inkasoporte.model.MensajeTi;
import com.example.inkasoporte.repository.MensajeTiRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/soporte")
public class SoporteController {

    private final MensajeTiRepository repository;

    public SoporteController(MensajeTiRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/mensajes")
    public ResponseEntity<?> crearMensaje(@RequestBody MensajeTi mensaje) {
        MensajeTi guardado = repository.save(mensaje);
        return ResponseEntity.ok(Map.of(
                "estado", "Éxito",
                "ticket", guardado.getMensajeId(),
                "mensaje", "Ticket guardado en PostgreSQL"
        ));
    }
}