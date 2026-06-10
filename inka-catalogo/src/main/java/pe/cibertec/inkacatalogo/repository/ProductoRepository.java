package pe.cibertec.inkacatalogo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import pe.cibertec.inkacatalogo.model.Producto;
import java.util.List;

public interface ProductoRepository extends MongoRepository<Producto, String> {

    // Filtro nativo de MongoDB: busca por ID de categoría y estado 'activo'
    // Nota: 'categoriaId' aquí es el campo plano en tu documento Mongo
    @Query("{ 'categoriaId': ?0, 'activo': ?1 }")
    List<Producto> findByCategoriaIdAndActivo(Integer categoriaId, Boolean activo);

    // Si no se especifica categoría (null), trae todo según el estado
    @Query("{ 'activo': ?0 }")
    List<Producto> findByActivo(Boolean activo);
}