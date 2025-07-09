package modaverse.web.service;

import java.util.List;
import modaverse.web.domain.Producto;

public interface ProductoService {
    
    public List<Producto> getProductos();

    public void save(Producto producto);

    public void delete(Long productoID);

    public Producto getProducto(Long productoID);
}
