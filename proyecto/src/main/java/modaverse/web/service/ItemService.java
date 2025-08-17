package modaverse.web.service;

import modaverse.web.domain.Item;
import java.util.List;

public interface ItemService {

    // Mantiene en memoria (por sesión) la info del carrito
    List<Item> gets();

    // Recupera un ítem por su productoID (dentro del Item recibido)
    Item get(Item item);

    // Elimina un ítem por productoID
    void delete(Item item);

    // Crea o incrementa un ítem (si ya existe por productoID)
    void save(Item item);

    // Actualiza la cantidad de un ítem existente
    void update(Item item);

    // "Facturar": procesar y limpiar el carrito
    void facturar();

    // Total del carrito
    double getTotal();
}
