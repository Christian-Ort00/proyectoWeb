package modaverse.web.service.impl;

import java.util.List;
import modaverse.web.domain.Producto;
import modaverse.web.dao.ProductoDao;
import modaverse.web.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoDao productoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> getProductos() {
        return productoDao.findAll();
    }
}
