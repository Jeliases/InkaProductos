package pe.cibertec.inkacatalogo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.inkacatalogo.dto.ProductoDTO;
import pe.cibertec.inkacatalogo.model.Producto;
import pe.cibertec.inkacatalogo.repository.ProductoRepository;
import pe.cibertec.inkacatalogo.service.ProductoService;

import java.util.List;

@RestController
@RequestMapping("/api/catalogo/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;
    private final ProductoRepository productoRepo;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listar(
            @RequestParam(required = false) Integer categoriaId,
            @RequestParam(required = false) Boolean activo) {

        List<ProductoDTO> productos = productoService.listar(categoriaId, activo);
        return ResponseEntity.ok(productos);
    }
    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoRepo.save(producto));
    }

    @PostMapping("/sincronizar")
    public ResponseEntity<String> sincronizar(@RequestBody ProductoDTO productoDTO) {
        return ResponseEntity.ok("Producto sincronizado con éxito en MongoDB");
    }
}