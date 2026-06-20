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


    private String categoria;
    private String estado = "ABIERTO";

    private LocalDateTime fechaEnvio = LocalDateTime.now();

    // Getters y Setters
    public Integer getMensajeId() { return mensajeId; }
    public void setMensajeId(Integer mensajeId) { this.mensajeId = mensajeId; }
    public String getEmisorEmail() { return emisorEmail; }
    public void setEmisorEmail(String emisorEmail) { this.emisorEmail = emisorEmail; }
    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }
    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public LocalDateTime getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(LocalDateTime fechaEnvio) { this.fechaEnvio = fechaEnvio; }
}