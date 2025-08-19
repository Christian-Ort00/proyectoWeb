package modaverse.web.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import modaverse.web.domain.CarritoItem;
import modaverse.web.domain.Producto;

@Service
public class CarritoService {

    private List<CarritoItem> items = new ArrayList<>();

    public List<CarritoItem> getItems() {
        return items;
    }

    public void addProducto(Producto producto) {
        for (CarritoItem item : items) {
            if (item.getProducto().getProductoID().equals(producto.getProductoID())) {
                item.setCantidad(item.getCantidad() + 1);
                return;
            }
        }
        items.add(new CarritoItem(producto));
    }

    public void removeProducto(Long productoId) {
        items.removeIf(item -> item.getProducto().getProductoID().equals(productoId));
    }

    public void clear() {
        items.clear();
    }

    public double getTotal() {
        return items.stream().mapToDouble(CarritoItem::getSubtotal).sum();
    }
}
