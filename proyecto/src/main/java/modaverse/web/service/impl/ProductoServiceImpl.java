package modaverse.web.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import modaverse.web.dao.ProductoDao;
import modaverse.web.domain.Producto;
import modaverse.web.service.ProductoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoDao productoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> getProductos() {
        return productoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> getProductosPorCategoriaNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            return getProductos();
        }
        return productoDao.findByCategoria_NombreIgnoreCase(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> getProductosPorCategoriaId(Long categoriaId) {
        if (categoriaId == null) {
            return getProductos();
        }
        return productoDao.findByCategoria_CategoriaID(categoriaId);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto getProducto(Long productoID) {
        return productoDao.findById(productoID).orElse(null);
    }

    @Override
    @Transactional
    public void save(Producto producto) {
        productoDao.save(producto);
    }

    @Override
    @Transactional
    public void delete(Long productoID) {
        productoDao.deleteById(productoID);
    }
}
