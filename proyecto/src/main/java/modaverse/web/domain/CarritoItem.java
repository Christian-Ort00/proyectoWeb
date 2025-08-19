package modaverse.web.domain;

import java.io.Serializable;
import lombok.Data;

@Data
public class CarritoItem implements Serializable {
    private Producto producto;
    private int cantidad;

    public CarritoItem(Producto producto) {
        this.producto = producto;
        this.cantidad = 1;
    }

    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }
}
