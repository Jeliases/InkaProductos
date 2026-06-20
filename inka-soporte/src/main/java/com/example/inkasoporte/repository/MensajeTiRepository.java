package com.example.inkasoporte.repository;

import com.example.inkasoporte.model.MensajeTi;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MensajeTiRepository extends JpaRepository<MensajeTi, Integer> {

    // orden por fecha de creacion
    List<MensajeTi> findAllByOrderByFechaEnvioDesc();

    // Para que el SUPERVISOR vea solo sus propios tickets
    List<MensajeTi> findByEmisorEmailOrderByFechaEnvioDesc(String emisorEmail);

    // Para que el ADMIN filtre solo los pendientes
    List<MensajeTi> findByEstadoOrderByFechaEnvioDesc(String estado);
}