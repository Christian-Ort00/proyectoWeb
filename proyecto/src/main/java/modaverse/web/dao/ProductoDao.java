package modaverse.web.dao;

import java.util.List;
import modaverse.web.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoDao extends JpaRepository<Producto, Long> {

    // Filtrar por nombre de categoría (Hombre / Mujer)
    List<Producto> findByCategoria_NombreIgnoreCase(String nombre);

    
    List<Producto> findByCategoria_CategoriaID(Long categoriaID);
}
