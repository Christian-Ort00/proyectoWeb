package modaverse.web.service;

import java.util.List;
import modaverse.web.domain.Categoria;

public interface CategoriaService {
    
    List<Categoria> getCategorias();

    void save(Categoria categoria);

    void delete(Long categoriaID);

    Categoria getCategoria(Long categoriaID);
}
