package com.example.inkasoporte.controller;

import com.example.inkasoporte.model.MensajeTi;
import com.example.inkasoporte.repository.MensajeTiRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/soporte")
public class SoporteController {

    private final MensajeTiRepository repository;

    public SoporteController(MensajeTiRepository repository) {
        this.repository = repository;
    }

    // 1. CREAR TICKET (Uso del Supervisor)
    @PostMapping("/mensajes")
    public ResponseEntity<?> crearMensaje(@RequestBody MensajeTi mensaje) {
        MensajeTi guardado = repository.save(mensaje);
        return ResponseEntity.ok(Map.of(
                "estado", "Éxito",
                "ticket", guardado.getMensajeId(),
                "mensaje", "Ticket generado correctamente en estado ABIERTO"
        ));
    }

    // 2. VER TODOS LOS TICKETS (Uso del Administrador)
    @GetMapping("/mensajes")
    public ResponseEntity<List<MensajeTi>> listarTodos() {
        return ResponseEntity.ok(repository.findAllByOrderByFechaEnvioDesc());
    }

    // 3. VER MIS TICKETS (Uso del Supervisor enviando su email)
    @GetMapping("/mensajes/mis-tickets")
    public ResponseEntity<List<MensajeTi>> listarMisTickets(@RequestParam String email) {
        return ResponseEntity.ok(repository.findByEmisorEmailOrderByFechaEnvioDesc(email));
    }

    // 4. ACTUALIZAR ESTADO DEL TICKET (Uso del Administrador)
    @PatchMapping("/mensajes/{id}/estado")
    public ResponseEntity<?> actualizarEstado(@PathVariable Integer id, @RequestBody Map<String, String> body) {
        return repository.findById(id).map(ticket -> {
            String nuevoEstado = body.get("estado");
            ticket.setEstado(nuevoEstado);
            repository.save(ticket);
            return ResponseEntity.ok(Map.of(
                    "mensaje", "Estado del ticket " + id + " actualizado a " + nuevoEstado
            ));
        }).orElse(ResponseEntity.notFound().build());
    }
}