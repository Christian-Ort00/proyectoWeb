package modaverse.web.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Item extends Producto {
    private int cantidad;

    public Item() {
        this.cantidad = 1; // inicio en 1 al agregar al carrito
    }

    public Item(Producto p) {
        this.setProductoID(p.getProductoID());
        this.setNombre(p.getNombre());
        this.setDescripcion(p.getDescripcion());
        this.setPrecio(p.getPrecio());
        this.setRutaImagen(p.getRutaImagen());
        this.cantidad = 1;
    }

    public double getSubtotal() {
        Double precio = getPrecio();
        return (precio == null ? 0.0 : precio) * cantidad;
    }
}
