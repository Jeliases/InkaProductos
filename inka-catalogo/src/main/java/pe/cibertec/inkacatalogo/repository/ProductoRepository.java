package pe.cibertec.inkacatalogo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import pe.cibertec.inkacatalogo.model.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends MongoRepository<Producto, String> {

    Optional<Producto> findBySku(String sku);

    @Query("{ 'categoriaId': ?0, 'activo': ?1 }")
    List<Producto> findByCategoriaIdAndActivo(Integer categoriaId, Boolean activo);

    @Query("{ 'activo': ?0 }")
    List<Producto> findByActivo(Boolean activo);
}