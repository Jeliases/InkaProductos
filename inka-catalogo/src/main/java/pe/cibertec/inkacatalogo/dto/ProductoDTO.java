package pe.cibertec.inkacatalogo.dto;

import java.math.BigDecimal;

public record ProductoDTO(
        String id,
        String sku,
        String nombre,
        BigDecimal precio,
        String categoria
) {}