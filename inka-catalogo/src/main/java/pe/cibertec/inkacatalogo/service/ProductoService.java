package pe.cibertec.inkacatalogo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.cibertec.inkacatalogo.dto.ProductoDTO;
import pe.cibertec.inkacatalogo.model.Producto;
import pe.cibertec.inkacatalogo.repository.ProductoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepo;

    public List<ProductoDTO> listar(Integer categoriaId, Boolean activo) {
        boolean filterActivo = (activo == null) ? true : activo;
        List<Producto> productos;

        // Aquí usamos los métodos que definimos en el repositorio de Mongo
        if (categoriaId == null || categoriaId == 0) {
            productos = productoRepo.findByActivo(filterActivo);
        } else {
            productos = productoRepo.findByCategoriaIdAndActivo(categoriaId, filterActivo);
        }

        return productos.stream()
                .map(this::toDTO)
                .toList();
    }

    private ProductoDTO toDTO(Producto p) {
        return new ProductoDTO(
                p.getId(),
                p.getSku(),
                p.getNombre(),
                p.getPrecio(),
                p.getCategoria() // Asumiendo que ahora guardas el nombre de la categoría
        );
    }
}