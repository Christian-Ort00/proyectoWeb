/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modaverse.web.service;

import modaverse.web.domain.Producto;
import java.util.List;

public interface ProductoService {

    // Se obtiene un listado de productos en un List
    List<Producto> getProductos(); // sin parámetro 'activos' en Modaverse

    // Se obtiene un Producto por su ID
    Producto getProducto(Long productoID);

    // Se inserta o actualiza un producto
    void save(Producto producto);

    // Se elimina el producto por ID
    void delete(Long productoID);

    // Lista de productos con precio entre ordenados por descripción (opcional si tu DAO lo soporta)
//    List<Producto> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup);

    // Consultas con JPQL (si tu DAO las implementa)
   // List<Producto> metodoJPQL(double precioInf, double precioSup);

    // Consultas nativas (si tu DAO las implementa)
  //  List<Producto> metodoNativo(double precioInf, double precioSup);
}
