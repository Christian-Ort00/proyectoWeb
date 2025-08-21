package modaverse.web.service.impl;

import java.util.List;
import modaverse.web.domain.Categoria;
import modaverse.web.dao.CategoriaDao;
import modaverse.web.dao.ProductoDao;
import modaverse.web.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaDao categoriaDao;

    @Autowired
    private ProductoDao productoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> getCategorias() {
        return categoriaDao.findAll();
    }

    @Override
    @Transactional
    public void save(Categoria categoria) {
        categoriaDao.save(categoria);
    }

    @Override
    @Transactional
    public void delete(Long categoriaID) {
        // Desasociar productos que tenían esa categoría
        var productos = productoDao.findAll();
        productos.forEach(p -> {
            if (p.getCategoria() != null && p.getCategoria().getCategoriaID().equals(categoriaID)) {
                p.setCategoria(null);
                productoDao.save(p);
            }
        });

        // Eliminar la categoría
        categoriaDao.deleteById(categoriaID);
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria getCategoria(Long categoriaID) {
        return categoriaDao.findById(categoriaID).orElse(null);
    }
}
