package com.example.inkasoporte.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mensaje_ti")
public class MensajeTi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mensajeId;

    private String emisorEmail;
    private String asunto;
    private String contenido;
    private LocalDateTime fechaEnvio = LocalDateTime.now();

    // Genera los Getters y Setters (o usa @Data de Lombok si lo agregaste)
    public Integer getMensajeId() { return mensajeId; }
    public void setMensajeId(Integer mensajeId) { this.mensajeId = mensajeId; }
    public String getEmisorEmail() { return emisorEmail; }
    public void setEmisorEmail(String emisorEmail) { this.emisorEmail = emisorEmail; }
    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }
    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }
    public LocalDateTime getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(LocalDateTime fechaEnvio) { this.fechaEnvio = fechaEnvio; }
}