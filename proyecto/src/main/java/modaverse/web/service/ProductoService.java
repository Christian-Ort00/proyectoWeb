package modaverse.web.service;

import java.util.List;
import modaverse.web.domain.Producto;

public interface ProductoService {

    List<Producto> getProductos();

    // Nuevos métodos de filtrado
    List<Producto> getProductosPorCategoriaNombre(String nombre);
    List<Producto> getProductosPorCategoriaId(Long categoriaId);

    Producto getProducto(Long productoID);

    void save(Producto producto);

    void delete(Long productoID);
}
